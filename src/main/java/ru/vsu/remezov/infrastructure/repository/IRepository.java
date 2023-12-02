package ru.vsu.remezov.infrastructure.repository;

import java.util.List;

public interface IRepository<T> {

    T  create(T object);

    T delete(T object);

    T update(T object);

    boolean isExist(int id);

    List<T> findAll();

    T findById(int id);
    
    T findByName(String name);
}