/*
 * HumiditySensor class for Weather Station TCSS 360
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
 * @author Egor Maksimenka
 * @author Gregory Hablutzel
 * @version 1.0
 * This class is the Humidity Sensor class for the VantagePro2 Weather Station.
 * It generates humidity data at a given interval in data packets, stores these in a
 * tree set, and serializes it to the console.
 */

public class HumiditySensor extends AbstractSensor<Integer> implements Runnable {

    private static final int MIN_HUMIDITY = 1;
    private static final int MAX_HUMIDITY = 100;
    private String sensorName = "Humidity";
    private String measurementDescription = "humidity";

    /**
     * Constructs a HumiditySensor object.
     *
     * @param file: output file.
     * @throws IllegalArgumentException if file is null
     */
    public HumiditySensor(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        dataSet = new TreeSet<DataPacket<Integer>>();
        this.file = file;
    }


    /**
     * Generates humidity between integers 1 and 100.
     *
     * @return humidity value.
     */
    public int generateHumidity() {
        int randomNumber = rand.nextInt(MAX_HUMIDITY + 1 - MIN_HUMIDITY) + MIN_HUMIDITY;
        return randomNumber;
    }

    /**
     * Method that executes in Runnable thread.
     * Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
     */
    public void run() {
        super.run(dataSet, file, 0, generateHumidity(), sensorName, measurementDescription);
    }
}