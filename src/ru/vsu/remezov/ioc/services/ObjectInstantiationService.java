package ru.vsu.remezov.ioc.services;

import ru.vsu.remezov.ioc.exceptions.BeanInstantiationException;
import ru.vsu.remezov.ioc.exceptions.PreDestroyExecutionException;
import ru.vsu.remezov.ioc.exceptions.ServiceInstantiationException;
import ru.vsu.remezov.ioc.models.ServiceBeanDetails;
import ru.vsu.remezov.ioc.models.ServiceDetails;

public interface ObjectInstantiationService {

    void createInstance(ServiceDetails serviceDetails, Object[] constructorParams, Object[] autowiredFieldInstances) throws ServiceInstantiationException;

    void createBeanInstance(ServiceBeanDetails serviceBeanDetails) throws BeanInstantiationException;

    void destroyInstance(ServiceDetails serviceDetails) throws PreDestroyExecutionException;
}
