package com.library.component;

import com.library.exception.AppException;
import com.library.model.server.Role;
import com.library.model.server.User;
import com.library.model.server.enums.RoleName;
import com.library.repository.RoleRepository;
import com.library.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

@Component
public class SystemInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SystemInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = requireNonNull(roleRepository, "roleRepository cannot be null");
        this.userRepository = requireNonNull(userRepository, "userRepository cannot be null");
        this.passwordEncoder = requireNonNull(passwordEncoder, "userRepository cannot be null");
    }

    @Override
    public void run(ApplicationArguments args) {
        initRoles();
        initAdmin();
    }

    private void initRoles() {
        for (RoleName roleName : RoleName.values()) {
            if (!roleRepository.existsByName(roleName)) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }
    }

    private void initAdmin() {
        if (!(userRepository.existsById(1L) || userRepository.existsByUsername("admin") || userRepository.existsByEmail("admin@admin.admin"))) {
            User user = new User()
                    .setName("admin")
                    .setSurname("admin")
                    .setUsername("admin")
                    .setEmail("admin@admin.admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setPhone("123123123");

            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException("User Role not set"));
            Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new AppException("Admin Role not set"));

            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            roles.add(adminRole);
            user.setRoles(roles);

            userRepository.save(user);
        }
    }
}
