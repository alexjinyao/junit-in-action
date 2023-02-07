package com.manning.junitbook.ch14.extensions;

import com.manning.junitbook.ch14.jdbc.ConnectionManager;
import com.manning.junitbook.ch14.jdbc.PassengerDao;
import com.manning.junitbook.ch14.jdbc.PassengerDaoImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * The class implements the ParameterResolver interface
 */
public class DataAccessObjectParameterResolver implements ParameterResolver {

    /**
     * The supports Parameter method returns true if the parameter is of type PassengerDao.
     * This is the missing parameter of the PassengerTest class constructor, so the parameter resolver supports only a PassengerDao object.
     * @param parameterContext
     * @param extensionContext
     * @return
     * @throws ParameterResolutionException
     */
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(PassengerDao.class);
    }

    /**
     * The resolveParameter method returns a newly initialized PassengerDaoImpl that receives as a constructor parameter the connection provided by the ConnectionManager.
     * This parameter will be injected into the test constructor at runtime.
     * @param parameterContext
     * @param extensionContext
     * @return
     * @throws ParameterResolutionException
     */
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new PassengerDaoImpl(ConnectionManager.getConnection());
    }
}
