package com.manning.junitbook.ch08.configurations;


public class DefaultConfiguration implements Configuration{

    public DefaultConfiguration(String configurationName) {}

    @Override
    public String getSQL(String sqlString) {
        return null;
    }
}
