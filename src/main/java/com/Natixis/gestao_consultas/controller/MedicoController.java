package com.Natixis.gestao_consultas.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.Natixis.gestao_consultas.model.Consulta;
import com.Natixis.gestao_consultas.model.Medico;
import com.Natixis.gestao_consultas.repository.ConsultaRepository;
import com.Natixis.gestao_consultas.repository.MedicoRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/medico")
@PreAuthorize("hasRole('MEDICO')")
@Validated
public class MedicoController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    
    @GetMapping
    public String medicoHome(Model model, Principal principal, @RequestParam(required = false) String dataFiltro) {
        String username = principal.getName();
        Medico medicoAtual = medicoRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado para o username: " + username));

        List<Consulta> consultas = consultaRepository.findByMedico(medicoAtual);

        if (dataFiltro != null && !dataFiltro.isEmpty()) {
            LocalDate data = LocalDate.parse(dataFiltro);
            consultas = consultaRepository.findByMedicoAndDataConsulta(medicoAtual, data);
            model.addAttribute("dataFiltro", data);
        } else {
            consultas = consultaRepository.findByMedico(medicoAtual);
        }

        long totalAppointments = consultas.stream().filter(c -> "CANCELADA".equals(c.getEstado())).count();
        long totalBooked = consultas.stream().filter(c -> "CONCLUIDA".equals(c.getEstado())).count();
        long totalPending = consultas.stream().filter(c -> "PENDENTE".equals(c.getEstado())).count();

        model.addAttribute("totalAppointments", totalAppointments);
        model.addAttribute("totalBooked", totalBooked);
        model.addAttribute("totalPending", totalPending);
        model.addAttribute("consultas", consultas);
        model.addAttribute("username", medicoAtual.getNome());
        return "medico";
    }

    
    @PostMapping("/atualizarEstado/{id}")
    public String atualizarEstadoConsulta(@PathVariable Long id, @RequestParam String estado, Model model) {
        Consulta consultaExistente = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));
        
        consultaExistente.setEstado(estado);
        consultaRepository.save(consultaExistente);
        
        model.addAttribute("mensagemSucesso", "Estado da consulta atualizado com sucesso!");
        return "redirect:/medico";
    }
}
