package org.example.service.impl;

import org.example.config.JDBC;
import org.example.model.City;
import org.example.service.CityService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityServiceImpl implements CityService {
    public void createTable() {
        String query = """
                   create  table cities(
                   id serial  primary key ,
                   name varchar(50) not null,
                   population integer not null,
                   square integer not null ,
                   country_id integer references country(id)
                );     
                   """;

        try (Connection connection = JDBC.getConnection()) {

            Statement statement = connection.createStatement();

            statement.execute(query);

            System.out.println("Table cities successfully created!");

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

    @Override
    public void deleteByCityId(int id) {
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
}

