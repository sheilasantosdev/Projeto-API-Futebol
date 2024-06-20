package com.api.projeto_final.controller;

import com.api.projeto_final.controller.advice.ExceptionPersonalizada;
import com.api.projeto_final.dto.ClubeRegistrarRequestDTO;
import com.api.projeto_final.dto.ClubeResponseDTO;
import com.api.projeto_final.service.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/clube")
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @PostMapping
    public ResponseEntity registrarClube(@RequestBody ClubeRegistrarRequestDTO dto) {
        try {
            clubeService.cadastrarClube(dto);
            return ResponseEntity.status(201).build();
        } catch (ExceptionPersonalizada ex) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getMsg());

        }
    }

    @PutMapping
    public ResponseEntity editar(@RequestParam Long id, @RequestBody ClubeRegistrarRequestDTO dto) {
        clubeService.editarClube(id, dto);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping
    public ResponseEntity inativar(@RequestParam Long id) {
        clubeService.inativarClube(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/buscar/id")
    public ResponseEntity<ClubeResponseDTO> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(clubeService.buscarClube(id));
    }

    @GetMapping("/buscar/todos")
    public ResponseEntity<List<ClubeResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(clubeService.buscarTodos());
    }
}