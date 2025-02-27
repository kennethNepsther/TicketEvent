package com.ticketevent.config.profile;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.enums.ERole;
import com.ticketevent.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Profile("prod")
@Configuration
@AllArgsConstructor
public class PrdConfig {
    final IUserRepository userRepository;
    @Bean
    public void startDatabase() {
    // Additional configurations for development environment

    var userAdmin = new UserEntity(null,"Kenneth", "Luzolo","934260018","test123",
            "kenneth@teste.com", true,true, ERole.ADMIN);

        var userOrganizer = new UserEntity(null,"Anahi", "Portilo","934260019","test123",
                "portilo@teste.com", true,true, ERole.ORGANIZER);

        userRepository.saveAll(Arrays.asList(userAdmin, userOrganizer)  );
    }
}
