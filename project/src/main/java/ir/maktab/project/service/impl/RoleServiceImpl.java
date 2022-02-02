package ir.maktab.project.service.impl;

import ir.maktab.project.base.service.impl.BaseServiceImpl;
import ir.maktab.project.domain.Role;
import ir.maktab.project.domain.User;
import ir.maktab.project.repository.RoleRepository;
import ir.maktab.project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<Role, Long,String, RoleRepository> implements RoleService {

    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public void addDefaultRole() {

        saveAllDefaultRole(Arrays.asList(new Role("manager"), new Role("master"), new Role("student")));
    }

    @Override
    public Role findRoleByName(String name) {
        return repository.findRoleByName(name);
    }

    @Override
    public Class<Role> entityClass() {
        return Role.class;
    }
    private void saveAllDefaultRole(List<Role> roles) {
        repository.saveAll(roles);
    }

}
