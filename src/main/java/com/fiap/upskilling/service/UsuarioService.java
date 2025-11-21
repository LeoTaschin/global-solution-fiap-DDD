package com.fiap.upskilling.service;

import com.fiap.upskilling.domain.NivelCarreira;
import com.fiap.upskilling.domain.Usuario;
import com.fiap.upskilling.dto.UsuarioRequestDTO;
import com.fiap.upskilling.dto.UsuarioResponseDTO;
import com.fiap.upskilling.exception.UsuarioNaoEncontradoException;
import com.fiap.upskilling.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail");
        }
        Usuario usuario = mapToEntity(dto);
        Usuario salvo = usuarioRepository.save(usuario);
        return mapToResponse(salvo);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        return mapToResponse(usuario);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        if (!usuario.getEmail().equals(dto.getEmail()) && usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail");
        }

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setAreaAtuacao(dto.getAreaAtuacao());
        usuario.setNivelCarreira(parseNivelCarreira(dto.getNivelCarreira()));

        Usuario atualizado = usuarioRepository.save(usuario);
        return mapToResponse(atualizado);
    }

    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        usuarioRepository.delete(usuario);
    }

    private Usuario mapToEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setAreaAtuacao(dto.getAreaAtuacao());
        usuario.setNivelCarreira(parseNivelCarreira(dto.getNivelCarreira()));
        return usuario;
    }

    private UsuarioResponseDTO mapToResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getAreaAtuacao(),
                usuario.getNivelCarreira().name(),
                usuario.getDataCadastro()
        );
    }

    private NivelCarreira parseNivelCarreira(String nivel) {
        try {
            return NivelCarreira.valueOf(nivel.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Nível de carreira inválido. Valores permitidos: JUNIOR, PLENO, SENIOR, TRANSICAO");
        }
    }
}


