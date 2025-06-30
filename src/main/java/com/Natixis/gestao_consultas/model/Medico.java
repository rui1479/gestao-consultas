package com.Natixis.gestao_consultas.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Medico extends User {

    @NotBlank(message = "Número da Ordem dos Médicos é obrigatório")
    @Size(min = 5, max = 20, message = "Número da Ordem dos Médicos deve ter entre 5 e 20 caracteres")
    private String numeroOrdemMedicos;

    @NotBlank(message = "Especialidade é obrigatória")
    @Size(min = 3, max = 100, message = "Especialidade deve ter entre 3 e 100 caracteres")
    private String especialidade;

    // Getters e setters
    public String getNumeroOrdemMedicos() {
        return numeroOrdemMedicos;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setNumeroOrdemMedicos(String numeroOrdemMedicos) {
        this.numeroOrdemMedicos = numeroOrdemMedicos;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
