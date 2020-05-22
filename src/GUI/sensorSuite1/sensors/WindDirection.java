/*
 * WindDirection class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.sensors;

import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;

import java.io.File;
import java.util.TreeSet;

/**
 * @author Melinda Tran
 * @author Gregory Hablutzel
 * @version 1.0
 * This class is the Humidity Sensor class for the VantagePro2 Weather Station.
 * It generates Wind Direction data at a given interval in data packets, stores these in a
 * tree set, and serializes it to the console.
 */

public class WindDirection extends AbstractSensor<Integer> implements Runnable {

    private static final int max = 360; // 360
    private static final int min = 1; // 1
    /**
     * The type of sensor.
     **/
    private String sensorName = "Wind";
    /**
     * The type of measurement for this sensor.
     **/
    private String measurementDescription = "wind direction";

    /**
     * Constructs a RainfallRate object.
     *
     * @param file: output file.
     * @throws IllegalArgumentException if file is null
     */
    public WindDirection(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        dataSet = new TreeSet<DataPacket<Integer>>();
        this.file = file;
    }

    /**
     * Returns the wind direction, which is generated pseudo-randomly from 1 to 360 degrees.
     *
     * @return returns the wind direction.
     */
    public int calcWindDirection() {
        return rand.nextInt(max + 1 - min) + min; // [1, 360]

    }

    /**
     * Method that executes in Runnable thread.
     * Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
     */
    public void run() {
        super.run(dataSet, Controller.WINDDIRECTION_FILE, 0, calcWindDirection(), sensorName, measurementDescription);
    }
}
