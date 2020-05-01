package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

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
