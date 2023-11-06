package ru.vsu.remezov.ioc.services;

import ru.vsu.remezov.ioc.exceptions.ServiceInstantiationException;
import ru.vsu.remezov.ioc.models.EnqueuedServiceDetails;
import ru.vsu.remezov.ioc.models.ServiceDetails;

import java.util.Collection;

public interface DependencyResolveService {

    void init(Collection<ServiceDetails> mappedServices);

    void checkDependencies(Collection<EnqueuedServiceDetails> enqueuedServiceDetails) throws ServiceInstantiationException;

    void addDependency(EnqueuedServiceDetails enqueuedServiceDetails, ServiceDetails serviceDetails);

    boolean isServiceResolved(EnqueuedServiceDetails enqueuedServiceDetails);

    boolean isDependencyRequired(EnqueuedServiceDetails enqueuedServiceDetails, ServiceDetails serviceDetails);
}
