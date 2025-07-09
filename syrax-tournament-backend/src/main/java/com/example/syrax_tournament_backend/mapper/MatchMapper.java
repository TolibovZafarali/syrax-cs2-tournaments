package com.example.syrax_tournament_backend.mapper;

import com.example.syrax_tournament_backend.dto.MatchDTO;
import com.example.syrax_tournament_backend.model.Match;
import com.example.syrax_tournament_backend.model.Team;
import com.example.syrax_tournament_backend.model.Tournament;

import java.util.List;
import java.util.stream.Collectors;

public class MatchMapper {

    public static MatchDTO toDto(Match match) {
        if (match == null) return null;

        return MatchDTO.builder()
                .id(match.getId())
                .lobbyLink(match.getLobbyLink())
                .round(match.getRound())
                .tournamentId(match.getTournament() != null ? match.getTournament().getId() : null)
                .teamAId(match.getTeamA() != null ? match.getTeamA().getId() : null)
                .teamBId(match.getTeamB() != null ? match.getTeamB().getId() : null)
                .winnerId(match.getWinner() != null ? match.getWinner().getId() : null)
                .build();
    }

    public static List<MatchDTO> toDtoList(List<Match> matches) {
        return matches.stream()
                .map(MatchMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Match toEntity(MatchDTO dto) {
        if (dto == null) return null;

        Match.MatchBuilder builder = Match.builder()
                .lobbyLink(dto.getLobbyLink())
                .round(dto.getRound());

        if (dto.getTournamentId() != null) {
            builder.tournament(Tournament.builder()
                    .id(dto.getTournamentId())
                    .build());
        }

        if (dto.getTeamAId() != null) {
            builder.teamA(Team.builder()
                    .id(dto.getTeamAId())
                    .build());
        }

        if (dto.getTeamBId() != null) {
            builder.teamB(Team.builder()
                    .id(dto.getTeamBId())
                    .build());
        }

        if (dto.getWinnerId() != null) {
            builder.winner(Team.builder()
                    .id(dto.getWinnerId())
                    .build());
        }

        return builder.build();
    }

    public static void updateEntity(Match existing, MatchDTO dto) {
        if (dto.getLobbyLink() != null) {
            existing.setLobbyLink(dto.getLobbyLink());
        }
        if (dto.getRound() != null) {
            existing.setRound(dto.getRound());
        }
        if (dto.getTournamentId() != null) {
            existing.setTournament(Tournament.builder()
                    .id(dto.getTournamentId())
                    .build());
        }
        if (dto.getTeamAId() != null) {
            existing.setTeamA(Team.builder()
                    .id(dto.getTeamAId())
                    .build());
        }
        if (dto.getTeamBId() != null) {
            existing.setTeamB(Team.builder()
                    .id(dto.getTeamBId())
                    .build());
        }
        if (dto.getWinnerId() != null) {
            existing.setWinner(Team.builder()
                    .id(dto.getWinnerId())
                    .build());
        }
    }
}
