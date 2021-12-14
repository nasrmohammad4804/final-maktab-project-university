package ir.maktab.project.init;

import ir.maktab.project.domain.Manager;
import ir.maktab.project.domain.Role;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.service.ManagerService;
import ir.maktab.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        initRole();
        initManager();
    }

    private void initRole() {
        if (roleService.count() == 0)
            roleService.addDefaultRole();
    }

    private void initManager() {
        if (managerService.count() == 0) {

            Role role = roleService.findRoleByName("manager");

            Manager manager = Manager.builder().firstName("mohammad").lastName("nasr").userName("nasrmohammad4804@gmail.com")
                    .password(passwordEncoder.encode("13804804")).isActive(true).role(role).registerState(RegisterState.CONFIRM).build();

            managerService.save(manager);
        }
    }

}
