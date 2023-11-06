package ru.vsu.remezov.ioc.services;

import ru.vsu.remezov.ioc.models.Directory;

import java.io.File;

public interface DirectoryResolver {

    Directory resolveDirectory(Class<?> startupClass);

    Directory resolveDirectory(File directory);
}
