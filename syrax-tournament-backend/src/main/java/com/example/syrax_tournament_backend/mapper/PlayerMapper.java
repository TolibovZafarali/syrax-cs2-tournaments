package com.example.syrax_tournament_backend.mapper;

import com.example.syrax_tournament_backend.dto.PlayerDTO;
import com.example.syrax_tournament_backend.model.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public static PlayerDTO toDto(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setSteamId(player.getSteamId());
        dto.setTelegramUsername(player.getTelegram());
        dto.setDiscordUsername(player.getDiscord());
        return dto;
    }

    public static Player toEntity(PlayerDTO dto) {
        Player player = new Player();
        player.setSteamId(dto.getSteamId());
        player.setTelegram(dto.getTelegramUsername());
        player.setDiscord(dto.getDiscordUsername());
        return player;
    }

    public static List<PlayerDTO> toDtoList(List<Player> players) {
        return players.stream()
                .map(PlayerMapper::toDto)
                .collect(Collectors.toList());
    }
}
