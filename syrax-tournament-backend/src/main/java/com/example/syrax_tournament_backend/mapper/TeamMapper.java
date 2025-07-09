// src/main/java/com/example/syrax_tournament_backend/mapper/TeamMapper.java
package com.example.syrax_tournament_backend.mapper;

import com.example.syrax_tournament_backend.dto.TeamDTO;
import com.example.syrax_tournament_backend.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    public static TeamDTO toDto(Team team) {
        if (team == null) return null;
        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .logoUrl(team.getLogoUrl())
                .ownerSteamId(team.getOwnerSteamId())
                .build();
    }

    public static List<TeamDTO> toDtoList(List<Team> teams) {
        return teams.stream()
                .map(TeamMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Team toEntity(TeamDTO dto) {
        if (dto == null) return null;
        Team.TeamBuilder builder = Team.builder()
                .name(dto.getName())
                .logoUrl(dto.getLogoUrl())
                .ownerSteamId(dto.getOwnerSteamId());
        if (dto.getId() != null) {
            builder.id(dto.getId());
        }
        return builder.build();
    }

    public static void updateEntity(Team existing, TeamDTO dto) {
        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getLogoUrl() != null) existing.setLogoUrl(dto.getLogoUrl());
        if (dto.getOwnerSteamId() != null) existing.setOwnerSteamId(dto.getOwnerSteamId());
    }
}
