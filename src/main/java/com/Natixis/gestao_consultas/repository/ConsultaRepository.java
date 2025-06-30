package com.Natixis.gestao_consultas.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Natixis.gestao_consultas.model.Consulta;
import com.Natixis.gestao_consultas.model.Medico;
import com.Natixis.gestao_consultas.model.Paciente;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPaciente(Paciente paciente);

    void deleteByMedico(Medico medico);

    void deleteByPaciente(Paciente paciente);

    List<Consulta> findByMedico(Medico medico);

    List<Consulta> findByMedicoAndDataConsulta(Medico medico, LocalDate dataConsulta);

    List<Consulta> findByPacienteAndDataConsulta(Paciente paciente, LocalDate dataConsulta);

}
