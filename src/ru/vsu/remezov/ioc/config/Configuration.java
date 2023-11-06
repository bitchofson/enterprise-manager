package ru.vsu.remezov.ioc.config;


import ru.vsu.remezov.ioc.config.configurations.InstantiationConfiguration;
import ru.vsu.remezov.ioc.config.configurations.ScanningConfiguration;

public class Configuration {

    private final ScanningConfiguration annotations;

    private final InstantiationConfiguration instantiations;

    public Configuration() {
        this.annotations = new ScanningConfiguration(this);
        this.instantiations = new InstantiationConfiguration(this);
    }

    public ScanningConfiguration scanning() {
        return this.annotations;
    }

    public InstantiationConfiguration instantiations() {
        return this.instantiations;
    }

    public Configuration build() {
        return this;
    }
}
