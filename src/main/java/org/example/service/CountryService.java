package org.example.service;

import org.example.model.Country;

import java.util.List;

public interface CountryService {
    void createTable();
    void saveCountry(Country country);
    Country findCountryById(int id);
    List<Country> getAllCountries();
    void updateCountry(int id,Country country);
    public void deleteCountryById(int id);

}
