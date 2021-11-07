package ir.maktab.project.repository;

import ir.maktab.project.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByName(String name);
}
