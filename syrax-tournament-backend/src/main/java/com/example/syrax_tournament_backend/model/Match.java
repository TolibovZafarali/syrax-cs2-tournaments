package com.example.syrax_tournament_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`match`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team_a_id")
    private Team teamA;

    @ManyToOne
    @JoinColumn(name = "team_b_id")
    private Team teamB;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Team winner;

    private int round;           // Optional - for bracket rounds
    private String lobbyLink;    // Optional - for CS2 lobby sharing
}
