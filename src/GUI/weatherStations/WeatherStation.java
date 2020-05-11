package GUI.weatherStations;

import GUI.WeatherStationIntegrater;

import java.util.List;

public abstract class WeatherStation {
    private WeatherStationIntegrater integrater;
    private boolean isConnected;

    public WeatherStation(WeatherStationIntegrater integrater) {
        this.integrater = integrater;
        this.isConnected = true;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    /**
     * Each time sensor suite gets new data, call this method to notify weather station.
     * Therefore, weather station could send the updated data to integrater.
     */
    public void update(List<Double> weatherData) {
        if (isConnected) {
            this.integrater.setState(weatherData);
        }
    }

    /**
     * Start the sensor suite.
     * @throws Exception 
     */
    public abstract void run() throws Exception;
}
