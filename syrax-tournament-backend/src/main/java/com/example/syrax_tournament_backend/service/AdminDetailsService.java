package com.example.syrax_tournament_backend.service;

import com.example.syrax_tournament_backend.model.Admin;
import com.example.syrax_tournament_backend.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔍 Attempting login for: " + username);

        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("❌ Admin not found");
                    return new UsernameNotFoundException("Admin not found");
                });

        System.out.println("✅ Admin found: " + admin.getUsername());
        System.out.println("✅ Password (hashed): " + admin.getPassword());

        return new User(
                admin.getUsername(),
                admin.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }
}
