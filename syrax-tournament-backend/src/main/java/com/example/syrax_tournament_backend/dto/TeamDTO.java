package com.example.syrax_tournament_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDTO {
    private Long id;

    @NotNull(message = "Team name is required")
    private String name;

    private String logoUrl;

    @NotNull(message = "Owner Steam ID is required")
    private String ownerSteamId;
}
