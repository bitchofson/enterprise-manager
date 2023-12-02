package ru.vsu.remezov.usecase;

import java.util.List;

public interface IService<T> {

    void create(T object);

    void delete(T object);

    void update(T object);

    boolean isExist(int id);

    T findById(int id);

    List<T> findAll();

    T findByName(String name);
}
