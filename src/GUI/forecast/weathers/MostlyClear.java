package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

/**
 * This class defines "mostly clear" weather conditions.
 * @author Aaron Lam
 * @version 05-01-2020
 */
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
