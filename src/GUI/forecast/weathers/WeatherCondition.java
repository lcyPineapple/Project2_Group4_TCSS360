package GUI.forecast.weathers;

public class WeatherCondition {

    private int lowerBound;
    private int upperBound;

    public WeatherCondition(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public boolean isWithinRange(int value) {
        return value >= lowerBound && value <= upperBound;
    }
}
