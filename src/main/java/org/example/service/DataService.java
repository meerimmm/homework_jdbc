package org.example.service;

import org.example.model.City;
import org.example.model.Country;
import org.example.model.RulerCity;

import java.util.List;

public  interface  DataService {
    void createTable();
    void saveCity(City city);
    void saveCountry(Country country);
    void saveRulerCity(RulerCity rulerCity);

    City findCityById(int id);
    Country findCountryById(int id);
    RulerCity findRulerCityById(int id);
    List<City> getAllCities();
    List<Country>getAllCountries();
    List<RulerCity>getAllRulersCities();

    void updateCity(int id,City city);
    void updateCountry(int id,Country country);

    void updateRulerCity(int id,RulerCity rulerCity);

    void deleteById(int id);
    public void deleteCountryById(int id);
    public void deleteRulerCityById(int id);
}
