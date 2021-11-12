package ir.maktab.project.service;

import ir.maktab.project.domain.Manager;
import org.springframework.stereotype.Service;

@Service
public interface ManagerService {

    long count();

    void save(Manager manager);
}
