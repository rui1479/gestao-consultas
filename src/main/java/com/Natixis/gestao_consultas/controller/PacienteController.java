package com.Natixis.gestao_consultas.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import com.Natixis.gestao_consultas.model.Consulta;
import com.Natixis.gestao_consultas.model.Medico;
import com.Natixis.gestao_consultas.model.Paciente;
import com.Natixis.gestao_consultas.model.User;
import com.Natixis.gestao_consultas.repository.ConsultaRepository;
import com.Natixis.gestao_consultas.repository.MedicoRepository;
import com.Natixis.gestao_consultas.repository.PacienteRepository;
import com.Natixis.gestao_consultas.repository.UserRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/paciente")
@PreAuthorize("hasRole('PACIENTE')")
@Validated
public class PacienteController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UserRepository userRepository;

    
    @GetMapping
    public String paginaPaciente(Model model, Principal principal, @RequestParam(required = false) String dataFiltro) {
        String username = principal.getName();
        Paciente pacienteAtual = pacienteRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado para o username: " + username));
        List<Consulta> consultas = consultaRepository.findByPaciente(pacienteAtual);
        
        if (dataFiltro != null && !dataFiltro.isEmpty()) {
            LocalDate data = LocalDate.parse(dataFiltro);
            consultas = consultaRepository.findByPacienteAndDataConsulta(pacienteAtual, data);
            model.addAttribute("dataFiltro", data);
        } else {
            consultas = consultaRepository.findByPaciente(pacienteAtual);
        }

        model.addAttribute("username", pacienteAtual.getNome());
        model.addAttribute("consultas", consultas);
        return "paciente";
    }

    // Mostrar formulário para marcar consulta
    @GetMapping("/marcar")
    public String mostrarFormularioMarcarConsulta(Model model, Principal principal) {
            Consulta consulta = new Consulta();

            String username = principal.getName();
            System.out.println("Looking for patient with username: " + username);
            Paciente pacienteAtual = pacienteRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado para o username: " + username));          
            List<User> medicos = userRepository.getUsersByRole("MEDICO");

            Set<String> especialidades = medicoRepository.findAll().stream()
                .map(Medico::getEspecialidade)
                .filter(especialidade -> especialidade != null && !especialidade.isEmpty())
                .collect(Collectors.toCollection(HashSet::new));
            model.addAttribute("especialidades", new ArrayList<>(especialidades));

            model.addAttribute("consulta", consulta);
            model.addAttribute("pacienteAtual", pacienteAtual);
            model.addAttribute("medicos", medicos);
        return "formulario-consulta"; 
    }

    // Guardar nova consulta
    @PostMapping("/marcar")
    public String guardarConsulta(@Valid @ModelAttribute("consulta") Consulta consulta,
                                   BindingResult result,
                                   Principal principal) {
        if (result.hasErrors()) {
            return "formulario-consulta";
        }

        // Procurar paciente pelo NIF
        Paciente paciente = pacienteRepository.findByUsername(principal.getName())
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado para o username: " + principal.getName()));
        
        consulta.setPaciente(paciente);
        consulta.setEstado("PENDENTE"); // estado inicial
        consultaRepository.save(consulta);

        return "redirect:/paciente";
    }

    // Cancelar consulta 
    @PostMapping("/consultas/cancelar/{id}")
    public String cancelarConsulta(@PathVariable Long id, Model model, Principal principal) {
        Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada: " + id));
        Paciente pacienteAtual = pacienteRepository.findByUsername(principal.getName())
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado para o username: " + principal.getName()));

        if (!consulta.getPaciente().getId().equals(pacienteAtual.getId())) {
            model.addAttribute("errorMessage", "Você não tem permissão para cancelar esta consulta.");
            return "paciente";
        }

        consulta.setEstado("CANCELADA");
        consultaRepository.save(consulta);
        model.addAttribute("successMessage", "Consulta cancelada com sucesso!");
        return "redirect:/paciente";
    }
}
