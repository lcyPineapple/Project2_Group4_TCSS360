package tst;

import GUI.WeatherStationIntegrater;
import GUI.WirelessConsole;
import GUI.forecast.Forecast;
import GUI.forecast.weathers.*;
import GUI.iss7.sensorSuite.SensorSuite;
import GUI.weatherStations.WeatherStation;
import GUI.weatherStations.WeatherStation2;
import GUI.weatherStations.WeatherStation4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeatherStation2Test {
    WirelessConsole c;
    WeatherStationIntegrater in;
    WeatherStation ws;

    @BeforeEach
    void setUp() throws Exception {
        c = new WirelessConsole();
        in = new WeatherStationIntegrater(c);
        ws = new WeatherStation2(in);
    }

    @Test
    void testStart() throws Exception {
        ws.run();
        Thread.sleep(20 * 1000);
        ((WeatherStation2) ws).kill();
    }
}
