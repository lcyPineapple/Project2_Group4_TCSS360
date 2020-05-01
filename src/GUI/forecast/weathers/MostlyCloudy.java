package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

public class MostlyCloudy extends Weather {
    public MostlyCloudy() {
        super("forecast-images/mostly-cloudy.png");
        Map<String, WeatherCondition> mostlyCloudyCondition = new HashMap<>();
        mostlyCloudyCondition.put("humidity", new WeatherCondition(0, 100));
        mostlyCloudyCondition.put("rainfall", new WeatherCondition(0, 100));
        mostlyCloudyCondition.put("temperature", new WeatherCondition(0, 100));
        mostlyCloudyCondition.put("windSpeed", new WeatherCondition(0, 100));
        setWeatherConditions(mostlyCloudyCondition);
    }
}
