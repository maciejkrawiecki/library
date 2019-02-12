package com.library.repository;

import com.library.model.server.Role;
import com.library.model.server.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(RoleName roleName);

    Optional<Role> findByName(RoleName roleUser);
}
