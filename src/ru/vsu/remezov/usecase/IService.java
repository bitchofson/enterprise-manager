package ru.vsu.remezov.usecase;

import java.util.List;

public interface IService<T> {

    void create(T object);

    void delete(T object);

    void update(T objectOld, T objectNew);

    boolean findById(int id);

    List<T> findAll();

}
