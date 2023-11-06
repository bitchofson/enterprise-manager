package ru.vsu.remezov.ioc.handlers;

import ru.vsu.remezov.ioc.models.DependencyParam;
public interface DependencyResolver {

    boolean canResolve(DependencyParam dependencyParam);

    Object resolve(DependencyParam dependencyParam);
}
