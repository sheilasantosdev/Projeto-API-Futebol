package com.api.projeto_final.repository;

import com.api.projeto_final.enuns.Estado;
import com.api.projeto_final.model.Clube;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubeRepository extends JpaRepository<Clube, Long> {
    boolean existsByNomeClubeAndEstado(String nomeClube, Estado estado);
}
