package org.example.service.impl;

import org.example.config.JDBC;
import org.example.model.Country;
import org.example.service.CountryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryServiceImpl implements CountryService {
    @Override
    public void createTable() {
        String query = """
                    create table country(
                   id serial  primary key ,
                   name varchar(50)not null ,
                   population integer not null
                   );
                     
                   """;

        try (Connection connection = JDBC.getConnection()) {

            Statement statement = connection.createStatement();

            statement.execute(query);

            System.out.println("Table country successfully created!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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
}
