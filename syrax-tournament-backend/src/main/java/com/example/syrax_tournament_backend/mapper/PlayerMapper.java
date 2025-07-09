package com.example.syrax_tournament_backend.mapper;

import com.example.syrax_tournament_backend.dto.PlayerDTO;
import com.example.syrax_tournament_backend.model.Player;
import com.example.syrax_tournament_backend.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public static PlayerDTO toDto(Player player) {
        if (player == null) return null;
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setSteamId(player.getSteamId());
        dto.setUsername(player.getUsername());
        dto.setTelegram(player.getTelegram());
        dto.setDiscord(player.getDiscord());
        dto.setTeamId(player.getTeam() != null ? player.getTeam().getId() : null);
        return dto;
    }

    public static Player toEntity(PlayerDTO dto) {
        if (dto == null) return null;
        Player player = new Player();
        player.setId(dto.getId());
        player.setSteamId(dto.getSteamId());
        player.setUsername(dto.getUsername());
        player.setTelegram(dto.getTelegram());
        player.setDiscord(dto.getDiscord());
        if (dto.getTeamId() != null) {
            player.setTeam(Team.builder().id(dto.getTeamId()).build());
        }
        return player;
    }

    public static List<PlayerDTO> toDtoList(List<Player> players) {
        if (players == null) return java.util.Collections.emptyList();
        return players.stream()
                .map(PlayerMapper::toDto)
                .collect(Collectors.toList());
    }
}
