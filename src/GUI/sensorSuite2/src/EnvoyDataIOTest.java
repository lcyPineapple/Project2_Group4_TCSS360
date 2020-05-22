package GUI.sensorSuite2.src;
/*
 * TCSS 305 - Autumn 2019
 * Instructor: Charles Byran
 * Assignment 1 - bookstore
 * 10/7/19
 */

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;

import static org.junit.Assert.assertEquals;


/**
 * JUnit Test Class for EnvoyDataIO.java
 *
 * @author Nicolas Roberts
 * @version 4/17/2020
 */

public class EnvoyDataIOTest {

    /**
     * A constant that will determine how much data is given in the output file.
     */
    private static final int DATA_FREQUENCY = 5;

    /**
     * A constant that determines the interval data is gathered from the Sensors.
     * Time interval is measured in milliseconds.
     */
    private static final int TIME_INTERVAL = 1000;

    /**
     * The correct data socket number.
     */
    private static final int SOCKET = 9876;

    /**
     * Correct file path where the .txt file will show up.
     */
    private static final String FILEPATH = "src/TestSensorData.txt";

    /**
     * Invalid time interval.
     */
    private static final int WRONG_TIME_INTERVAL = -1000;

    /**
     * Invalid frequency.
     */
    private static final int WRONG_DATA_FREQUENCY = -3;

    /**
     * Wrong socket number.
     */
    private static final int WRONG_SOCKET = 9875;

    /**
     * A test EnvoyDataIO object.
     */
    private EnvoyDataIO myEVIO;


    /**
     * Creating instances of item to use throughout JUnit testing. Runs before every test case.
     */
    @Before
    public void setUp() {
        myEVIO = new EnvoyDataIO();

    }

    /**
     * If incorrect Time Interval is given, exception should be thrown.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFillFileTimeInterval() throws InterruptedException, IOException {
        EnvoyDataIO output = new EnvoyDataIO();
        PrintStream outputFile = output.getOutputStream(FILEPATH);
        myEVIO.fillFile(SOCKET, DATA_FREQUENCY, WRONG_TIME_INTERVAL, outputFile);
    }

    /**
     * If incorrect Time Interval is given, exception should be thrown.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFrequency() throws InterruptedException, IOException {
        EnvoyDataIO output = new EnvoyDataIO();
        PrintStream outputFile = output.getOutputStream(FILEPATH);
        myEVIO.fillFile(SOCKET, WRONG_DATA_FREQUENCY, TIME_INTERVAL, outputFile);
    }

    /**
     * If incorrect Socket is given, exception should be thrown.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test(expected = Exception.class)
    public void testSocket() throws InterruptedException, IOException {
        EnvoyDataIO output = new EnvoyDataIO();
        PrintStream outputFile = output.getOutputStream(FILEPATH);
        myEVIO.fillFile(WRONG_SOCKET, DATA_FREQUENCY, TIME_INTERVAL, outputFile);
    }

    /**
     * Testing method run without exceptions.
     *
     * @throws ConnectException
     */
    @Test
    public void testSocket2() throws ConnectException {
        boolean test = true;
        EnvoyDataIO output = new EnvoyDataIO();
        PrintStream outputFile = output.getOutputStream(FILEPATH);
        myEVIO.fillFile(SOCKET, DATA_FREQUENCY, TIME_INTERVAL, outputFile);
        assertEquals(test, myEVIO.getTestBoolean());
    }

}

