package ru.vsu.remezov.ioc.services;

import ru.vsu.remezov.ioc.models.ServiceDetails;

import java.util.Set;

public interface ServicesScanningService {

    Set<ServiceDetails> mapServices(Set<Class<?>> locatedClasses);
}
