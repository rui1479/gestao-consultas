package com.Natixis.gestao_consultas.controller;


import com.Natixis.gestao_consultas.model.Medico;
import com.Natixis.gestao_consultas.model.Paciente;
import com.Natixis.gestao_consultas.model.User;
import com.Natixis.gestao_consultas.repository.ConsultaRepository;
import com.Natixis.gestao_consultas.repository.MedicoRepository;
import com.Natixis.gestao_consultas.repository.UserRepository;
import com.Natixis.gestao_consultas.repository.PacienteRepository;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;	


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping
    public String adminPage(Model model) {
        List<User> allUsers = userRepository.findAll();

        List<User> medicos = allUsers.stream()
            .filter(user -> "MEDICO".equals(user.getRole()))
            .collect(Collectors.toList());

        List<User> pacientes = allUsers.stream()
            .filter(user -> "PACIENTE".equals(user.getRole()))
            .collect(Collectors.toList());

        model.addAttribute("medicos", medicos);
        model.addAttribute("pacientes", pacientes); 
        model.addAttribute("username", "admin"); 
        return "admin";
    }

    @GetMapping("/criarMedico")
    public String criarMedicoForm(Model model) {
        model.addAttribute("medico", new Medico());
        return "formulario-medico";
    }

    @PostMapping("/criarMedico")
    public String criarMedico(@ModelAttribute("medico") Medico medico,  BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            
            return "formulario-medico"; 
        }

        medico.setPassword(passwordEncoder.encode(medico.getPassword()));

        medicoRepository.save(medico);
        model.addAttribute("mensagemSucesso", "Médico criado com sucesso!");
        return "redirect:/admin"; 
    }



}
