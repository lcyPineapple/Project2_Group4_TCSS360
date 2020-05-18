package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WeatherStationIntegrater {
    private WirelessConsole wirelessConsole;
    private List<List<Double>> weatherDataLists;
    private static final int INTERVAL = 2500;
    private static final int WEATHER_DATA_LIST_SIZE = 4;
    private Timer integraterTimer;

    public WeatherStationIntegrater() {
        this.weatherDataLists = new ArrayList<>();
        for (int i = 0; i < WEATHER_DATA_LIST_SIZE; i++) {
            weatherDataLists.add(new ArrayList<>());
        }
    }
    
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
        if (wirelessConsole == null) {
            return;
        }
        if (integraterTimer == null) {
            integraterTimer = new Timer();
            integraterTimer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    wirelessConsole.updateGUI();
                }
            }, 0, INTERVAL);
        }
    }

    /**
     * Append new data in weather data list.
     */
    public void setState(List<Double> weatherData) {
        /*
         * Prevent modification of weatherDataLists
         * while a copy of weatherDataLists is made
         */
        synchronized (this) {
            for (int i = 0; i < weatherData.size(); i++) {
                weatherDataLists.get(i).add(weatherData.get(i));
            }   
        }
    }

    /**
     * Setter of weather data lists.
     */
    public List<List<Double>> getWeatherDataListsCopy() {
        var copyList = new ArrayList<List<Double>>();
        /*
         * Prevent modification of weatherDataLists
         * while a copy of weatherDataLists is made
         */
        synchronized (this) {
            for (var subList : weatherDataLists) {
                var subListCopy = new ArrayList<Double>();
                for (var subListItem : subList) {
                    subListCopy.add(subListItem);
                }
                copyList.add(subListCopy);
            }
        }
        return copyList;
    }
}
