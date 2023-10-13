package ru.vsu.remezov.infrastructure.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    T save(T object);

    T delete(String id);

    Optional<T> findById(String id);

    List<T> findAll();
}