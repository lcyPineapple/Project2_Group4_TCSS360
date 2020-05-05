/*
 * A data point that tracks ultra violet data points.
 */

package weatherstation6.src.WeatherData;

import java.util.Objects;

/**
 * Collects and processes ultraVoilet data from the
 * respective sensors.
 * @author Paras Sharma
 * @version 0.01
 */
public class UltraViolet extends HistoricalDataPoint {

    /** The data type (category) that describes this object. */
    private final DataType dataType = DataType.ULTRAVIOLET;
    /** The sensor type providing this instance with data. */
    private Sensor sensor;
    /** The unit in which this data point is interpreting values. */
    private final String unit = "MEDs";
    /** Range the lower bound accepts. */
    private final int rangeLow = 0;
    /** Range the upper bound accepts. */
    private final int rangeHigh = 199; // MEDs
    /** Alarm lowRange. */
    private final int alarmRangeLow = 0;
    /** Alarm highRange. */
    private final double alarmRangeHigh = 19.9; // MEDs


    /**
     * Constructs a new WeatherData.UltraViolet data processing
     * instance for the specified sensor.
     *
     * @param s the type of sensor that will send this
     *            object data (inside,
     */
    public UltraViolet(Sensor s) {
        Objects.requireNonNull(s, "WeatherData.Sensor type cannot be null.");
        this.sensor = s;
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
        if (point <= rangeHigh && point >= rangeLow && sensor != Sensor.EXTRA)
            super.addDataPoint(point);
        else if (point <= rangeHigh && point >= rangeLow && sensor == Sensor.EXTRA)
            super.addExternalDataPoint(point);
    }

    /**
     * Throws a warning on display if the MEDs go high.
     *
     * @param point, data that will compared with range
     * @return warning String
     */
    public String alarmWarning(double point) {
        double point1 = point;
        StringBuilder sb = new StringBuilder();
        if (point1 >= alarmRangeHigh) {
            sb.append("WARNING!, UV dosage is HIGH");
        } else if (point1 == alarmRangeLow) {
            sb.append("No protection needed");
        }

        return sb.toString();
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