package ir.maktab.project.service.impl;

import ir.maktab.project.base.service.impl.BaseServiceImpl;
import ir.maktab.project.domain.Manager;
import ir.maktab.project.repository.ManagerRepository;
import ir.maktab.project.service.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ManagerServiceImpl extends BaseServiceImpl<Manager,Long,String,ManagerRepository> implements ManagerService {


    public ManagerServiceImpl(ManagerRepository repository) {
        super(repository);
    }

    @Override
    public Class<Manager> entityClass() {
        return Manager.class;
    }


    @Override
    public long count() {
        return repository.count();
    }

}
