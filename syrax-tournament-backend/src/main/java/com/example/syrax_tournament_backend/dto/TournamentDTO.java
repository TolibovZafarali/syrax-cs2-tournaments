package com.example.syrax_tournament_backend.dto;

import com.example.syrax_tournament_backend.model.TournamentStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TournamentDTO {
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Game is required")
    private String game;

    @Min(value = 1, message = "Team size must be at least 1")
    private int teamSize;

    @Min(value = 0, message = "Prize pool must be non-negative")
    private double prizePool;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "Status is required")
    private TournamentStatus status;

    private List<TeamDTO> teams;
    private List<MatchDTO> matches;
}
