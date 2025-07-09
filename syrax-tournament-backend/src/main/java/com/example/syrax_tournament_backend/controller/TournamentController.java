package com.example.syrax_tournament_backend.controller;

import com.example.syrax_tournament_backend.dto.TournamentDTO;
import com.example.syrax_tournament_backend.mapper.TournamentMapper;
import com.example.syrax_tournament_backend.model.Tournament;
import com.example.syrax_tournament_backend.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        path = "/api/tournaments",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Validated
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentRepository tournamentRepository;

    @GetMapping
    public List<TournamentDTO> getAllTournaments() {
        return TournamentMapper.toDtoList(
                tournamentRepository.findAll()
        );
    }

    @GetMapping("/{id}")
    public TournamentDTO getTournamentById(@PathVariable Long id) {
        Tournament entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id " + id));
        return TournamentMapper.toDto(entity);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public TournamentDTO createTournament(@Valid @RequestBody TournamentDTO dto) {
        Tournament toSave = TournamentMapper.toEntity(dto);
        Tournament saved  = tournamentRepository.save(toSave);
        return TournamentMapper.toDto(saved);
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public TournamentDTO updateTournament(
            @PathVariable Long id,
            @Valid @RequestBody TournamentDTO dto
    ) {
        Tournament existing = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id " + id));

        // copy over only the non-null fields
        TournamentMapper.updateEntity(existing, dto);
        Tournament updated = tournamentRepository.save(existing);
        return TournamentMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTournament(@PathVariable Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new RuntimeException("Tournament not found with id " + id);
        }
        tournamentRepository.deleteById(id);
    }
}
