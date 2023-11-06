package ru.vsu.remezov.ioc.services;

import ru.vsu.remezov.ioc.exceptions.ClassLocationException;

import java.util.Set;

/**
 * Service for locating classes in the application context.
 */
public interface ClassLocator {

    Set<Class<?>> locateClasses(String directory) throws ClassLocationException;
}
