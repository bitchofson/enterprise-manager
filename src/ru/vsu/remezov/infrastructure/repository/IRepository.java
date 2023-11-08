package ru.vsu.remezov.infrastructure.repository;

import java.util.List;

public interface IRepository<T> {

    T  create(T object);

    T delete(T object);

    T update(T objectOld, T objectNew);

    boolean isExist(int id);

    List<T> findAll();
}