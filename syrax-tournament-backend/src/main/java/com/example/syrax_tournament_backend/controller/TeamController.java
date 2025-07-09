package com.example.syrax_tournament_backend.controller;

import com.example.syrax_tournament_backend.dto.TeamDTO;
import com.example.syrax_tournament_backend.mapper.TeamMapper;
import com.example.syrax_tournament_backend.model.Team;
import com.example.syrax_tournament_backend.model.Tournament;
import com.example.syrax_tournament_backend.repository.TeamRepository;
import com.example.syrax_tournament_backend.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO dto) {
        Tournament tournament = tournamentRepository.findById(dto.getTournamentId())
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        Team team = TeamMapper.toEntity(dto, tournament);
        return ResponseEntity.ok(teamRepository.save(team));
    }

    @GetMapping("/tournament/{tournamentId}")
    public ResponseEntity<List<Team>> getTeamsByTournament(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        return ResponseEntity.ok(tournament.getTeams());
    }
}
