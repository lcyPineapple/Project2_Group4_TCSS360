package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

/**
 * This class defines "snow" weather conditions.
 *
 * @author Aaron Lam
 * @version 05-01-2020
 */
public class Snow extends Weather {
    public Snow() {
        super("forecast-images/snow.png");
        Map<String, WeatherCondition> snowCondition = new HashMap<>();
        snowCondition.put("humidity", new WeatherCondition(70, 100));
        snowCondition.put("rainfall", new WeatherCondition(10, 100));
        snowCondition.put("temperature", new WeatherCondition(0, 30));
        snowCondition.put("windSpeed", new WeatherCondition(20, 70));
        setWeatherConditions(snowCondition);
    }
}
