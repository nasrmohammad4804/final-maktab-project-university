package ir.maktab.project.service;

import ir.maktab.project.base.service.BaseService;
import ir.maktab.project.domain.Manager;
import org.springframework.stereotype.Service;

@Service
public interface ManagerService extends BaseService<Manager,String,Long> {

    long count();
}
