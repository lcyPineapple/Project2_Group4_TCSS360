/*
 * The temperature class manages sensor input from the
 * temperature sensor, providing historical metrics.
 */

package GUI.sensorSuite6.src.WeatherData;

import java.util.Objects;

/**
 * The temperature class processes input from the
 * temperature sensor and provides historical metrics.
 *
 * @author Spencer Little
 * @version 0.0.0
 */
public class Temperature extends HistoricalDataPoint {

    /**
     * The data type (category) that describes this object.
     */
    private final DataType dataType = DataType.TEMPERATURE;
    /**
     * The scale in which this data point is representing values.
     */
    private final Temp scale;
    /**
     * The sensor type providing this instance with data.
     */
    private Sensor sensorType;
    /**
     * The upper bound of the acceptable input range.
     */
    private double rangeHigh;
    /**
     * The lower bound of the acceptable input range.
     */
    private double rangeLow;
    /**
     * Constructs a new WeatherData.Temperature data processing
     * instance for the specified scale and sensor.
     *
     * @param sns the type of sensor that will send this
     *            object data (inside, outside, or extra)
     * @param tmp the desired temperature scale (C or F)
     */
    public Temperature(Sensor sns, Temp tmp) {
        Objects.requireNonNull(sns, "Sensor type cannot be null.");
        scale = Objects.requireNonNull(tmp, "Temperature scale cannot be null.");
        sensorType = sns;
        if (sns == Sensor.INSIDE) {
            rangeHigh = tmp == Temp.CELCIUS ? 60 : 140;
            rangeLow = tmp == Temp.CELCIUS ? 0 : 32;
        } else {
            rangeHigh = tmp == Temp.CELCIUS ? 65 : 150;
            rangeLow = -40;
        }
    }

    /**
     * Adds a data point to the historical data set,
     * ignoring points that are out of the appropriate
     * range.
     *
     * @param point the data point to be added.
     */
    @Override
    public void addDataPoint(double point) {
        if (point <= rangeHigh && point >= rangeLow && sensorType != Sensor.EXTRA)
            super.addDataPoint(point);
        else if (point <= rangeHigh && point >= rangeLow && sensorType == Sensor.EXTRA)
            super.addExternalDataPoint(point);
    }

    /**
     * Gets a string description of the unit in which
     * this object is interpreting data.
     *
     * @return a string representation of the unit in which this object
     * interprets data
     */
    @Override
    public String getUnit() {
        if (scale == Temp.CELCIUS)
            return "celcius";
        else
            return "fahrenheit";
    }

    /**
     * Returns this objects data type.
     *
     * @return an enum specifying the type of data this object represents
     */
    @Override
    public DataType getType() {
        return dataType;
    }

    /**
     * Returns the upper bound of the range of acceptable
     * values for this data point.
     *
     * @return a double representing the upper bound of acceptable
     * values for this data point
     */
    @Override
    public double getUpperBound() {
        return rangeHigh;
    }

    /**
     * Returns the lower bound of the range of acceptable
     * values for this data point.
     *
     * @return a double representing the lower bound of acceptable
     * values for this data point
     */
    @Override
    public double getLowerBound() {
        return rangeLow;
    }

    /**
     * The temperature paradigm used to measure data.
     */
    public enum Temp {CELCIUS, FAHRENHEIT}
}
