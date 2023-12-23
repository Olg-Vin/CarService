package org.vinio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vinio.models.UserRole;
import org.vinio.models.enums.Role;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String > {
    Optional<UserRole> findRoleByRole(Role role);
}
