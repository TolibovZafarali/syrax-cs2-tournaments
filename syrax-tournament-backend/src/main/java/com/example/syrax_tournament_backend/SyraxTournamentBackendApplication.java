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

}
