/*
 * RainfallRate class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.computations;

import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.sensors.AbstractSensor;

import java.io.File;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author Gregory Hablutzel
 * @version 1.0
 * This class represents the Rainfall Rate calculation for the VantagePro2 Weather Station.
 * RainfallRate calculates the rainfall rate given the past 60 seconds of rainfall data.
 */

public class RainfallRate extends AbstractSensor<Double> implements Runnable {
    private static final int maxVal = 30; // 30"/hr
    private static final double minVal = 0.04; // 0.04"/hr
    TreeSet<DataPacket<Double>> rainDataSet;
    private String sensor = "Rain";
    private String measurementString = "rain rate";

    /**
     * Constructs a RainfallRate object.
     *
     * @param file:        output file.
     * @param rainDataSet: input rain data.
     * @throws IllegalArgumentException if file is null
     * @throws IllegalArgumentException if tempDataSet is null
     * @throws IllegalArgumentException if humidityDataaSet is null
     */
    public RainfallRate(File file, TreeSet<DataPacket<Double>> rainDataSet) {
        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        if (rainDataSet == null) {
            throw new IllegalArgumentException("input set cannot be null");
        }
        dataSet = new TreeSet<DataPacket<Double>>();
        this.file = file;
        this.rainDataSet = rainDataSet;
    }

    /**
     * Calculates the rain rate (average) given the past 60 seconds of rain data (last 3 data points).
     * (each data point is 20 seconds apart).
     *
     * @return returns the rain rate.
     * @throws IllegalArgumentException if there is not 3 data points to analyze.
     */
    public double calcRainRate() {
        // 0 or [0.4, 30]
        if (rainDataSet.size() < 3) {
            throw new IllegalArgumentException("rain rate input set too small!");
        }
        double sum = 0;
        Iterator<DataPacket<Double>> itr = rainDataSet.descendingIterator();
        int i = 1;
        while (itr.hasNext() && i <= 3) {
            DataPacket<Double> dp = itr.next();
            sum += dp.getValue();
            i++;
        }

        double ave = sum / 3.0;

        double rate = ave / 20.0 * 3600.0; // have "/20 sec, want "/hr

        rate = round(rate, 2);

        if (rate < minVal) {
            rate = 0;
        }
        if (rate > maxVal) {
            rate = maxVal;
        }
        return rate;
    }

    /**
     * Method that executes in Runnable thread.
     * Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
     */
    public void run() {
        super.run(dataSet, Controller.RAINRATE_FILE, 0.0, calcRainRate(), sensor, measurementString);
    }
}
