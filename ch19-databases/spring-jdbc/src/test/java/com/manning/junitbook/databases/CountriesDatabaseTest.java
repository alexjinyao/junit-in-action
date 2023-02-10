package com.manning.junitbook.databases;

import com.manning.junitbook.databases.dao.CountryDao;
import com.manning.junitbook.databases.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * **SpringExtension** is used to integrate the Spring TestContext with the JUnit 5 Jupiter test.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class CountriesDatabaseTest {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CountriesLoader countriesLoader;

    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    @BeforeEach
    void setUp() {
        initExpectedCountryLists();
        countriesLoader.loadCountries();
    }

    /**
     * @DirtiesContext annotation is sued when a test has modified the context, in this case, the state of the embedded database.
     * This reduces the database burden; subsequent tests will be supplied with a new unmodified context.
     */
    @Test
    @DirtiesContext
    public void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    @DirtiesContext
    public void testCountryListStartsWithA() {
        List<Country> countryList = countryDao.getCountryListStartWith("A");
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
        }
    }


    private void initExpectedCountryLists() {
        for (int i = 0; i < CountriesLoader.COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = CountriesLoader.COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }

}
