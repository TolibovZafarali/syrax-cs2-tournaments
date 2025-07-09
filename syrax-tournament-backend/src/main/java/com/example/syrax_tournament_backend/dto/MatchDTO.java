package com.example.syrax_tournament_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchDTO {
    private Long id;

    @NotNull(message = "Lobby link is required")
    private String lobbyLink;

    @NotNull(message = "Round is required")
    @Min(value = 1, message = "Round must be at least 1")
    private Integer round;

    @NotNull(message = "Tournament ID is required")
    private Long tournamentId;

    @NotNull(message = "Team A ID is required")
    private Long teamAId;

    @NotNull(message = "Team B ID is required")
    private Long teamBId;

    // winnerId is optional
    private Long winnerId;
}
