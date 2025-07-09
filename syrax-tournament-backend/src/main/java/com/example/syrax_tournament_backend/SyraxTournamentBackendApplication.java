package com.example.syrax_tournament_backend;

import com.example.syrax_tournament_backend.model.*;
import com.example.syrax_tournament_backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SyraxTournamentBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyraxTournamentBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init(AdminRepository adminRepository, BCryptPasswordEncoder encoder) {
		return args -> {
			String username = "syraxadmin";
			String rawPassword = "admin123";

			if (adminRepository.findByUsername(username).isEmpty()) {
				Admin admin = Admin.builder()
						.username(username)
						.password(encoder.encode(rawPassword))
						.build();
				adminRepository.save(admin);
				System.out.println("✅ Default admin user created: " + username);
			} else {
				System.out.println("ℹ️ Admin user already exists: " + username);
			}
		};
	}

	@Bean
	CommandLineRunner seedData(
			TournamentRepository tournamentRepository,
			TeamRepository teamRepository,
			PlayerRepository playerRepository,
			MatchRepository matchRepository
	) {
		return args -> {
			if (tournamentRepository.count() == 0) {
				// ✅ Create a test tournament
				var tournament = Tournament.builder()
						.name("Weekly 2v2 Test")
						.game("CS2")
						.teamSize(2)
						.prizePool(100.00)
						.status("upcoming")
						.startTime(java.time.LocalDateTime.now().plusDays(2))
						.build();
				tournamentRepository.save(tournament);

				// ✅ Create 2 teams
				var teamA = Team.builder()
						.name("Team Alpha")
						.ownerSteamId("76561198011111111")
						.tournament(tournament)
						.build();

				var teamB = Team.builder()
						.name("Team Bravo")
						.ownerSteamId("76561198022222222")
						.tournament(tournament)
						.build();

				teamRepository.save(teamA);
				teamRepository.save(teamB);

				// ✅ Add players to both teams
				playerRepository.save(Player.builder()
						.steamId("76561198011111111")
						.username("PlayerOne")
						.discord("PlayerOne#1234")
						.telegram("@playerone")
						.team(teamA)
						.build());

				playerRepository.save(Player.builder()
						.steamId("76561198011111112")
						.username("PlayerTwo")
						.discord("PlayerTwo#1234")
						.telegram("@playertwo")
						.team(teamA)
						.build());

				playerRepository.save(Player.builder()
						.steamId("76561198022222222")
						.username("PlayerThree")
						.discord("PlayerThree#1234")
						.telegram("@playerthree")
						.team(teamB)
						.build());

				playerRepository.save(Player.builder()
						.steamId("76561198022222223")
						.username("PlayerFour")
						.discord("PlayerFour#1234")
						.telegram("@playerfour")
						.team(teamB)
						.build());

				// ✅ Create a match
				matchRepository.save(Match.builder()
						.tournament(tournament)
						.teamA(teamA)
						.teamB(teamB)
						.round(1)
						.lobbyLink("steam://joinlobby/730/1234567890/76561198011111111")
						.build());

				System.out.println("✅ Seeded test tournament, teams, players, and match.");
			}
		};
	}

}
