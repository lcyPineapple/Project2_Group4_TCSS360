package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

public class Rain extends Weather {
    public Rain() {
        super("forecast-images/rain.png");
        Map<String, WeatherCondition> rainCondition = new HashMap<>();
        rainCondition.put("humidity", new WeatherCondition(0, 100));
        rainCondition.put("rainfall", new WeatherCondition(0, 100));
        rainCondition.put("temperature", new WeatherCondition(0, 100));
        rainCondition.put("windSpeed", new WeatherCondition(0, 100));
        setWeatherConditions(rainCondition);
    }
}
