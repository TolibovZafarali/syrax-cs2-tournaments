package com.example.syrax_tournament_backend.service;

import com.example.syrax_tournament_backend.model.Admin;
import com.example.syrax_tournament_backend.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(AdminDetailsService.class);

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("🔍 Attempting login for: {}", username);

        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.info("❌ Admin not found");
                    return new UsernameNotFoundException("Admin not found");
                });

        log.debug("✅ Admin found: {}", admin.getUsername());
        log.debug("✅ Password (hashed): {}", admin.getPassword());

        return new User(
                admin.getUsername(),
                admin.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }
}
