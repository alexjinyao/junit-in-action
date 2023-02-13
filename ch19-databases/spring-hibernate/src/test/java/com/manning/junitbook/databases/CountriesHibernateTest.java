package com.manning.junitbook.databases;

import com.manning.junitbook.databases.model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class CountriesHibernateTest {

    @Autowired
    private CountryService countryService;

    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryLitStartsWithA = new ArrayList<>();

    @BeforeEach
    void setUp() {
        countryService.init();
        initExpectedCountryLists();
    }

    @AfterEach
    void tearDown() {
        countryService.clear();
    }

    @Test
    void testCountryList() {
        final List<Country> countryList = countryService.getAllCountries();
        assertNotNull(countryList);
        assertEquals(CountryService.COUNTRY_INIT_DATA.length, countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    void testCountryListStartsWithA() {
        List<Country> countryList = countryService.getCountriesStartingWithA();
        assertNotNull(countryList);
        assertEquals(expectedCountryLitStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryLitStartsWithA.size(); i++) {
            assertEquals(expectedCountryLitStartsWithA.get(i), countryList.get(i));
        }
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < CountryService.COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = CountryService.COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryLitStartsWithA.add(country);
            }
        }
    }
}
