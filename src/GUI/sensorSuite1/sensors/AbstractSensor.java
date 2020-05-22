/*
 * AbstractSensor class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1
 * Assignment Title: Weather Station
 * Year: Spring 2020
 * School: UW-Tacoma
 * Description:
 *
 * Note, we are UNABLE to test for exceptions, because this abstract classes run() method is called within the Runnable threads run() methods.
 * All Runnable threads, by design, cannot throw or return exceptions, and thus we are unable to check for thrown exceptions in JUNIT.
 *
 * For more information, see here: https://dev4devs.com/2016/06/21/java-how-to-create-threads-that-return-values/
 *
 */

package GUI.sensorSuite1.sensors;

import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.TreeSet;


/**
 * @author Gregory Hablutzel
 * @version 1.0
 * This class is the Abstract Sensor class for the VantagePro2 Weather Station.
 * It contains some of the variables needed for each sensor, a pseudo random number generator using a given seed, and more.
 * It holds the run() method that each Runnable thread executes, and contains a round() method to round doubles.
 */

public abstract class AbstractSensor<T> {

    // protected means that this class and any subclasses may access the property.
    protected String sensorName = "Abstract Sensor";
    protected String measurement = "NULL";

    protected ZonedDateTime eventTime;
    protected long seed = 24;
    // for each of time we create a new instance of one of the sensors,
    // 	create a new random object, and initialize it with a seed to we can reproduce
    // 	the values it generates later in our JUnit Tests
    // 	(make sensor output pseudo-random and reproducible)
    protected Random rand = new Random(seed);
    protected FileOutputStream fos;
    protected ObjectOutputStream oos;
    protected TreeSet<DataPacket<T>> dataSet;
    protected File file;

    public TreeSet<DataPacket<T>> getSet() {
        return dataSet;
    }

    /**
     * Rounds a double to a given level of precision (1 = 1 decimal point)
     *
     * @param value:     inputed value
     * @param precision: precision amount
     * @return returns rounded value
     */
    public double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /**
     * @param outputSet:       output data set containing last 60 seconds of data.
     * @param f:               output file.
     * @param zeroValue:       zero value for integer or double (0 or 0.0).
     * @param value:           measurement value.
     * @param sensor:          sensor name.
     * @param measurementName: name of measurement.
     *                         <p>
     *                         Method that executes in Runnable thread.
     *                         Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
     *                         Note, we cannot test this for exceptions, because the Runnable thread does not permit exceptions to be thrown.
     */
    public void run(TreeSet<DataPacket<T>> outputSet, File f, T zeroValue, T value, String sensor, String measurementName) {
        try {
            fos = new FileOutputStream(f);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);

        } catch (IOException e) {
            e.printStackTrace();
        }
        eventTime = ZonedDateTime.now();
        DataPacket<T> rdp = new DataPacket<T>(eventTime, sensor, measurementName, value);
        outputSet.add(rdp);
        TreeSet<DataPacket<T>> dataPacket = (TreeSet<DataPacket<T>>)
                outputSet.tailSet(new DataPacket<T>(ZonedDateTime
                        .now()
                        .minusSeconds(60), sensor, measurementName, zeroValue));
        try {
            oos.writeObject(dataPacket);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            Controller.CON.<T>readSerializedData(f);
            oos.flush();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
