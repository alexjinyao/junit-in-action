package com.manning.junitbook.databases;

import com.manning.junitbook.databases.model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountriesHibernateTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    public static final String[][] COUNTRY_INIT_DATA = {
            {"Australia", "AU"}, {"Canada", "CA"}, {"France", "FR"},
            {"Germany", "DE"}, {"Italy", "IT"}, {"Japan", "JP"}, {"Romania", "RO"},
            {"Russian Federation", "RU"}, {"Spain", "ES"}, {"Switzerland", "CH"},
            {"United Kingdom", "UK"}, {"United States", "US"}
    };

    @BeforeEach
    void setUp(){
        initExpectedCountryLists();

        /**
         * Initializes EntityManagerFactory and EntityManager objects.
         * EntityManagerFactory provides instances of EntityManager for connecting to the same database,
         * while EntityManager accesses a database in a particular application.
         */
        entityManagerFactory = Persistence.createEntityManagerFactory("manning.hibernate");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            entityManager.persist(country);
        }

        entityManager.getTransaction().commit();
    }

    @AfterEach
    void tearDown() {
        /**
         * Closes EntityManagerFactory and EntityManager
         */
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testCountryList() {
        List<Country> countryList = entityManager.createQuery("select c from Country c").getResultList();
        assertNotNull(countryList);
        assertEquals(COUNTRY_INIT_DATA.length, countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    public void testCountryListStartsWithA() {
        List<Country> countryList = entityManager.createQuery("select c from Country c where c.name like 'A%'").getResultList();
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
        }
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }
}
