package com.manning.junitbook.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Service means Spring will automatically create a bean of the type of this class.
 * Implements the ApplicationContextAware interface, it will have a reference to the application context that will use to publish events.
 */
@Service
public class RegistrationManager implements ApplicationContextAware {

    /**
     * Keeps a reference of application context that it will use to publish events.
     */
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
