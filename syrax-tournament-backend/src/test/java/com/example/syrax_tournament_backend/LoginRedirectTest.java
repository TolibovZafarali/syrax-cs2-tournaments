package com.example.syrax_tournament_backend;

import com.example.syrax_tournament_backend.model.Admin;
import com.example.syrax_tournament_backend.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class LoginRedirectTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setupAdmin() {
        adminRepository.deleteAll();
        Admin admin = new Admin();
        admin.setUsername("syraxadmin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        adminRepository.save(admin);
    }

    @Test
    void loginRedirectsToAdminApi() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "syraxadmin")
                        .param("password", "admin123"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/api/admin"));
    }
}
