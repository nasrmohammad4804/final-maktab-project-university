package ir.maktab.project.base.service;

import ir.maktab.project.base.domain.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends BaseEntity<U,ID>,U extends CharSequence, ID extends Serializable> {

    void saveOrUpdate(E e);

    void deleteById(ID id);

    E findById(ID id);

    List<E> findAll();
}
