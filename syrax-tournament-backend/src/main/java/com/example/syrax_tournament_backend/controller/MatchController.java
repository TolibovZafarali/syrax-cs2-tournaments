package com.example.syrax_tournament_backend.controller;

import com.example.syrax_tournament_backend.dto.MatchDTO;
import com.example.syrax_tournament_backend.mapper.MatchMapper;
import com.example.syrax_tournament_backend.model.Match;
import com.example.syrax_tournament_backend.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchRepository matchRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MatchDTO> getAllMatches() {
        return MatchMapper.toDtoList(matchRepository.findAll());
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MatchDTO getMatchById(@PathVariable Long id) {
        Match m = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id " + id));
        return MatchMapper.toDto(m);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public MatchDTO createMatch(@Valid @RequestBody MatchDTO dto) {
        Match saved = matchRepository.save(MatchMapper.toEntity(dto));
        return MatchMapper.toDto(saved);
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MatchDTO updateMatch(
            @PathVariable Long id,
            @Valid @RequestBody MatchDTO dto
    ) {
        Match existing = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id " + id));
        MatchMapper.updateEntity(existing, dto);
        Match updated = matchRepository.save(existing);
        return MatchMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match not found with id " + id);
        }
        matchRepository.deleteById(id);
    }
}
