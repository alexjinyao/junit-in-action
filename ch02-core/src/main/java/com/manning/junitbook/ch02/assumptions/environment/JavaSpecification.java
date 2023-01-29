package com.manning.junitbook.ch02.assumptions.environment;

public class JavaSpecification {
    private String version;

    public JavaSpecification(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
