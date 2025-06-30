package com.Natixis.gestao_consultas.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class Paciente extends User {

    @NotNull(message = "NIF é obrigatório")
    @Digits(integer = 9, fraction = 0, message = "NIF deve ter exatamente 9 dígitos")
    private int nif;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser uma data passada")
    private LocalDate dataNascimento;

    // Getters e setters
    public int getNif() {
        return nif;
    }
    public void setNif(int nif) {
        this.nif = nif;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
