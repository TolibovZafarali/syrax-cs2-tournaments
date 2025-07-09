package com.example.syrax_tournament_backend.dto;

import lombok.Data;

@Data
public class PlayerDTO {
    private String steamId;
    private String telegramUsername;
    private String discordUsername;
}
