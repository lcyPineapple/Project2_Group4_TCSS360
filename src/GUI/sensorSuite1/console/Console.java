/*
 * Console class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.console;

import GUI.sensorSuite1.controller.DataPacket;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.TreeSet;


/**
 * @author Gregory Hablutzel
 * @version 1.0
 * This class is the (Display) Console  for the VantagePro2 Weather Station.
 * It displays information from the controller, through a serialized connection.
 * The information it displays is the reading and calculations from the serialized sensor and computational threads.
 */

public class Console {

    /**
     * @param <T> Can work for integers or doubles.
     * @param f:  takes in a file to read serialized data from.
     * @throws Exception: throws exceptions for FileInputStream, ObjectInputStream.
     *                    Reads serialized data, and prints it to console.
     */
    public <T> void readSerializedData(File f) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        @SuppressWarnings("unchecked")
        TreeSet<DataPacket<T>> obj1 = (TreeSet<DataPacket<T>>) ois.readObject();
        // get a DataPacket from the set, used for the println statement.
        DataPacket<T> dp = obj1.iterator().next();
        System.out.println("serialized " + dp.getMeasurement() + " data is: " + obj1.toString());
        ois.close();
    }
}
