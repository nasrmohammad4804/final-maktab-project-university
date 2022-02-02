package ir.maktab.project.service;

import ir.maktab.project.base.service.BaseService;
import ir.maktab.project.domain.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService extends BaseService<Role,String,Long> {

    long count();

    void addDefaultRole();
    Role findRoleByName(String name);
}
