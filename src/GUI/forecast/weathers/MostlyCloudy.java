package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

/**
 * This class defines "mostly cloudy" weather conditions.
 * @author Aaron Lam
 * @version 05-01-2020
 */
public class MostlyCloudy extends Weather {
    public MostlyCloudy() {
        super("forecast-images/mostly-cloudy.png");
        Map<String, WeatherCondition> mostlyCloudyCondition = new HashMap<>();
        mostlyCloudyCondition.put("humidity", new WeatherCondition(30, 60));
        mostlyCloudyCondition.put("rainfall", new WeatherCondition(0, 0));
        mostlyCloudyCondition.put("temperature", new WeatherCondition(40, 100));
        mostlyCloudyCondition.put("windSpeed", new WeatherCondition(30, 50));
        setWeatherConditions(mostlyCloudyCondition);
    }
}
