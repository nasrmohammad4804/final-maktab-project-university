package ir.maktab.project.service;

import ir.maktab.project.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    long count();

    void addDefaultRole();
    Role findRoleByName(String name);

    List<Role> findAll();


}
