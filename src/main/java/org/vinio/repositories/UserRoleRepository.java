package org.vinio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vinio.models.UserRole;

import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String > {
}
