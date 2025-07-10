package com.example.syrax_tournament_backend.controller;

import com.example.syrax_tournament_backend.dto.PlayerDTO;
import com.example.syrax_tournament_backend.mapper.PlayerMapper;
import com.example.syrax_tournament_backend.model.Player;
import com.example.syrax_tournament_backend.model.Team;
import com.example.syrax_tournament_backend.repository.PlayerRepository;
import com.example.syrax_tournament_backend.repository.TeamRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = PlayerMapper.toDtoList(playerRepository.findAll());
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id)
                .map(player -> ResponseEntity.ok(PlayerMapper.toDto(player)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO dto) {
        Team team = null;
        if (dto.getTeamId() != null) {
            team = teamRepository.findById(dto.getTeamId()).orElse(null);
            if (team == null) {
                return ResponseEntity.notFound().build();
            }
        }

        Player toSave = PlayerMapper.toEntity(dto, team);
        Player saved = playerRepository.save(toSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(PlayerMapper.toDto(saved));
    }
}
