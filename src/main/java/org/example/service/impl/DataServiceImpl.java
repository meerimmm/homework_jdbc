package org.example.service.impl;

import org.example.config.JDBC;
import org.example.model.City;
import org.example.model.Country;
import org.example.model.RulerCity;
import org.example.service.DataService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataServiceImpl implements DataService {
    public void createTable() {
        String query = """
                    create table country(
                   id serial  primary key ,
                   name varchar(50)not null ,
                   population integer not null
                   );
                   
                   create  table cities(
                   id serial  primary key ,
                   name varchar(50) not null,
                   population integer not null,
                   square integer not null ,
                   country_id integer references country(id)
                ); 
                  
                   create table  rulers_cities(
                   id serial  primary key ,
                   name varchar(50)not null ,
                   last_name varchar(70)not null,
                   age integer not null,
                  city_id integer  references cities(id)
                   );
                             
                   """;

        try (Connection connection = JDBC.getConnection()) {

            Statement statement = connection.createStatement();

            statement.execute(query);

            System.out.println("Table country,cities,rules_cities successfully created!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveCity(City city) {
        String query = """
                insert into cities (name, population, square,country_id) values (?, ?, ?,?);
                """;

        try (Connection connection = JDBC.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getPopulation());
            preparedStatement.setInt(3, city.getSquare());
            preparedStatement.setInt(4, city.getCountryId());


            System.out.println("The data was successfully added to the city table");

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveCountry(Country country) {
        String query = """
                insert into country (name, population) values (?, ?);
                """;

        try (Connection connection = JDBC.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getPopulation());
            System.out.println("The data was successfully added to the country table");

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveRulerCity(RulerCity rulerCity) {
        String query = """
                            
                insert into rulers_cities (name, last_name,age,city_id) values (?, ?, ?,?);
                """;

        try (Connection connection = JDBC.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rulerCity.getName());
            preparedStatement.setString(2, rulerCity.getLastName());
            preparedStatement.setInt(3, rulerCity.getAge());
            preparedStatement.setInt(4, rulerCity.getCityId());

            System.out.println("The data was successfully added to the rulers_cities table");

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public City findCityById(int id) {
        String query = "select * from cities where id =? ";

        City city = new City();

        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                city.setPopulation(resultSet.getInt("population"));
                city.setSquare(resultSet.getInt("square"));
                city.setSquare(resultSet.getInt("country_id"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return city;
    }

    public Country findCountryById(int id) {
        String query = "select * from country where id =? ";

        Country country = new Country();

        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                country.setId(resultSet.getInt("id"));
                country.setName(resultSet.getString("name"));
                country.setPopulation(resultSet.getInt("population"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return country;

    }

    public RulerCity findRulerCityById(int id) {
        String query = "select * from rulers_cities where id =? ";

        RulerCity rulerCity = new RulerCity();

        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                rulerCity.setId(resultSet.getInt("id"));
                rulerCity.setName(resultSet.getString("name"));
                rulerCity.setLastName(resultSet.getString("last_name"));
                rulerCity.setAge(resultSet.getInt("age"));
                rulerCity.setAge(resultSet.getInt("city_id"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rulerCity;

    }

    public List<City> getAllCities() {
        String query = """
                select * from cities;
                """;

        List<City> cities = new ArrayList<>();
        try (Connection connection = JDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                city.setPopulation(resultSet.getInt("population"));
                city.setSquare(resultSet.getInt("square"));
                city.setCountryId(resultSet.getInt("country_id"));

                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }

    public List<Country> getAllCountries() {
        String query = """
                select * from country;
                """;

        List<Country> countries = new ArrayList<>();
        try (Connection connection = JDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setName(resultSet.getString("name"));
                country.setPopulation(resultSet.getInt("population"));
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countries;
    }

    public List<RulerCity> getAllRulersCities() {
        String query = """
                select * from rulers_cities;
                """;

        List<RulerCity> rulerCities = new ArrayList<>();
        try (Connection connection = JDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                RulerCity rulerCity = new RulerCity();
                rulerCity.setId(resultSet.getInt("id"));
                rulerCity.setName(resultSet.getString("name"));
                rulerCity.setLastName(resultSet.getString("last_name"));
                rulerCity.setAge(resultSet.getInt("age"));
                rulerCity.setId(resultSet.getInt("city_id"));
                rulerCities.add(rulerCity);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rulerCities;
    }

    public void updateCity(int id, City city) {
        String query = """
                update cities set name =?, population =?, square =? ,country_id=? where id =?;
                """;
        try (Connection connection = JDBC.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getPopulation());
            preparedStatement.setInt(3, city.getSquare());
            preparedStatement.setInt(4, city.getCountryId());
            preparedStatement.setInt(5, id);
            System.out.println("successfully updated");
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateCountry(int id, Country country) {
        String query = """
                update country set name =?, population =? where id =?;
                """;
        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getPopulation());
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
            System.out.println("successfully updated");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRulerCity(int id, RulerCity rulerCity) {
        String query = """
                update rulers_cities set name =?, last_name =?, age =?,city_id=? where id =?;
                """;
        try (Connection connection = JDBC.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rulerCity.getName());
            preparedStatement.setString(2, rulerCity.getLastName());
            preparedStatement.setInt(3, rulerCity.getAge());
            preparedStatement.setInt(4, rulerCity.getCityId());
            preparedStatement.setInt(5, id);
            preparedStatement.execute();
            System.out.println("successfully updated");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id) {
        String query = """
                delete from cities where id =?;
                """;
        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("successfully removed from the 'city' table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCountryById(int id) {
        String query = """
                delete from country where id =?;
                """;
        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("successfully removed from the 'country' table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRulerCityById(int id) {
        String query = """
                delete from rulers_cities where id =?;
                """;
        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("successfully removed from the 'rulers_cities' table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

