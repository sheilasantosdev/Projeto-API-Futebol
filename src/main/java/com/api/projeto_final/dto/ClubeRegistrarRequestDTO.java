package com.api.projeto_final.dto;

import com.api.projeto_final.enuns.Estado;

import java.time.LocalDate;

public class ClubeRegistrarRequestDTO {

    private String nomeClube;

    private LocalDate dataCriacao;

    private Estado estado;

    public String getNomeClube() {
        return nomeClube;
    }

    public void setNomeClube(String nomeClube) {
        this.nomeClube = nomeClube;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
