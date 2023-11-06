package ru.vsu.remezov.ioc;


import ru.vsu.remezov.ioc.annotations.StartUp;
import ru.vsu.remezov.ioc.models.ServiceDetails;
import ru.vsu.remezov.ioc.config.Configuration;
import ru.vsu.remezov.ioc.enums.DirectoryType;
import ru.vsu.remezov.ioc.models.Directory;
import ru.vsu.remezov.ioc.services.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Application starting point.
 * <p>
 * Contains multiple starting point methods.
 */
public class Injector {

    /**
     * Overload with default configuration.
     *
     * @param startupClass any class from the client side.
     */
    public static DependencyContainer run(Class<?> startupClass) {
        return run(startupClass, new Configuration());
    }

    /**
     * Runs with startup class.
     *
     * @param startupClass  any class from the client side.
     * @param configuration client configuration.
     */
    public static DependencyContainer run(Class<?> startupClass, Configuration configuration) {
        final DependencyContainer dependencyContainer = run(new File[]{
                new File(new DirectoryResolverImpl().resolveDirectory(startupClass).getDirectory()),
        }, configuration);

        runStartUpMethod(startupClass, dependencyContainer);

        return dependencyContainer;
    }

    public static DependencyContainer run(File[] startupDirectories, Configuration configuration) {
        final ServicesScanningService scanningService = new ServicesScanningServiceImpl(configuration.scanning());
        final ObjectInstantiationService objectInstantiationService = new ObjectInstantiationServiceImpl();
        final ServicesInstantiationService instantiationService = new ServicesInstantiationServiceImpl(
                configuration.instantiations(),
                objectInstantiationService,
                new DependencyResolveServiceImpl(configuration.instantiations())
        );

        final Set<Class<?>> locatedClasses = new HashSet<>();
        final List<ServiceDetails> serviceDetails = new ArrayList<>();

        final Thread runner = new Thread(() -> {
            locatedClasses.addAll(locateClasses(startupDirectories));
            final Set<ServiceDetails> mappedServices = new HashSet<>(scanningService.mapServices(locatedClasses));
            serviceDetails.addAll(new ArrayList<>(instantiationService.instantiateServicesAndBeans(mappedServices)));
        });

        runner.setContextClassLoader(configuration.scanning().getClassLoader());
        runner.start();
        try {
            runner.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



        final DependencyContainer dependencyContainer = new DependencyContainerCached();
        dependencyContainer.init(locatedClasses, serviceDetails, objectInstantiationService);

        return dependencyContainer;
    }

    private static Set<Class<?>> locateClasses(File[] startupDirectories) {
        final Set<Class<?>> locatedClasses = new HashSet<>();
        final DirectoryResolver directoryResolver = new DirectoryResolverImpl();

        for (File startupDirectory : startupDirectories) {
            final Directory directory = directoryResolver.resolveDirectory(startupDirectory);

            ClassLocator classLocator = new ClassLocatorForDirectory();
            if (directory.getDirectoryType() == DirectoryType.JAR_FILE) {
                classLocator = new ClassLocatorForJarFile();
            }

            locatedClasses.addAll(classLocator.locateClasses(directory.getDirectory()));
        }

        return locatedClasses;
    }

    /**
     * This method calls executes when all services are loaded.
     * <p>
     * Looks for instantiated service from the given type.
     * <p>
     * If instance is found, looks for void method with 0 params
     * and with with @StartUp annotation and executes it.
     *
     * @param startupClass any class from the client side.
     */
    private static void runStartUpMethod(Class<?> startupClass, DependencyContainer dependencyContainer) {
        final ServiceDetails serviceDetails = dependencyContainer.getServiceDetails(startupClass, null);

        if (serviceDetails == null) {
            return;
        }

        for (Method declaredMethod : serviceDetails.getServiceType().getDeclaredMethods()) {
            if ((declaredMethod.getReturnType() != void.class &&
                    declaredMethod.getReturnType() != Void.class)
                    || !declaredMethod.isAnnotationPresent(StartUp.class)) {
                continue;
            }

            declaredMethod.setAccessible(true);
            final Object[] params = Arrays.stream(declaredMethod.getParameterTypes())
                    .map(dependencyContainer::getService)
                    .toArray(Object[]::new);

            try {
                declaredMethod.invoke(serviceDetails.getActualInstance(), params);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            return;
        }
    }
}
