package org.example.service;

import org.example.model.RulerCity;

import java.util.List;

public interface RulerCityService {
    void createTable();
    void saveRulerCity(RulerCity rulerCity);
    RulerCity findRulerCityById(int id);
    List<RulerCity> getAllRulersCities();
    void updateRulerCity(int id,RulerCity rulerCity);
    public void deleteRulerCityById(int id);
}
