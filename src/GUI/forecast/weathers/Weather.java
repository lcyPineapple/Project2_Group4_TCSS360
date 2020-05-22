package GUI.forecast.weathers;

import java.util.Map;

/**
 * Weather is an abstract class which contains weather information and its conditions logic.
 *
 * @author Aaron Lam
 * @version 05-01-2020
 */
public abstract class Weather {
    private String imageFilePath;
    private Map<String, WeatherCondition> weatherConditions;

    public Weather(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    /**
     * @return weather image file path.
     */
    public String getImageFilePath() {
        return imageFilePath;
    }

    /**
     * Setter of weather condition.
     *
     * @param weatherConditions
     */
    public void setWeatherConditions(Map<String, WeatherCondition> weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    /**
     * Iterate weather data and match them with weather conditions.
     * If all weather conditions are matched, return true.
     */
    public boolean isConditionMatch(Map<String, Double> weatherData) {
        for (Map.Entry<String, Double> weatherEntry : weatherData.entrySet()) {
            String dataField = weatherEntry.getKey();
            double dataValue = weatherEntry.getValue();
            WeatherCondition weatherCondition = weatherConditions.get(dataField);
            if (weatherCondition != null && !weatherCondition.isWithinRange(dataValue)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Weather.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Weather other = (Weather) obj;
        return getImageFilePath().equals(other.getImageFilePath());
    }
}
