package com.manning.junitbook.ch04.assumptions.environment;

public class JavaSpecification {
    private String version;

    public JavaSpecification(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
