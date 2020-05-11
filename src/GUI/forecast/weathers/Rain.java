package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

/**
 * This class defines "rain" weather conditions.
 * @author Aaron Lam
 * @version 05-01-2020
 */
public class Rain extends Weather {
    public Rain() {
        super("forecast-images/rain.png");
        Map<String, WeatherCondition> rainCondition = new HashMap<>();
        rainCondition.put("humidity", new WeatherCondition(60, 100));
        rainCondition.put("rainfall", new WeatherCondition(20, 100));
        rainCondition.put("temperature", new WeatherCondition(30, 70));
        rainCondition.put("windSpeed", new WeatherCondition(20, 70));
        setWeatherConditions(rainCondition);
    }
}
