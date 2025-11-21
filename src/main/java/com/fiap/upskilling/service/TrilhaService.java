package com.fiap.upskilling.service;

import com.fiap.upskilling.domain.NivelTrilha;
import com.fiap.upskilling.domain.Trilha;
import com.fiap.upskilling.dto.TrilhaRequestDTO;
import com.fiap.upskilling.dto.TrilhaResponseDTO;
import com.fiap.upskilling.exception.TrilhaNaoEncontradaException;
import com.fiap.upskilling.repository.TrilhaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrilhaService {

    private final TrilhaRepository trilhaRepository;

    public TrilhaService(TrilhaRepository trilhaRepository) {
        this.trilhaRepository = trilhaRepository;
    }

    public TrilhaResponseDTO criar(TrilhaRequestDTO dto) {
        validarCargaHoraria(dto.getCargaHoraria());
        Trilha trilha = mapToEntity(dto);
        Trilha salva = trilhaRepository.save(trilha);
        return mapToResponse(salva);
    }

    @Transactional(readOnly = true)
    public List<TrilhaResponseDTO> listarTodas() {
        return trilhaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TrilhaResponseDTO buscarPorId(Long id) {
        Trilha trilha = trilhaRepository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException(id));
        return mapToResponse(trilha);
    }

    public TrilhaResponseDTO atualizar(Long id, TrilhaRequestDTO dto) {
        Trilha trilha = trilhaRepository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException(id));

        validarCargaHoraria(dto.getCargaHoraria());

        trilha.setNome(dto.getNome());
        trilha.setDescricao(dto.getDescricao());
        trilha.setNivel(parseNivel(dto.getNivel()));
        trilha.setCargaHoraria(dto.getCargaHoraria());
        trilha.setFocoPrincipal(dto.getFocoPrincipal());

        Trilha atualizada = trilhaRepository.save(trilha);
        return mapToResponse(atualizada);
    }

    public void deletar(Long id) {
        Trilha trilha = trilhaRepository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException(id));
        trilhaRepository.delete(trilha);
    }

    private void validarCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero");
        }
    }

    private Trilha mapToEntity(TrilhaRequestDTO dto) {
        Trilha trilha = new Trilha();
        trilha.setNome(dto.getNome());
        trilha.setDescricao(dto.getDescricao());
        trilha.setNivel(parseNivel(dto.getNivel()));
        trilha.setCargaHoraria(dto.getCargaHoraria());
        trilha.setFocoPrincipal(dto.getFocoPrincipal());
        return trilha;
    }

    private TrilhaResponseDTO mapToResponse(Trilha trilha) {
        return new TrilhaResponseDTO(
                trilha.getId(),
                trilha.getNome(),
                trilha.getDescricao(),
                trilha.getNivel().name(),
                trilha.getCargaHoraria(),
                trilha.getFocoPrincipal()
        );
    }

    private NivelTrilha parseNivel(String nivel) {
        try {
            return NivelTrilha.valueOf(nivel.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Nível da trilha inválido. Valores permitidos: INICIANTE, INTERMEDIARIO, AVANCADO");
        }
    }
}


