package GUI;

import java.util.ArrayList;
import java.util.List;

public class WeatherStationIntegrater {
    private WirelessConsole wirelessConsole;
    private List<List<Double>> weatherDataLists;
    private static final int INTERVAL = 2500;
    private static final int WEATHER_DATA_LIST_SIZE = 5;

    public WeatherStationIntegrater(WirelessConsole wirelessConsole) {
        this.wirelessConsole = wirelessConsole;
        this.weatherDataLists = new ArrayList<>();
        for (int i = 0; i < WEATHER_DATA_LIST_SIZE; i++) {
            weatherDataLists.add(new ArrayList<>());
        }
    }

    /**
     * Integrater will update views on wireless console every 2.5 seconds
     */
    public void run() {
        Thread integraterThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(INTERVAL);
                    wirelessConsole.updateGUI();
                }
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        });
        integraterThread.start();
    }

    /**
     * Append new data in weather data list.
     */
    public void setState(List<Double> weatherData) {
        for (int i = 0; i < weatherData.size(); i++) {
            weatherDataLists.get(i).add(weatherData.get(i));
        }
    }

    /**
     * Setter of weather data lists.
     */
    public List<List<Double>> getWeatherDataLists() {
        return weatherDataLists;
    }
}
