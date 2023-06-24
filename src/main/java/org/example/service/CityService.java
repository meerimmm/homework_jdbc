package org.example.service;

import org.example.model.City;


import java.util.List;

public  interface CityService {
    void createTable();
    void saveCity(City city);

    City findCityById(int id);

    List<City> getAllCities();


    void updateCity(int id,City city);


    void deleteByCityId(int id);


}
