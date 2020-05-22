/*
 * WindChill Test class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.computationsTests;

import GUI.sensorSuite1.computations.WindChill;
import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.sensors.TemperatureSensor;
import GUI.sensorSuite1.sensors.WindSensor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cade Reynoldson
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the WindChill class for the VantagePro2 Weather Station.
 */
class WindChillTest {


    /*
     * Ensures that if wind chill works using generated values from temp and windspeed.
     * temp = 80.1F, windSpeed = 3MPH, WindChill = 84F (expected).
     */
    @Test
    void testGeneratedValues() {

        TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
        WindSensor windSpeed = new WindSensor(Controller.WINDSPEED_FILE, Controller.WINDSENSOR_LENGTH);
        WindChill windChill = new WindChill(Controller.WINDCHILL_FILE, temp.getSet(), windSpeed.getSet());
        temp.run();          // 80.1F
        windSpeed.run();  // 3MPH
        windChill.run();  // 84F

        if (windChill.getSet().last().getValue() != 84) {
            fail("values dont match");
        }
    }


    /*
     * Triggers IllegalArgumentException for file parameter in constructor.
     */
    @Test
    void testConstructorNullFileException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new WindChill(null, new TreeSet<DataPacket<Double>>(), new TreeSet<DataPacket<Integer>>());
                });
    }

    /*
     * Triggers IllegalArgumentException if first data set parameter is null in constructor.
     */
    @Test
    void testConstructorNullSetInputException1() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new WindChill(new File("test.txt"), null, new TreeSet<DataPacket<Integer>>());
                });
    }


    /*
     * Triggers IllegalArgumentException if second data set parameter is null in constructor.
     */
    @Test
    void testConstructorNullSetInputException2() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new WindChill(new File("test.txt"), new TreeSet<DataPacket<Double>>(), null);
                });
    }

    /*
     * Triggers IllegalArgumentException if wind speed is less than 3MPH.
     *
     */
    @Test
    void testWindSpeedLessThan3Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
                    WindSensor windSpeed = new WindSensor(Controller.WINDSPEED_FILE, Controller.WINDSENSOR_LENGTH);
                    WindChill windChill = new WindChill(Controller.WINDCHILL_FILE, temp.getSet(), windSpeed.getSet());
                    temp.run();
                    ZonedDateTime eventTime = ZonedDateTime.now();
                    windSpeed.getSet().add(new DataPacket<Integer>(eventTime, "wind", "wind", 1)); // set windspeed at 1MPH
                    windChill.calculateWindChill();
                });
    }


    /*
     * Ensures that if wind chill is greater than 135 (the MAX), that it will get set to 135.
     * (Calculating windchill based of 200F temp and 5MPH wind speed, which results in a wind chill > 135).
     */
    @Test
    void testWindChillGreaterThan135() {
        TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
        WindSensor windSpeed = new WindSensor(Controller.WINDSPEED_FILE, Controller.WINDSENSOR_LENGTH);
        WindChill windChill = new WindChill(Controller.WINDCHILL_FILE, temp.getSet(), windSpeed.getSet());
        ZonedDateTime eventTime = ZonedDateTime.now();
        temp.getSet().add(new DataPacket<Double>(eventTime, "temp", "temp", 200.0));
        windSpeed.getSet().add(new DataPacket<Integer>(eventTime, "wind", "wind", 5));
        int windChillVal = windChill.calculateWindChill();
        assertEquals(135, windChillVal);
    }


    /*
     * Ensures that if wind chill is less than -110(the MIN), that it will get set to -110.
     * (Calculating windchill based of -200F temp and 5MPH wind speed, which results in a wind chill < -110).
     */
    @Test
    void testWindChillLesshanNegative110() {
        TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
        WindSensor windSpeed = new WindSensor(Controller.WINDSPEED_FILE, Controller.WINDSENSOR_LENGTH);
        WindChill windChill = new WindChill(Controller.WINDCHILL_FILE, temp.getSet(), windSpeed.getSet());
        ZonedDateTime eventTime = ZonedDateTime.now();
        temp.getSet().add(new DataPacket<Double>(eventTime, "temp", "temp", -200.0));
        windSpeed.getSet().add(new DataPacket<Integer>(eventTime, "wind", "wind", 5));
        int windChillVal = windChill.calculateWindChill();
        assertEquals(-110, windChillVal);
    }
}
