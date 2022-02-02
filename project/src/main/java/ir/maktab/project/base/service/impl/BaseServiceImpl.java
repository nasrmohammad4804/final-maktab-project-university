package ir.maktab.project.base.service.impl;

import ir.maktab.project.base.domain.BaseEntity;
import ir.maktab.project.base.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<E extends BaseEntity<U,ID>,ID extends Serializable,U extends CharSequence,R extends JpaRepository<E,ID>>
        implements BaseService<E,U,ID> {

    protected final R repository;

    public  abstract Class<E> entityClass();

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public void  saveOrUpdate(E e) {
         repository.save(e);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("this "+ entityClass().getSimpleName()+" not found"));

    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }
}
