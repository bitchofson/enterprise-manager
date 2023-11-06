package ru.vsu.remezov.ioc.config;

public abstract class BaseSubConfiguration {

    private final Configuration parentConfig;

    protected BaseSubConfiguration(Configuration parentConfig) {
        this.parentConfig = parentConfig;
    }

    public Configuration and() {
        return this.parentConfig;
    }
}
