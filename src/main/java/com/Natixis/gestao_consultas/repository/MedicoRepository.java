package com.Natixis.gestao_consultas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Natixis.gestao_consultas.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
    Optional<Medico> findByUsername(String username);
    List<Medico> findByEspecialidade(String especialidade);


}
