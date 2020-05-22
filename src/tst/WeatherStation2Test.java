package tst;

import GUI.WeatherStationIntegrater;
import GUI.WirelessConsole;
import GUI.weatherStations.WeatherStation;
import GUI.weatherStations.WeatherStation2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
