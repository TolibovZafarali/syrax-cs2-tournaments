package com.example.syrax_tournament_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TournamentStatus {
    UPCOMING,
    ONGOING,
    COMPLETED,
    CANCELLED
}
