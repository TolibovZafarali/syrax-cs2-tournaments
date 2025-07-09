package com.example.syrax_tournament_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String steamId;
    private String username;

    private String telegram;
    private String discord;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
