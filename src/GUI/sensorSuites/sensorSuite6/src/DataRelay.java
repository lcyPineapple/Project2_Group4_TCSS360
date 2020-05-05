package GUI.sensorSuites.sensorSuite6.src;/*
 * An object that manages data ingest and output.
 */



import GUI.sensorSuites.sensorSuite6.src.WeatherData.DataType;
import GUI.sensorSuites.sensorSuite6.src.WeatherData.HistoricalDataPoint;
import GUI.sensorSuites.sensorSuite6.src.WeatherData.Sensor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Accepts data from the various sensors, aggregates,
 * and writes data to a text file after @code{writeThreshold}
 * data points have been added.
 * @author Spencer Little
 * @version 0.0.0
 */
public class DataRelay implements Serializable {

    /** The set of data point objects that collect incoming data. */
    private final List<HistoricalDataPoint> aggregators = new LinkedList<>();
    /** The number of data points between transmission/writing. */
    private final int IO_RATE = 1000;
    /** The base of the url to which weather data will be written (time stamp will be added). */
    private final String BASE_URL = "WDATA_";
    /** The url corresponding to the most recent writing of the txt file. */
    private String lastUrl;
    /** Tracks the number of data points that have been processed. */
    private int processed = 0;

    /**
     * Creates a generic DataRelay capable of processing all available
     * types of sensor data. Assumes all sensors are of type OUTSIDE.
     */
    public DataRelay() {
        for (int i = 0; i < DataType.ALL_TYPES.length; i++) {
            aggregators.add(HistoricalDataPoint.fromType(DataType.ALL_TYPES[i], Sensor.OUTSIDE));
        }
    }

    public DataRelay(GregorianCalendar cal) {
        this();
        Objects.requireNonNull(cal, "Initializing Calendar cannot be null.");
        for (HistoricalDataPoint dp : aggregators) {
            dp.setCalendar(cal);
        }
    }

    /**
     * Creates a DataRelay with the provided list of data point objects.
     * @param dataPoints a variable number, or array, of HistoricalDataPoint
     *                   objects.
     */
    public DataRelay(HistoricalDataPoint... dataPoints) {
        if (dataPoints.length < 1) throw new IllegalArgumentException("DataRelay requires at least one data point.");
        aggregators.addAll(Arrays.asList(dataPoints));
    }

    /**
     * Constructs a new DataRelay for the specified list of
     * data types.
     * @param types the list of data types supported by this relay
     * @param s the type of sensor this relay supports
     */
    public DataRelay(DataType[] types, Sensor s) {
        for (int i = 0; i < types.length; i++) {
            aggregators.add(HistoricalDataPoint.fromType(types[i], s));
        }
    }

    /**
     * Returns the list of DataPoints maintained by this object.
     * @return a LinkedList of DataPoints maintained by this object
     */
    public List<HistoricalDataPoint> getDataPoints() { return aggregators; }

    /**
     * Increments the Calendar of each data point object in this DataRelay.
     * @param field the calendar field to increment (a constant from Calendar, eg. Calendar.YEAR)
     * @param amount the amount to increment
     */
    public void incrementCal(int field, int amount) {
        for (HistoricalDataPoint dp : aggregators) {
            dp.addToCal(field, amount);
        }
    }

    /**
     * Returns the url of the most recent .txt snapshot. See
     * @{code writeData()}.
     * @return the url corresponding to the most recent .txt snapshot
     */
    public String getLastUrl() { return lastUrl; }


    /**
     * Accepts a data point from a sensor, adding it to the respective
     * DataPoint object.
     * @param point the value of the data point
     * @param type the type of data point
     */
    public void acceptDataPoint(double point, DataType type) {
        for (HistoricalDataPoint p : aggregators) {
            if (p.getType() == type) p.addDataPoint(point);
        }
        if (++processed > IO_RATE) {
            processed = 0;
            writeData();
        }
    }

    /**
     * Writes the data in each file object to a unified text file.
     */
    private void writeData() {
        String timeStamp = String.join("_", (new Date()).toString().split(" "));
        StringBuilder data = new StringBuilder();
        for (HistoricalDataPoint dp : aggregators) {
            data.append(dp.getType().toString() + " (" + dp.getUnit() + ") ");
            data.append("\nDaily high/low ").append(dp.getDailyHigh() + " ").append(dp.getDailyLow());
            data.append("\nMonthly high/low ").append(dp.getMonthlyHigh() + " ").append(dp.getMonthlyLow());
            data.append("\nYearly high/low ").append(dp.getYearlyHigh() + " ").append(dp.getYearlyLow());
            data.append("\nHourly readings (past hour) ");
            for (double p : dp.getHourlyReadings()) {
                data.append(p + " ");
            }
            data.append("\nMost recent readings ");
            for (double p : dp.getAllReadings()) {
                data.append(p + " ");
            }
            data.append("\n-----------------------------------------------------------------\n");
        }

        try {
            lastUrl = BASE_URL + timeStamp + ".txt";
            FileOutputStream out = new FileOutputStream(BASE_URL + timeStamp + ".txt");
            out.write(data.toString().getBytes());
        } catch (FileNotFoundException fne) {
            System.out.println("Unable to create output file. Are permissions correct?");
        } catch (IOException iox) {
            System.out.println("Error occurred while writing to data file.");
        }
    }


/**
 * Compares this DataRelay with another by comparing
 * the list of data points.
 * @param o the DataRelay to compare against
 * @return a boolean indicating whether this == o
 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataRelay or = (DataRelay) o;
        return aggregators.equals(or.aggregators);
    }
}