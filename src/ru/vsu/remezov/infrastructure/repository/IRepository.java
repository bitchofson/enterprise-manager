package ru.vsu.remezov.infrastructure.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {

    T create(T object);

    T delete(String id);

    Optional<T> findById(String id);

    List<T> findAll();

}