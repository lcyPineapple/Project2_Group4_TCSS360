/*
 * A data point that tracks rail rate.
 */

package GUI.sensorSuite6.src.WeatherData;

import java.util.Objects;

/**
 * The rain rate data processing model, accepting
 * rain rate metrics from its sensor.
 *
 * @author Deline Zent
 */
public class RainRate extends HistoricalDataPoint {

    /**
     * A serial number for this class.
     */
    private static final long serialVersionUID = 4427698316186989617L;
    /**
     * The data type (category) that describes this object.
     */
    private final DataType dataType = DataType.RAIN_RATE;
    /**
     * The upper bound of the acceptable input range.
     */
    private final double rangeHigh;
    /**
     * The lower bound of the acceptable input range.
     */
    private final int rangeLow = 0;
    /**
     * The scale in which this data point is interpreting values.
     */
    private final length scale;

    /**
     * Constructs a new WeatherData.RainRate data processing
     * instance for the specified measuring unit.
     *
     * @param theLength is the unit of measurement
     */
    public RainRate(length theLength) {
        scale = Objects.requireNonNull(theLength, "Length scale cannot be null.");
        if (theLength == length.INCHES) {
            rangeHigh = 0.04;
        } else {
            rangeHigh = 1;
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
        if (point <= rangeHigh && point >= rangeLow)
            super.addDataPoint(point);
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
        if (scale == length.INCHES)
            return "inches";
        else
            return "millimeters";
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
     * The user specified measuring options.
     */
    public enum length {INCHES, MILLIMETERS}

}
