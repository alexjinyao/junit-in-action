package com.manning.junitbook.ch04.assumptions.environment;

public class TestEnvironment {
    private JavaSpecification javaSpecification;
    private OperationSystem operationSystem;

    public TestEnvironment(JavaSpecification javaSpecification, OperationSystem operationSystem) {
        this.javaSpecification = javaSpecification;
        this.operationSystem = operationSystem;
    }

    public boolean isWindows() {
        return operationSystem.getName().contains("Windows");
    }

    public boolean isAmd64Architecture() {
        return operationSystem.getArchitecture().equals("amd64");
    }

    public String getJavaVersion() {
        return javaSpecification.getVersion();
    }
}
