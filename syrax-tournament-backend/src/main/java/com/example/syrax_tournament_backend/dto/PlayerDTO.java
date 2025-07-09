package com.example.syrax_tournament_backend.dto;

import lombok.Data;

@Data
public class PlayerDTO {
    private Long id;
    private String steamId;
    private String username;
    private String telegram;
    private String discord;
    private Long teamId;
}
