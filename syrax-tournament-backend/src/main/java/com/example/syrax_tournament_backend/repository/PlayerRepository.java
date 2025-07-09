package com.example.syrax_tournament_backend.repository;

import com.example.syrax_tournament_backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId);
    Player findBySteamId(String steamId);
}
