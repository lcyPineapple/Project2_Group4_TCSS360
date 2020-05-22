/*
 * TemperatureSensor class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.sensors;

import GUI.sensorSuite1.controller.DataPacket;

import java.io.File;
import java.util.TreeSet;

/**
 * @author Daniel Machen
 * @author Gregory Hablutzel
 * @version 1.0
 * This class is the Temperature Sensor class for the VantagePro2 Weather Station.
 * It generates temperature data at a given interval in data packets, stores these in a
 * tree set, and serializes it to the console.
 */

public class TemperatureSensor extends AbstractSensor<Double> implements Runnable {


    private static final int maxTemp = 1500; // 150.0F
    private static final int minTemp = -400; // -40.0F
    private String sensorName = "Temperature";
    private String measurementDescription = "temperature";

    /**
     * Constructs a RainfallRate object.
     *
     * @param file: output file.
     * @throws IllegalArgumentException if file is null
     */
    public TemperatureSensor(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        dataSet = new TreeSet<DataPacket<Double>>();
        this.file = file;
    }

    /**
     * generates the temperature data between -40 and 150.
     *
     * @return returns temperature data.
     */
    public double getTemp() {
        return (rand.nextInt(maxTemp + 1 - minTemp) + minTemp) / 10.0; // [-40.0, 150.0]

    }

    /**
     * Method that executes in Runnable thread.
     * Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
     */
    public void run() {
        super.run(dataSet, file, 0.0, getTemp(), sensorName, measurementDescription);
    }
}