package ru.vsu.remezov.ioc.config.configurations;


import ru.vsu.remezov.ioc.config.Configuration;
import ru.vsu.remezov.ioc.constants.Constants;
import ru.vsu.remezov.ioc.handlers.DependencyResolver;
import ru.vsu.remezov.ioc.models.ServiceDetails;
import ru.vsu.remezov.ioc.config.BaseSubConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class InstantiationConfiguration extends BaseSubConfiguration {

    private int maximumAllowedIterations;

    private final Collection<ServiceDetails> providedServices;

    private final Set<DependencyResolver> dependencyResolvers;

    public InstantiationConfiguration(Configuration parentConfig) {
        super(parentConfig);
        this.providedServices = new ArrayList<>();
        this.maximumAllowedIterations = Constants.MAX_NUMBER_OF_INSTANTIATION_ITERATIONS;
        this.dependencyResolvers = new HashSet<>();
    }

    public InstantiationConfiguration setMaximumNumberOfAllowedIterations(int num) {
        this.maximumAllowedIterations = num;
        return this;
    }

    public int getMaximumAllowedIterations() {
        return this.maximumAllowedIterations;
    }

    public InstantiationConfiguration addProvidedServices(Collection<ServiceDetails> serviceDetails) {
        this.providedServices.addAll(serviceDetails);
        return this;
    }

    public InstantiationConfiguration addDependencyResolver(DependencyResolver dependencyResolver) {
        this.dependencyResolvers.add(dependencyResolver);
        return this;
    }

    public Collection<ServiceDetails> getProvidedServices() {
        return this.providedServices;
    }

    public Set<DependencyResolver> getDependencyResolvers() {
        return this.dependencyResolvers;
    }
}
