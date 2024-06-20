package com.api.projeto_final.service;

import com.api.projeto_final.controller.advice.ExceptionPersonalizada;
import com.api.projeto_final.dto.ClubeRegistrarRequestDTO;
import com.api.projeto_final.dto.ClubeResponseDTO;
import com.api.projeto_final.enuns.Status;
import com.api.projeto_final.model.Clube;
import com.api.projeto_final.repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    public void cadastrarClube(ClubeRegistrarRequestDTO dto) {
        validarClube(dto);
        Clube novoClube =  converterDtoParaClube(dto);

        clubeRepository.save(novoClube);
    }

    private static Clube converterDtoParaClube(ClubeRegistrarRequestDTO dto) {
        Clube clube = new Clube();
        clube.setNomeClube(dto.getNomeClube());
        clube.setEstado(dto.getEstado());
        clube.setDataCriacao(dto.getDataCriacao());
        clube.setStatus(Status.ATIVO);
        return clube;
    }

    private void validarClube(ClubeRegistrarRequestDTO dto) {
        if (dto.getNomeClube().length() <= 2 || dto.getDataCriacao().isAfter(LocalDate.now())) {
            throw new ExceptionPersonalizada(400, "Usuario com menos de dois caracteres ou data maior que  a data atual");

        }

        if (clubeRepository.existsByNomeClubeAndEstado(dto.getNomeClube(), dto.getEstado())) {
            throw new ExceptionPersonalizada(409, "Clube já existe");
        }
    }

    //PRECISAR REFATORAR METODO PARA OUTRA REGRA
    public void editarClube(Long id, ClubeRegistrarRequestDTO dto) {
        existClube(id);
        validarClube(dto);
        Clube clubeEditado = converterDtoParaClube(dto);
        clubeEditado.setId(id);
        clubeRepository.save(clubeEditado);
        
    }

    private void existClube(Long id) {
        if (!clubeRepository.existsById(id)) {
            throw new ExceptionPersonalizada(404, "Não encontrada");

        }
    }

    public void inativarClube(Long id) {
        existClube(id);
        Clube clube = clubeRepository.findById(id).get();
        clube.setStatus(Status.INATIVO);
        clubeRepository.save(clube);
    }

    public ClubeResponseDTO buscarClube(Long id) {
        existClube(id);
        return converterClubeParaClubeResponseDTO(clubeRepository.findById(id).get());
    }

    private ClubeResponseDTO converterClubeParaClubeResponseDTO(Clube clube) {
        ClubeResponseDTO dto = new ClubeResponseDTO();
        dto.setId(clube.getId());
        dto.setNomeClube(clube.getNomeClube());
        dto.setEstado(clube.getEstado());
        dto.setStatus(clube.getStatus());
        dto.setDataCriacao(clube.getDataCriacao());

        return dto;
    }

    public List<ClubeResponseDTO> buscarTodos() {
        List<Clube> clubeList = clubeRepository.findAll();

        if (!clubeList.isEmpty()) {
            List<ClubeResponseDTO> clubeResponseDTOS = new ArrayList<>();
            for (Clube c : clubeList) {
                clubeResponseDTOS.add(converterClubeParaClubeResponseDTO(c));
            }
            return clubeResponseDTOS;
        }
        return null;

    }
}
