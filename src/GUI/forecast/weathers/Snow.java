package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

public class Snow extends Weather {
    public Snow() {
        super("forecast-images/snow.png");
        Map<String, WeatherCondition> snowCondition = new HashMap<>();
        snowCondition.put("humidity", new WeatherCondition(0, 100));
        snowCondition.put("rainfall", new WeatherCondition(0, 100));
        snowCondition.put("temperature", new WeatherCondition(0, 100));
        snowCondition.put("windSpeed", new WeatherCondition(0, 100));
        setWeatherConditions(snowCondition);
    }
}
