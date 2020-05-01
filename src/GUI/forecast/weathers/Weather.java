package GUI.forecast.weathers;

import java.util.Map;

public abstract class Weather {
    private String imageFilePath;
    private Map<String, WeatherCondition> weatherConditions;

    public Weather(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setWeatherConditions(Map<String, WeatherCondition> weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public boolean isConditionMatch(Map<String, Integer> weatherData) {
        for (Map.Entry<String, Integer> weatherEntry : weatherData.entrySet()) {
            String dataField = weatherEntry.getKey();
            int dataValue = weatherEntry.getValue();
            WeatherCondition weatherCondition = weatherConditions.get(dataField);
            if (weatherCondition != null && !weatherCondition.isWithinRange(dataValue)) {
                return false;
            }
        }
        return true;
    }
}
