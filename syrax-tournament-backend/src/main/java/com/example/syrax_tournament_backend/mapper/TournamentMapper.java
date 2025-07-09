package com.example.syrax_tournament_backend.mapper;

import com.example.syrax_tournament_backend.dto.TeamDTO;
import com.example.syrax_tournament_backend.dto.MatchDTO;
import com.example.syrax_tournament_backend.mapper.TeamMapper;
import com.example.syrax_tournament_backend.mapper.MatchMapper;

import com.example.syrax_tournament_backend.dto.TournamentDTO;
import com.example.syrax_tournament_backend.model.Match;
import com.example.syrax_tournament_backend.model.Team;
import com.example.syrax_tournament_backend.model.Tournament;
import java.util.List;
import java.util.stream.Collectors;

public class TournamentMapper {

    public static TournamentDTO toDto(Tournament tournament) {
        if (tournament == null) return null;

        return TournamentDTO.builder()
                .id(tournament.getId())
                .name(tournament.getName())
                .game(tournament.getGame())
                .teamSize(tournament.getTeamSize())
                .prizePool(tournament.getPrizePool())
                .startTime(tournament.getStartTime())
                .status(tournament.getStatus())
                .teams(
                        tournament.getTeams() != null
                                ? tournament.getTeams().stream()
                                .map(TeamMapper::toDto)
                                .collect(Collectors.toList())
                                : null
                )
                .matches(
                        tournament.getMatches() != null
                                ? tournament.getMatches().stream()
                                .map(MatchMapper::toDto)
                                .collect(Collectors.toList())
                                : null
                )
                .build();
    }

    public static List<TournamentDTO> toDtoList(List<Tournament> list) {
        return list.stream()
                .map(TournamentMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Tournament toEntity(TournamentDTO dto) {
        if (dto == null) return null;

        Tournament.TournamentBuilder builder = Tournament.builder()
                .name(dto.getName())
                .game(dto.getGame())
                .teamSize(dto.getTeamSize())
                .prizePool(dto.getPrizePool())
                .startTime(dto.getStartTime())
                .status(dto.getStatus());

        if (dto.getId() != null) {
            builder.id(dto.getId());
        }

        if (dto.getTeams() != null) {
            builder.teams(
                    dto.getTeams().stream()
                            .map(teamDto -> Team.builder().id(teamDto.getId()).build())
                            .collect(Collectors.toList())
            );
        }

        if (dto.getMatches() != null) {
            builder.matches(
                    dto.getMatches().stream()
                            .map(matchDto -> Match.builder().id(matchDto.getId()).build())
                            .collect(Collectors.toList())
            );
        }

        return builder.build();
    }

    public static void updateEntity(Tournament existing, TournamentDTO dto) {
        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getGame() != null) existing.setGame(dto.getGame());
        existing.setTeamSize(dto.getTeamSize());
        existing.setPrizePool(dto.getPrizePool());
        if (dto.getStartTime() != null) existing.setStartTime(dto.getStartTime());
        if (dto.getStatus() != null) existing.setStatus(dto.getStatus());

        if (dto.getTeams() != null) {
            existing.setTeams(
                    dto.getTeams().stream()
                            .map(teamDto -> Team.builder().id(teamDto.getId()).build())
                            .collect(Collectors.toList())
            );
        }

        if (dto.getMatches() != null) {
            existing.setMatches(
                    dto.getMatches().stream()
                            .map(matchDto -> Match.builder().id(matchDto.getId()).build())
                            .collect(Collectors.toList())
            );
        }
    }
}
