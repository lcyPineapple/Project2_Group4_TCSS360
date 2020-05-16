package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import GUI.WeatherStationIntegrater;
import GUI.weatherStations.WeatherStation1;

class WeatherStation1Tests {    
    @Test
    void noExceptionsOnGettingComputationsWithAbsentData() {
        try {
            var ws = new WeatherStation1(null);
            // Test dew point and heat index
            
            // get data when no temperature data or humidity data
            //    yet exist
            ws.getDewPoint();
            ws.getHeatIndex();
            
            ws = new WeatherStation1(null);
            ws.getTemp();
            // get data when no humidity data exists
            ws.getDewPoint();
            ws.getHeatIndex();
            
            ws = new WeatherStation1(null);
            ws.getHumidity();
            // get data when no temperature data exists
            ws.getDewPoint();
            ws.getHeatIndex();
            
            // Test wind chill
            ws = new WeatherStation1(null);
            // get data when no temperature or wind speed data exists
            ws.getWindChill();
            
            ws = new WeatherStation1(null);
            ws.getTemp();
            // get data when no wind speed data exists
            ws.getWindChill();
            
            ws = new WeatherStation1(null);
            ws.getWindSpeed();
            // get data when no temperature data exists
            ws.getWindChill();
            
            // Test rainfall rate
            ws = new WeatherStation1(null);
            // get data when no rainfall data exists
            ws.getRainfallRate();
            
            ws = new WeatherStation1(null);
            ws.getRain();
            ws.getRain();
            // get data when not enough rainfall data exists
            ws.getRainfallRate();
        } catch (Exception e) {
            fail("An exception occured in one of the computations");
        }
    }
    
    @Test
    void noExceptionsOnRunning() {
        var ws = new WeatherStation1(null);
        ws.setConnected(false);
        var tA = System.currentTimeMillis();
        ws.run();
        while (System.currentTimeMillis() - tA < 10_000) {
            // wait 10 seconds.
        }
        
        // attempt getting some of the computations
        ws.getWindChill();
        ws.getHeatIndex();
        ws.getDewPoint();
        ws.getRainfallRate();
    }
}