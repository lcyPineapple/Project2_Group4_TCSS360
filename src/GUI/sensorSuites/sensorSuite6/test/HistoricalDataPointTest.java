/*
 * A set of unit tests covering the historical data point
 * class.
 */
package GUI.sensorSuites.sensorSuite6.test;

import GUI.sensorSuites.sensorSuite6.src.WeatherData.DataType;
import GUI.sensorSuites.sensorSuite6.src.WeatherData.HistoricalDataPoint;
import GUI.sensorSuites.sensorSuite6.src.WeatherData.Sensor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Random;

/**
 * A set of unit tests covering the HistoricalDataPoint
 * class hierarchy.
 * @author Spencer Little
 * @version 0.0.0
 */
public class HistoricalDataPointTest {

    /** The random number generator used to create data points. */
    private final Random numGen = new Random();

    /**
     * Tests each instantiable data point specified in the DataTypes
     * enum by passing each to the respective testDaily/Weakly/Yearly
     * reading methods.
     */
    @Test
    public void testDataPoints() {
        for (DataType t : DataType.ALL_TYPES) {
            testDailyReading(HistoricalDataPoint.fromType(t, Sensor.OUTSIDE)); // inside and outside cases are identical
            testMonthlyReading(HistoricalDataPoint.fromType(t, Sensor.OUTSIDE));
            testYearlyReading(HistoricalDataPoint.fromType(t, Sensor.OUTSIDE));
        }
    }

    /**
     * Tests the tracking of daily high/low data points by generating
     * random values and manually incrementing the WeatherData object's
     * Calendar to simulate time passed.
     */
    public void testDailyReading(HistoricalDataPoint testCollector) {
        double max = Double.MIN_VALUE,  min = Double.MAX_VALUE;
        int day = testCollector.getCalendar().get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < numGen.nextInt(1000); i++) {
            double data = testCollector.getLowerBound()
                    + (testCollector.getUpperBound() - testCollector.getLowerBound()) * numGen.nextDouble();
            if (day != testCollector.getCalendar().get(Calendar.DAY_OF_YEAR)) { // if day changes, reset max, min
                max = data;
                min = data;
                day = testCollector.getCalendar().get(Calendar.DAY_OF_YEAR);
            } else {
                max = Math.max(data, max);
                min = Math.min(data, min);
            }
            testCollector.addDataPoint(data);
            testCollector.addToCal(Calendar.HOUR, 1); // + 1 hour
        }
        Assert.assertEquals(max, testCollector.getDailyHigh(), 0f);
        Assert.assertEquals(min, testCollector.getDailyLow(), 0f);
    }

    /**
     * Tests the tracking of monthly high/low data points by generating
     * random values and manually incrementing the WeatherData object's
     * Calendar to simulate time passed.
     */
    public void testMonthlyReading(HistoricalDataPoint testCollector) {
        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        int month = testCollector.getCalendar().get(Calendar.MONTH);
        for (int i = 0; i < numGen.nextInt(1000); i++) {
            double data = testCollector.getLowerBound()
                    + (testCollector.getUpperBound() - testCollector.getLowerBound()) * numGen.nextDouble();
            if (month != testCollector.getCalendar().get(Calendar.MONTH)) { // if month changes, reset max, min
                max = data;
                min = data;
                month = testCollector.getCalendar().get(Calendar.MONTH);
            } else {
                max = Math.max(data, max);
                min = Math.min(data, min);
            }
            testCollector.addDataPoint(data);
            testCollector.addToCal(Calendar.DAY_OF_YEAR, 1); // + 1 day
        }
        Assert.assertEquals(max, testCollector.getMonthlyHigh(), 0f);
        Assert.assertEquals(min, testCollector.getMonthlyLow(), 0f);
    }

    /**
     * Tests the tracking of yearly high/low data points by generating
     * random values and manually incrementing the WeatherData object's
     * Calendar to simulate time passed.
     */
    public void testYearlyReading(HistoricalDataPoint testCollector) {
        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        int year = testCollector.getCalendar().get(Calendar.YEAR);
        for (int i = 0; i < numGen.nextInt(1000); i++) {
            double data = testCollector.getLowerBound()
                    + (testCollector.getUpperBound() - testCollector.getLowerBound()) * numGen.nextDouble();
            if (year != testCollector.getCalendar().get(Calendar.YEAR)) { // if year changes, reset max, min
                max = data;
                min = data;
                year = testCollector.getCalendar().get(Calendar.YEAR);
            } else {
                max = Math.max(data, max);
                min = Math.min(data, min);
            }
            testCollector.addDataPoint(data);
            testCollector.addToCal(Calendar.MONTH, 1); // + 1 month
        }
        Assert.assertEquals(max, testCollector.getYearlyHigh(), 0f);
        Assert.assertEquals(min, testCollector.getYearlyLow(), 0f);
    }
}
