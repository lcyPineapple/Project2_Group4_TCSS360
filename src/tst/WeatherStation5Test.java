package tst;

import GUI.WeatherStationIntegrater;
import GUI.WirelessConsole;
import GUI.iss8.data.WeatherData;
import GUI.weatherStations.WeatherStation5;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the methods of Weather Station 5.
 *
 * @author Leika Yamada
 * @version 5.15.2020
 */
class WeatherStation5Test {
    //private static Double[] testArray;
    //ADD moon phase Tests

    /**
     * This test ensures that the collectData method
     * correctly fills the arrays with data and updates
     * the WeatherStationIntergrater.
     */
    @Test
    void readDataFileTest() throws Exception {
        WirelessConsole myConsole = new WirelessConsole();
        WeatherStationIntegrater myIntegreater = new WeatherStationIntegrater(myConsole);
        WeatherStation5 myWeatherStation = new WeatherStation5(myIntegreater);
        assertEquals(true, myWeatherStation.readDataFile());
    }

    /**
     * This test ensures that the collectData method
     * correctly fills the arrays with data and updates
     * the WeatherStationIntergrater.
     */
    @Test
    void collectDataTest() throws Exception {
        WirelessConsole myConsole = new WirelessConsole();
        WeatherStationIntegrater myIntegreater = new WeatherStationIntegrater(myConsole);
        WeatherStation5 myWeatherStation = new WeatherStation5(myIntegreater);
        Double[] myArray = myWeatherStation.getSensorArray();
        myWeatherStation.collectData();

        ObjectInputStream in;
        WeatherData sensor8Data;
        try {
            in = new ObjectInputStream(new FileInputStream("weather.ser"));
            sensor8Data = (WeatherData) in.readObject();
            in.close();
            assertEquals(myArray[0], (double) sensor8Data.getHumidity());
        } catch (ClassNotFoundException e) {
            // print the error
            e.printStackTrace();
        } catch (IOException e) {
            // print the error
            e.printStackTrace();
        }
    }

    /**
     * This test checks that weather station 8 was
     * set up properly.
     *
     * @throws Exception
     */
    @Test
    void setUpStation8Test() throws Exception {
        WirelessConsole myConsole = new WirelessConsole();
        WeatherStationIntegrater myIntegreater = new WeatherStationIntegrater(myConsole);
        WeatherStation5 myWeatherStation = new WeatherStation5(myIntegreater);
        boolean myBool = myWeatherStation.setUpStation8();
        assertEquals(true, myBool);
    }

    /**
     * This tests that constructor of the WeatherStation5 class.
     */
    @Test
    void station5ConstructorTest() {
        WirelessConsole myConsole;
        try {
            myConsole = new WirelessConsole();
            WeatherStationIntegrater myIntegreater = new WeatherStationIntegrater(myConsole);
            WeatherStation5 testStation = new WeatherStation5(myIntegreater);
            Double[] myArray = testStation.getSensorArray();
            for (int i = 0; i < 4; i++) {
                assertEquals(null, myArray[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
