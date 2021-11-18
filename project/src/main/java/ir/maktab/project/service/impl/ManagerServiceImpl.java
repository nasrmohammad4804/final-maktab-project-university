package ir.maktab.project.service.impl;

import ir.maktab.project.domain.Manager;
import ir.maktab.project.repository.ManagerRepository;
import ir.maktab.project.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public long count() {
        return managerRepository.count();
    }

    @Override
    @Transactional
    public void save(Manager manager) {
        managerRepository.save(manager);
    }
}