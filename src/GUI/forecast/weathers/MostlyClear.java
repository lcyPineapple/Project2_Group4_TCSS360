package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

public class MostlyClear extends Weather {
    public MostlyClear() {
        super("forecast-images/mostly-clear.png");
        Map<String, WeatherCondition> mostlyClearCondition = new HashMap<>();
        mostlyClearCondition.put("humidity", new WeatherCondition(0, 100));
        mostlyClearCondition.put("rainfall", new WeatherCondition(0, 100));
        mostlyClearCondition.put("temperature", new WeatherCondition(0, 100));
        mostlyClearCondition.put("windSpeed", new WeatherCondition(0, 100));
        setWeatherConditions(mostlyClearCondition);
    }
}
