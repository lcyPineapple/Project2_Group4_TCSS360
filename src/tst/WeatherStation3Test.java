package tst;

import GUI.WeatherStationIntegrater;
import GUI.weatherStations.WeatherStation3;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherStation3Test {

    @Test
    public void testReadData() {
        WeatherStationIntegrater integrater = new WeatherStationIntegrater();
        WeatherStation3 weatherStation3 = new WeatherStation3(integrater);
        weatherStation3.readData();
        String fileName = "WDATA_" + String.join("_", (new Date()).toString().split(" ")) + ".txt";
        assertTrue(Files.isReadable(Path.of(fileName)));
    }
}
