package GUI.weatherStations;

import GUI.WeatherStationIntegrater;
import GUI.iss7.sensorSuite.SensorSuite;
import GUI.iss7.views.ConsoleReceiver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Weather Station for ISS7.
 *
 * @author Yolanda Xu
 */
public class WeatherStation4 extends WeatherStation {

    private static SensorSuite sensorSuite;
    private static ConsoleReceiver consoleReceiver;
    File f = GUI.iss7.sensorSuite.SensorSuite.OUT4;
    Scanner sc = new Scanner(f);
    private Thread integraterThread;
    private Thread integraterThread2;

    public WeatherStation4(WeatherStationIntegrater integrater) throws Exception {
        super(integrater);
    }

    /**
     * This method runs the weather station by receiving and transferring data.
     */
    @Override
    public void run() throws Exception {
        integraterThread = new Thread(() -> {
            try {
                while (true) {
                    setUp();
                    sensorSuite.run();
                }
            } catch (Exception e) {
            }
        });
        integraterThread.start();
        integraterThread2 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(3000);
                    updateList();
                }
            } catch (Exception e) {
            }
        });
        integraterThread2.start();
    }

    /**
     * Interrupt threads.
     */
    @Override
    public void kill() {
        if (integraterThread != null) {
            integraterThread.interrupt();
        }
        if (integraterThread2 != null) {
            integraterThread2.interrupt();
        }
    }

    /**
     * Method obtained in Project 1 to set up the sensors.
     * @return
     * @throws Exception
     */
    private boolean setUp() throws Exception {
        sensorSuite = new SensorSuite();
        consoleReceiver = new ConsoleReceiver(sensorSuite);
        sensorSuite.addObserver(consoleReceiver);
        return true;
    }

    /**
     * Method to update the list with sensor data.
     * @return
     */
    private List<Double> updateList() {
        List<Double> weatherDataList4 = new ArrayList<>();
        weatherDataList4.add(sc.nextDouble());
        weatherDataList4.add(sc.nextDouble());
        weatherDataList4.add(sc.nextDouble());
        weatherDataList4.add(sc.nextDouble());
        super.update(weatherDataList4);
        return weatherDataList4;
    }
}