package com.ticketevent.config.profile;

import com.ticketevent.entity.RoleEntity;
import com.ticketevent.entity.UserEntity;
import com.ticketevent.enums.ERole;
import com.ticketevent.repository.IRoleRepository;
import com.ticketevent.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Profile("dev")
@Configuration
@AllArgsConstructor
public class DevConfig {
    private static final Logger log = LoggerFactory.getLogger(DevConfig.class);
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    public CommandLineRunner startDatabase() {
        return args -> {
            // Criar e salvar roles
            Set<RoleEntity> roles = initializeRoles();
            // Criar e salvar users
            initializeUsers(roles);
        };
    }

    private Set<RoleEntity> initializeRoles() {
        Set<RoleEntity> roles = createInitialRoles();
        if (roleRepository.count() == 0) {
            roles = new HashSet<>(roleRepository.saveAll(roles));
            log.info("Roles iniciais criadas: {}", roles);
        } else {
            // Buscar as roles diretamente do repositório para garantir que sejam gerenciadas
            roles = new HashSet<>(roleRepository.findAll());
            log.info("Roles já existem, usando existentes: {}", roles);
        }
        return roles;
    }

    private void initializeUsers(Set<RoleEntity> roles) {
        if (userRepository.count() == 0) {
            Set<UserEntity> users = createInitialUsers(roles);
            userRepository.saveAll(users);
            log.info("Usuários iniciais criados: {}", users);
        } else {
            log.info("Usuários já existem, inicialização pulada.");
        }
    }

    private Set<RoleEntity> createInitialRoles() {
        return new HashSet<>(Arrays.asList(
                new RoleEntity(null, ERole.ADMIN.name()),
                new RoleEntity(null, ERole.ORGANIZER.name())

        ));
    }

    private Set<UserEntity> createInitialUsers(Set<RoleEntity> roles) {
        RoleEntity adminRole = getRoleByName(roles, ERole.ADMIN.name());
        RoleEntity organizerRole = getRoleByName(roles, ERole.ORGANIZER.name());


        return new HashSet<>(Arrays.asList(
                new UserEntity(null, "admin","user","934260018",
                        passwordEncoder.encode("admin123"), "admin@email.com",true,true, Set.of(adminRole, organizerRole)),

                new UserEntity(null, "organizer","user","934260019",
                        passwordEncoder.encode("organizer123"), "organizer@email.com",true,true, Set.of(organizerRole))
        ));
    }

    private RoleEntity getRoleByName(Set<RoleEntity> roles, String roleName) {
        return roles.stream()
                .filter(role -> role.getName().equals(roleName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Role " + roleName + " not initialized"));
    }
}
