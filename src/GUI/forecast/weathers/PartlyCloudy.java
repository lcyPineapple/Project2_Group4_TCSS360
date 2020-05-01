package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

/**
 * This class defines "partly cloudy" weather conditions.
 * @author Aaron Lam
 * @version 05-01-2020
 */
public class PartlyCloudy extends Weather {
    public PartlyCloudy() {
        super("forecast-images/partly-cloudy.png");
        Map<String, WeatherCondition> partlyCloudyCondition = new HashMap<>();
        partlyCloudyCondition.put("humidity", new WeatherCondition(0, 100));
        partlyCloudyCondition.put("rainfall", new WeatherCondition(0, 100));
        partlyCloudyCondition.put("temperature", new WeatherCondition(0, 100));
        partlyCloudyCondition.put("windSpeed", new WeatherCondition(0, 100));
        setWeatherConditions(partlyCloudyCondition);
    }
}
