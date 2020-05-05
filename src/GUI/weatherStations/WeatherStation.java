package GUI;

public abstract class WeatherStation {
    private WeatherStationIntegrater integrater;
    private boolean isConnected;

    public WeatherStation(WeatherStationIntegrater integrater) {
        this.integrater = integrater;
        this.isConnected = false;
    }



    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
