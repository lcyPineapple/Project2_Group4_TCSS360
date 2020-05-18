import GUI.WeatherStationIntegrater;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherStationIntegraterTest {

    @Test
    void testIntegrater() {
        WeatherStationIntegrater integrater = new WeatherStationIntegrater();
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(1.0);
        }
        integrater.setState(list);
        List<List<Double>> weatherDataLists = integrater.getWeatherDataListsCopy();
        for (List<Double> weatherDataList : weatherDataLists) {
            assertEquals(weatherDataList.get(0), 1);
        }
    }
}
