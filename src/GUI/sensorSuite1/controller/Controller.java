/*
 * Controller class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.controller;

import GUI.sensorSuite1.computations.DewPoint;
import GUI.sensorSuite1.computations.HeatIndex;
import GUI.sensorSuite1.computations.RainfallRate;
import GUI.sensorSuite1.computations.WindChill;
import GUI.sensorSuite1.console.Console;
import GUI.sensorSuite1.sensors.*;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Gregory Hablutzel
 * @version 1.0
 * This class represents the main controller box for the VantagePro2 Weather Station.
 * This box gets sensor input, and serializes the data to a controller via a wireless connection.
 * <p>
 * The Controller class operates and schedules all the asynchronous sensor threads.
 * It is these threads themselves who manipulate and serialize the data stored inside this Controller.
 */
public class Controller {

    /*
     * Update Intervals for the sensors and rates.
     */

    public static final int RAINFALL_UPDATE_INTERVAL = 20; // 20 to 24 seconds per specification.
    public static final int RAINRATE_UPDATE_INTERVAL = 20; // 20 to 24 seconds per specification.
    public static final int TEMP_UPDATE_INTERVAL = 10; // 10 to 12 seconds per specification.
    public static final int WINDCHILL_UPDATE_INTERVAL = 10; // 10 to 12 seconds per specification.
    public static final int WINDDIRECTION_UPDATE_INTERVAL = 3; // 2.5 to 3 seconds per specification.
    public static final int WINDSPEED_UPDATE_INTERVAL = 3; // 2.5 1to 3 seconds per specification.
    public static final int HEATINDEX_UPDATE_INTERVAL = 10; // 10 to 12 seconds per specification.
    public static final int HUMIDITY_UPDATE_INTERVAL = 10; // 10 to 12 seconds per specification.
    public static final int DEWPOINT_UPDATE_INTERVAL = 10; // 10 to 12 seconds per specification.

    /*
     * Delay to before sensors start.
     */
    public static final int SENSOR_INITIAL_DELAY = 0;

    /*
     * Delay to before rate calculations start.
     * Allows sensor data to populate before any rates are calculated on data.
     */
    public static final int RATE_INITIAL_DELAY = 61;

    /*
     * WindSensor's Cable Length, which affects the max wind speed allowed.
     */
    public static final int WINDSENSOR_LENGTH = 30;

    /*
     * Files used to transmit serialized data.
     */
    public static final File RAINFALL_FILE = new File("serializedData/rainfallSerializedOutput.txt");
    public static final File RAINRATE_FILE = new File("serializedData/rainfallRateSerializedOutput.txt");
    public static final File TEMPERATURE_FILE = new File("serializedData/temperatureSerializedOutput.txt");
    public static final File HUMIDITY_FILE = new File("serializedData/humiditySerializedOutput.txt");
    public static final File WINDCHILL_FILE = new File("serializedData/windChillSerializedOutput.txt");
    public static final File WINDDIRECTION_FILE = new File("serializedData/windDirectionSerializedOutput.txt");
    public static final File WINDSPEED_FILE = new File("serializedData/windSpeedSerializedOutput.txt");
    public static final File DEWPOINT_FILE = new File("serializedData/dewpointSerializedOutput.txt");
    public static final File HEATINDEX_FILE = new File("serializedData/heatIndexSerializedOutput.txt");

    /*
     * Instance of the console which prints serialized data.
     */
    public static final Console CON = new Console();

    /*
     * Runs the various threads of the sensors and rates.
     */
    public static void main(String[] args) {

        // runs threads.
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


        // SENSORS:

        TemperatureSensor temp = new TemperatureSensor(TEMPERATURE_FILE);
        scheduledExecutorService.scheduleAtFixedRate(temp, SENSOR_INITIAL_DELAY, TEMP_UPDATE_INTERVAL, TimeUnit.SECONDS);

        WindSensor windSpeed = new WindSensor(WINDSPEED_FILE, WINDSENSOR_LENGTH);
        scheduledExecutorService.scheduleAtFixedRate(windSpeed, SENSOR_INITIAL_DELAY, WINDSPEED_UPDATE_INTERVAL, TimeUnit.SECONDS);

        WindDirection windDirection = new WindDirection(WINDDIRECTION_FILE);
        scheduledExecutorService.scheduleAtFixedRate(windDirection, SENSOR_INITIAL_DELAY, WINDDIRECTION_UPDATE_INTERVAL, TimeUnit.SECONDS);

        RainSensor rain = new RainSensor(RAINFALL_FILE);
        scheduledExecutorService.scheduleAtFixedRate(rain, SENSOR_INITIAL_DELAY, RAINFALL_UPDATE_INTERVAL, TimeUnit.SECONDS);

        HumiditySensor humidity = new HumiditySensor(HUMIDITY_FILE);
        scheduledExecutorService.scheduleAtFixedRate(humidity, SENSOR_INITIAL_DELAY, HUMIDITY_UPDATE_INTERVAL, TimeUnit.SECONDS);

        // COMPUTATIONS:

        WindChill windChill = new WindChill(WINDCHILL_FILE, temp.getSet(), windSpeed.getSet());
        scheduledExecutorService.scheduleAtFixedRate(windChill, RATE_INITIAL_DELAY, WINDDIRECTION_UPDATE_INTERVAL, TimeUnit.SECONDS);

        RainfallRate rainfallRate = new RainfallRate(RAINRATE_FILE, rain.getSet());
        scheduledExecutorService.scheduleAtFixedRate(rainfallRate, RATE_INITIAL_DELAY, RAINRATE_UPDATE_INTERVAL, TimeUnit.SECONDS);

        DewPoint dewPoint = new DewPoint(DEWPOINT_FILE, temp.getSet(), humidity.getSet());
        scheduledExecutorService.scheduleAtFixedRate(dewPoint, RATE_INITIAL_DELAY, DEWPOINT_UPDATE_INTERVAL, TimeUnit.SECONDS);

        HeatIndex heatIndex = new HeatIndex(HEATINDEX_FILE, temp.getSet(), humidity.getSet());
        scheduledExecutorService.scheduleAtFixedRate(heatIndex, RATE_INITIAL_DELAY, HEATINDEX_UPDATE_INTERVAL, TimeUnit.SECONDS);

    }
}
