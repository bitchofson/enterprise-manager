package ru.vsu.remezov.infrastructure.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {

    T  create(T object);

    T delete(T object);

    T update(T objectOld, T objectNew);

    boolean findById(int id);

    List<T> findAll();
}