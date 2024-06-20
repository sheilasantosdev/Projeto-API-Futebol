package com.api.projeto_final.service;

import com.api.projeto_final.controller.advice.ExceptionPersonalizada;
import com.api.projeto_final.dto.ClubeRegistrarRequestDTO;
import com.api.projeto_final.enuns.Estado;
import com.api.projeto_final.enuns.Status;
import com.api.projeto_final.model.Clube;
import com.api.projeto_final.repository.ClubeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClubeServiceTest {


    @InjectMocks
    private ClubeService clubeService;

    @Mock
    private ClubeRepository clubeRepository;

    private Clube clube;

    private ClubeRegistrarRequestDTO dto;

    @BeforeEach
    public void setUp() {
        startClube();
        startClubePesponseDdto();
    }

    @Test
    public void cadastrarClubeComSucesso() {

        //mock
        when(clubeRepository.save(any())).thenReturn(clube);
        when(clubeRepository.existsByNomeClubeAndEstado(dto.getNomeClube(), dto.getEstado())).thenReturn(false);

        clubeService.cadastrarClube(dto);

        verify(clubeRepository, times(1)).existsByNomeClubeAndEstado(dto.getNomeClube(), dto.getEstado());
        verify(clubeRepository, times(1)).save(any());
    }

    @Test
    public void devoRetornarExceptionPersonalizadaQquandoNomeClubeForMenorQueDois() {
        dto.setNomeClube("C");

        ExceptionPersonalizada ex = Assertions.assertThrows(
            ExceptionPersonalizada.class, () -> clubeService.cadastrarClube(dto));

        assertEquals(ExceptionPersonalizada.class, ex.getClass());
        assertEquals(400, ex.getStatus());
    }

    @Test
    public void devoRetornarExceptionPersonalizadaQquandoexistsByNomeClubeAndEstado() {

        when(clubeRepository.existsByNomeClubeAndEstado(dto.getNomeClube(), dto.getEstado())).thenReturn(true);

        ExceptionPersonalizada ex = Assertions.assertThrows(
                ExceptionPersonalizada.class, () -> clubeService.cadastrarClube(dto));


        assertEquals(ExceptionPersonalizada.class, ex.getClass());
        assertEquals(409, ex.getStatus());
    }

    private void startClube() {
        clube = new Clube();
        clube.setId(1L);
        clube.setNomeClube("Cruzeiro");
        clube.setEstado(Estado.MG);
        clube.setStatus(Status.ATIVO);
        clube.setDataCriacao(LocalDate.parse("2024-06-14"));
    }

    private void startClubePesponseDdto() {
        dto = new ClubeRegistrarRequestDTO();
        dto.setNomeClube("Cruzeiro");
        dto.setEstado(Estado.MG);
        dto.setDataCriacao(LocalDate.parse("2024-06-15"));
    }

}