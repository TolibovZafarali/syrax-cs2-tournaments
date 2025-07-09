package com.example.syrax_tournament_backend.repository;

import com.example.syrax_tournament_backend.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
