package GUI.forecast.weathers;

/**
 * Weather Condition stores weather condition's data range.
 *
 * @author Aaron Lam
 * @version 05-01-2020
 */
public class WeatherCondition {

    private int lowerBound;
    private int upperBound;

    public WeatherCondition(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Check whether the value is in weather condition's data range.
     */
    public boolean isWithinRange(double value) {
        return value >= lowerBound && value <= upperBound;
    }
}
