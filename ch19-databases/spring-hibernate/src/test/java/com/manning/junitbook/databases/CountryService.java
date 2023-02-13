package com.manning.junitbook.databases;

import com.manning.junitbook.databases.model.Country;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CountryService {

    /**
     * EntityManager is used to access a database and is created by the container using the information in the persistence.xml
     * To use it at run time, we need to request that it be injected into one of the components via @PersistenceContext.
     */
    @PersistenceContext
    private EntityManager entityManager;

    public static final String[][] COUNTRY_INIT_DATA = {
            {"Australia", "AU"}, {"Canada", "CA"}, {"France", "FR"},
            {"Germany", "DE"}, {"Italy", "IT"}, {"Japan", "JP"}, {"Romania", "RO"},
            {"Russian Federation", "RU"}, {"Spain", "ES"}, {"Switzerland", "CH"},
            {"United Kingdom", "UK"}, {"United States", "US"}
    };

    @Transactional
    public void init(){
        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            entityManager.persist(country);
        }
    }

    @Transactional
    public void clear() {
        entityManager.createQuery("delete from Country c").executeUpdate();
    }

    public List<Country> getAllCountries() {
        return entityManager.createQuery("select c from Country c").getResultList();
    }

    public List<Country> getCountriesStartingWithA() {
        return entityManager.createQuery("select c from Country c where c.name like 'A%'").getResultList();
    }
}
