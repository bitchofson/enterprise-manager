package ru.vsu.remezov.ioc.services;

import ru.vsu.remezov.ioc.exceptions.ServiceInstantiationException;
import ru.vsu.remezov.ioc.models.ServiceDetails;

import java.util.Collection;
import java.util.Set;

public interface ServicesInstantiationService {
    Collection<ServiceDetails> instantiateServicesAndBeans(Set<ServiceDetails> mappedServices) throws ServiceInstantiationException;
}
