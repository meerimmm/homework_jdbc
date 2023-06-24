package org.example.model;

public class City {
    private int id;
    private String name;
    private int population;
    private int square;
   private int countryId;

    public City() {
    }

    public City(String name, int population, int square, int countryId) {
        this.name = name;
        this.population = population;
        this.square = square;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", square=" + square +
                ", countryId=" + countryId +
                '}';
    }
}
