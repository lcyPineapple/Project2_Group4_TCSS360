package GUI.forecast.weathers;

import java.util.HashMap;
import java.util.Map;

/**
 * This class defines "mostly clear" weather conditions.
 *
 * @author Aaron Lam
 * @version 05-01-2020
 */
public class MostlyClear extends Weather {
    public MostlyClear() {
        super("forecast-images/mostly-clear.png");
        Map<String, WeatherCondition> mostlyClearCondition = new HashMap<>();
        mostlyClearCondition.put("humidity", new WeatherCondition(20, 40));
        mostlyClearCondition.put("rainfall", new WeatherCondition(0, 0));
        mostlyClearCondition.put("temperature", new WeatherCondition(50, 80));
        mostlyClearCondition.put("windSpeed", new WeatherCondition(30, 50));
        setWeatherConditions(mostlyClearCondition);
    }
}
