package ir.maktab.project.service.impl;

import ir.maktab.project.domain.Role;
import ir.maktab.project.repository.RoleRepository;
import ir.maktab.project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

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
    public List<Role> findAll() {
        return repository.findAll();
    }

    private void saveAllDefaultRole(List<Role> roles) {
        repository.saveAll(roles);
    }

}
