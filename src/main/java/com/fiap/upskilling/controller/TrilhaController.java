package com.fiap.upskilling.controller;

import com.fiap.upskilling.dto.TrilhaRequestDTO;
import com.fiap.upskilling.dto.TrilhaResponseDTO;
import com.fiap.upskilling.service.TrilhaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trilhas")
public class TrilhaController {

    private final TrilhaService trilhaService;

    public TrilhaController(TrilhaService trilhaService) {
        this.trilhaService = trilhaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrilhaResponseDTO criar(@RequestBody @Valid TrilhaRequestDTO dto) {
        return trilhaService.criar(dto);
    }

    @GetMapping
    public List<TrilhaResponseDTO> listarTodas() {
        return trilhaService.listarTodas();
    }

    @GetMapping("/{id}")
    public TrilhaResponseDTO buscarPorId(@PathVariable Long id) {
        return trilhaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public TrilhaResponseDTO atualizar(@PathVariable Long id,
                                       @RequestBody @Valid TrilhaRequestDTO dto) {
        return trilhaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        trilhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}


