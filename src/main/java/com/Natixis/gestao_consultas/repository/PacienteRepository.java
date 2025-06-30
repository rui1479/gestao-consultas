package com.Natixis.gestao_consultas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Natixis.gestao_consultas.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    Optional<Paciente> findByUsername(String username);

    Optional<Paciente> findByNif(int nif);


}
