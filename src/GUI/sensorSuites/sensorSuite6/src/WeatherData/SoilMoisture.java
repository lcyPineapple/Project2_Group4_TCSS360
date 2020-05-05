/*
 * A data point that tracks soil moisture.
 */

package weatherstation6.src.WeatherData;

/**
 * The soil moisture data processing model which
 * measures highs and lows. 
 * @author Deline Zent
 */
public class SoilMoisture extends HistoricalDataPoint {

    /** The data type (category) that describes this object. */
    private final DataType dataType = DataType.SOIL_MOISTURE;
    /** The unit in which this data point represents values. */
    private final String unit = "cb";
    /** A serial number for the class. */
    private static final long serialVersionUID = -6927110504185143175L;
    /** The upper bound of the acceptable input range in cb. */
    private final int rangeHigh = 200;
    /** The lower bound of the acceptable input range in cb. */
    private final int rangeLow = 0;
    
    /**
     * Adds a data point to the historical data set,
     * ignoring points that are out of the appropriate
     * range.
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
     * @return a string representation of the unit in which this object
     *         interprets data
     */
    @Override
    public String getUnit() {
        return unit;
    }

    /**
     * Returns this objects data type.
     * @return an enum specifying the type of data this object represents
     */
    @Override
    public DataType getType() {
        return dataType;
    }

    /**
     * Returns the upper bound of the range of acceptable
     * values for this data point.
     * @return a double representing the upper bound of acceptable
     *         values for this data point
     */
    @Override
    public double getUpperBound() {
        return rangeHigh;
    }

    /**
     * Returns the lower bound of the range of acceptable
     * values for this data point.
     * @return a double representing the lower bound of acceptable
     *         values for this data point
     */
    @Override
    public double getLowerBound() {
        return rangeLow;
    }

}
