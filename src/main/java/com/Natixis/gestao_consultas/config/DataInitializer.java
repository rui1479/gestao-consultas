package com.Natixis.gestao_consultas.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Natixis.gestao_consultas.model.Medico;
import com.Natixis.gestao_consultas.model.Paciente;
import com.Natixis.gestao_consultas.model.User;
import com.Natixis.gestao_consultas.model.Consulta;
import com.Natixis.gestao_consultas.repository.MedicoRepository;
import com.Natixis.gestao_consultas.repository.PacienteRepository;
import com.Natixis.gestao_consultas.repository.UserRepository;
import com.Natixis.gestao_consultas.repository.ConsultaRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            UserRepository userRepo,
            PacienteRepository pacienteRepo,
            MedicoRepository medicoRepo,
            ConsultaRepository consultaRepo,
            PasswordEncoder passwordEncoder) {
        return args -> {

            // --- ADMIN (only one) ---
            if (userRepo.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole("ADMIN");
                admin.setNome("Administrador");
                admin.setEmail("admin@mail.com");
                admin.setIdade(35);
                admin.setTelefone("900000000");
                userRepo.save(admin);
            }

            // --- PACIENTES ---
            if (pacienteRepo.count() == 0) {
                Paciente p1 = new Paciente();
                p1.setUsername("paciente1");
                p1.setPassword(passwordEncoder.encode("1234"));
                p1.setRole("PACIENTE");
                p1.setNome("João Silva");
                p1.setEmail("joao.silva@mail.com");
                p1.setIdade(30);
                p1.setTelefone("912345670");
                p1.setNif(111111111);
                p1.setDataNascimento(LocalDate.of(1993, 5, 20));
                pacienteRepo.save(p1);

                Paciente p2 = new Paciente();
                p2.setUsername("paciente2");
                p2.setPassword(passwordEncoder.encode("1234"));
                p2.setRole("PACIENTE");
                p2.setNome("Maria Santos");
                p2.setEmail("maria.santos@mail.com");
                p2.setIdade(28);
                p2.setTelefone("912345671");
                p2.setNif(222222222);
                p2.setDataNascimento(LocalDate.of(1995, 3, 15));
                pacienteRepo.save(p2);

                Paciente p3 = new Paciente();
                p3.setUsername("paciente3");
                p3.setPassword(passwordEncoder.encode("1234"));
                p3.setRole("PACIENTE");
                p3.setNome("Carlos Pereira");
                p3.setEmail("carlos.pereira@mail.com");
                p3.setIdade(45);
                p3.setTelefone("912345672");
                p3.setNif(333333333);
                p3.setDataNascimento(LocalDate.of(1978, 7, 10));
                pacienteRepo.save(p3);

                Paciente p4 = new Paciente();
                p4.setUsername("paciente4");
                p4.setPassword(passwordEncoder.encode("1234"));
                p4.setRole("PACIENTE");
                p4.setNome("Ana Lopes");
                p4.setEmail("ana.lopes@mail.com");
                p4.setIdade(33);
                p4.setTelefone("912345673");
                p4.setNif(444444444);
                p4.setDataNascimento(LocalDate.of(1990, 9, 5));
                pacienteRepo.save(p4);

                Paciente p5 = new Paciente();
                p5.setUsername("paciente5");
                p5.setPassword(passwordEncoder.encode("1234"));
                p5.setRole("PACIENTE");
                p5.setNome("Pedro Almeida");
                p5.setEmail("pedro.almeida@mail.com");
                p5.setIdade(27);
                p5.setTelefone("912345674");
                p5.setNif(555555555);
                p5.setDataNascimento(LocalDate.of(1996, 11, 12));
                pacienteRepo.save(p5);
            }

            // --- MEDICOS ---
            if (medicoRepo.count() == 0) {
                Medico m1 = new Medico();
                m1.setUsername("medico1");
                m1.setPassword(passwordEncoder.encode("1234"));
                m1.setRole("MEDICO");
                m1.setNome("Dr. Almeida");
                m1.setEmail("almeida@mail.com");
                m1.setIdade(50);
                m1.setTelefone("923456780");
                m1.setNumeroOrdemMedicos("12345");
                m1.setEspecialidade("Cardiologia");
                medicoRepo.save(m1);

                Medico m2 = new Medico();
                m2.setUsername("medico2");
                m2.setPassword(passwordEncoder.encode("1234"));
                m2.setRole("MEDICO");
                m2.setNome("Dra. Fernandes");
                m2.setEmail("fernandes@mail.com");
                m2.setIdade(40);
                m2.setTelefone("923456781");
                m2.setNumeroOrdemMedicos("67890");
                m2.setEspecialidade("Dermatologia");
                medicoRepo.save(m2);

                Medico m3 = new Medico();
                m3.setUsername("medico3");
                m3.setPassword(passwordEncoder.encode("1234"));
                m3.setRole("MEDICO");
                m3.setNome("Dr. Costa");
                m3.setEmail("costa@mail.com");
                m3.setIdade(38);
                m3.setTelefone("923456782");
                m3.setNumeroOrdemMedicos("54321");
                m3.setEspecialidade("Neurologia");
                medicoRepo.save(m3);

                Medico m4 = new Medico();
                m4.setUsername("medico4");
                m4.setPassword(passwordEncoder.encode("1234"));
                m4.setRole("MEDICO");
                m4.setNome("Dra. Mendes");
                m4.setEmail("mendes@mail.com");
                m4.setIdade(45);
                m4.setTelefone("923456783");
                m4.setNumeroOrdemMedicos("98765");
                m4.setEspecialidade("Ortopedia");
                medicoRepo.save(m4);

                Medico m5 = new Medico();
                m5.setUsername("medico5");
                m5.setPassword(passwordEncoder.encode("1234"));
                m5.setRole("MEDICO");
                m5.setNome("Dr. Sousa");
                m5.setEmail("sousa@mail.com");
                m5.setIdade(42);
                m5.setTelefone("923456784");
                m5.setNumeroOrdemMedicos("45678");
                m5.setEspecialidade("Pediatria");
                medicoRepo.save(m5);
            }

            // --- CONSULTAS ---
            if (consultaRepo.count() == 0) {
                List<Paciente> pacientes = pacienteRepo.findAll();
                List<Medico> medicos = medicoRepo.findAll();
                Random random = new Random();
                String[] estados = {"PENDENTE", "CONFIRMADA", "CANCELADA"};

                // Generate 25 consultations with multiple for each patient and medic
                for (int i = 0; i < 25; i++) {
                    Consulta c = new Consulta();
                    int pacienteIndex = i % pacientes.size(); // Cycle through patients
                    int medicoIndex = i % medicos.size();    // Cycle through medics
                    c.setPaciente(pacientes.get(pacienteIndex));
                    c.setMedico(medicos.get(medicoIndex));

                    // Vary dates over the next 30 days
                    int daysOffset = random.nextInt(30) + 1; // 1 to 30 days from now
                    c.setDataConsulta(LocalDate.now().plusDays(daysOffset));
                    // Vary times between 9:00 and 17:00
                    int hour = 9 + random.nextInt(8); // 9 to 16
                    int minute = random.nextInt(4) * 15; // 0, 15, 30, 45
                    c.setHoraConsulta(LocalTime.of(hour, minute));

                    // Random state
                    c.setEstado(estados[random.nextInt(estados.length)]);
                    // Random observation
                    String[] observacoes = {
                        "Consulta de rotina.",
                        "Avaliação pós-operatória.",
                        "Exame de seguimento.",
                        "Consulta de emergência.",
                        "Reavaliação de sintomas."
                    };
                    c.setObservacoes(observacoes[random.nextInt(observacoes.length)]);

                    consultaRepo.save(c);
                }
            }
        };
    }
}
