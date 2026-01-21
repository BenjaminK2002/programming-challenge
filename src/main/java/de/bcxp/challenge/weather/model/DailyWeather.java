package de.bcxp.challenge.weather.model;

public class DailyWeather {
    public int day;
    public double maxTemp;
    public double minTemp;

    public DailyWeather(int day, double maxTemp, double minTemp){
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public double getSpread() {
        return maxTemp - minTemp;
    }
}