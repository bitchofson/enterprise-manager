package ru.vsu.remezov.ioc.events;


import ru.vsu.remezov.ioc.models.ServiceDetails;

@FunctionalInterface
public interface ServiceDetailsCreated {
    void serviceDetailsCreated(ServiceDetails serviceDetails);
}
