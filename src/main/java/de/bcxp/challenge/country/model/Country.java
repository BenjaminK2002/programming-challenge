package de.bcxp.challenge.country.model;

public class Country {
    public String name;
    public long population;
    public double area;

    public Country(String name, long population, double area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    public double getPopulationDensity() {
        if (area == 0) return 0; // Division durch Null verhindern
        return population / area;
    }
}