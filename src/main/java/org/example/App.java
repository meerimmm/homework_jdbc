package org.example;

import org.example.model.City;
import org.example.model.Country;
import org.example.model.RulerCity;
import org.example.service.impl.CityServiceImpl;
import org.example.service.impl.CountryServiceImpl;
import org.example.service.impl.RulerCityServiceImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        CountryServiceImpl countryService = new CountryServiceImpl();
        //countryService.createTable();
        /*countryService.saveCountry(new Country("Spain", 1636193));
       countryService.saveCountry(new Country("Italy", 380948));
        countryService.saveCountry(new Country("Kyrgyzstan", 7000000));
      countryService.saveCountry(new Country("Germany", 3677464));
        countryService.saveCountry(new Country("Russia", 149000000));
          countryService.saveCountry(new Country("America",5738492));*/
        // System.out.println(countryService.findCountryById(1));
        //countryService.deleteCountryById(2);
        // System.out.println(countryService.findCountryById(4));
        //countryService.updateCountry(1, new Country("spain", 4403923));
        System.out.println(countryService.getAllCountries());

        CityServiceImpl cityService = new CityServiceImpl();
        System.out.println(cityService.findCityById(2));
        // cityService.createTable();
       /* cityService.saveCity(new City("Bishkek",7000000,2170000,3));
       cityService.saveCity(new City("Moscow",149000000,619000000,5));
       cityService.saveCity(new City("Barcelona",1636193,780000,1));
       cityService.saveCity(new City("Berlin", 3677472, 9000,4));*/
        //cityService.deleteByCityId(1);
        // cityService.updateCity(2,new City("LA",1478392,39293,6));
        System.out.println(cityService.getAllCities());


        RulerCityServiceImpl rulerCityService = new RulerCityServiceImpl();
        //rulerCityService.createTable();
        /*rulerCityService.saveRulerCity(new RulerCity("Meder","Tynychbekov",40,2));
       rulerCityService.saveRulerCity(new RulerCity("Aksana","Chyrmasheva",45,3));
       rulerCityService.saveRulerCity(new RulerCity("Murad","Raimjanov",50,4));
        rulerCityService.saveRulerCity(new RulerCity("Meerim","Ismanalieva",35,2));*/
        //  rulerCityService.deleteRulerCityById(4);
        //System.out.println(rulerCityService.findRulerCityById(3));
        // rulerCityService.updateRulerCity(1, new RulerCity("Meerim", "Asatova", 25, 2));
        System.out.println(rulerCityService.getAllRulersCities());


    }
}
