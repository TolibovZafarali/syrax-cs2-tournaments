package com.example.syrax_tournament_backend.controller;

import com.example.syrax_tournament_backend.dto.PlayerDTO;
import com.example.syrax_tournament_backend.mapper.PlayerMapper;
import com.example.syrax_tournament_backend.model.Player;
import com.example.syrax_tournament_backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerRepository playerRepository;

    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return PlayerMapper.toDtoList(playerRepository.findAll());
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        return PlayerMapper.toDto(player);
    }

    @PostMapping
    public PlayerDTO createPlayer(@RequestBody PlayerDTO dto) {
        Player toSave = PlayerMapper.toEntity(dto);
        Player saved = playerRepository.save(toSave);
        return PlayerMapper.toDto(saved);
    }
}
