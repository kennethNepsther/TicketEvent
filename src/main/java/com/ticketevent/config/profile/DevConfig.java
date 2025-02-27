package com.ticketevent.config.profile;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.enums.ERole;
import com.ticketevent.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Configuration
@AllArgsConstructor
public class DevConfig {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner startDatabase() {
        return args -> {
            List<UserEntity> users = createInitialUsers();
            userRepository.saveAll(users);
        };
    }

    private List<UserEntity> createInitialUsers() {
        var userAdmin = new UserEntity(null, "Kenneth", "Luzolo", "934260018",
                passwordEncoder.encode("test123"), "kenneth@teste.com", true, true, ERole.ADMIN);

        var userOrganizer = new UserEntity(null, "Anahi", "Portilo", "934260019",
                passwordEncoder.encode("test123"), "portilo@teste.com", true, true, ERole.ORGANIZER);

        return Arrays.asList(userAdmin, userOrganizer);
    }
}
