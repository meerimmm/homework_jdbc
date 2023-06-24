package org.example.service.impl;

import org.example.config.JDBC;
import org.example.model.RulerCity;
import org.example.service.RulerCityService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RulerCityServiceImpl implements RulerCityService {
    @Override
    public void createTable() {
        String query = """
               
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

            System.out.println("Table rules_cities successfully created!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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
