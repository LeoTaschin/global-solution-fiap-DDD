package com.fiap.upskilling.seed;

import com.fiap.upskilling.domain.NivelCarreira;
import com.fiap.upskilling.domain.NivelTrilha;
import com.fiap.upskilling.domain.Trilha;
import com.fiap.upskilling.domain.Usuario;
import com.fiap.upskilling.repository.TrilhaRepository;
import com.fiap.upskilling.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final TrilhaRepository trilhaRepository;

    public DatabaseSeeder(UsuarioRepository usuarioRepository, TrilhaRepository trilhaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.trilhaRepository = trilhaRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            Usuario u1 = new Usuario("Ana Silva", "ana.silva@upskilling.com", "Tecnologia da Informação", NivelCarreira.JUNIOR);
            Usuario u2 = new Usuario("Bruno Costa", "bruno.costa@upskilling.com", "Recursos Humanos", NivelCarreira.TRANSICAO);
            Usuario u3 = new Usuario("Carla Souza", "carla.souza@upskilling.com", "Inovação", NivelCarreira.PLENO);
            usuarioRepository.save(u1);
            usuarioRepository.save(u2);
            usuarioRepository.save(u3);
        }

        if (trilhaRepository.count() == 0) {
            Trilha t1 = new Trilha(
                    "Fundamentos de IA Generativa",
                    "Introdução prática à IA generativa aplicada ao futuro do trabalho.",
                    NivelTrilha.INICIANTE,
                    20,
                    "Automação e aumento de produtividade"
            );

            Trilha t2 = new Trilha(
                    "Soft Skills para 2030",
                    "Comunicação, colaboração remota e liderança em times distribuídos.",
                    NivelTrilha.INTERMEDIARIO,
                    16,
                    "Desenvolvimento humano e competências socioemocionais"
            );

            Trilha t3 = new Trilha(
                    "Arquiteturas Cloud-Native e DDD",
                    "Boas práticas de arquitetura para sistemas resilientes e escaláveis.",
                    NivelTrilha.AVANCADO,
                    32,
                    "Engenharia de software para o futuro"
            );

            trilhaRepository.save(t1);
            trilhaRepository.save(t2);
            trilhaRepository.save(t3);
        }
    }
}


