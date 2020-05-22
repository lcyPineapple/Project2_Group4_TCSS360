/*
 * RainfallRate Test class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.computationsTests;

import GUI.sensorSuite1.computations.RainfallRate;
import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.sensors.RainSensor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the RainfallRate class for the VantagePro2 Weather Station.
 */
class RainfallRateTest {

    /*
     * Ensures that rain fall rate works for first 3 values generated by the rain sensor.
     * (Rainfall rate given values of 0.01", 0.04", and 0", should generate a rainfall rate of 3.0"/hr)
     */
    @Test
    void testGeneratedValues() {
        System.out.println("test");
        RainSensor rain = new RainSensor(Controller.RAINFALL_FILE);

        rain.run(); // 0.01
        rain.run(); // 0.04
        rain.run(); // 0.0

        RainfallRate rainfallRate = new RainfallRate(Controller.RAINRATE_FILE, rain.getSet());

        rainfallRate.run();
        Double[] rainfallGeneratedValues = {3.0};
        int i = 0;
        for (DataPacket<Double> dp : rainfallRate.getSet()) {
            if (Double.compare(dp.getValue(), rainfallGeneratedValues[i]) != 0) {
                fail("values dont match");
            }

            i++;
        }
    }

    /*
     * Inputs a rainfall of 0.0000001, 0.0, 0.0, which results in a rain rate of < 0.04.
     * This triggers the if branch (if rainrate < 0.04) in the calcRainRate() method.
     * We are verifying that this branch occurs, and that the rainrate gets set to 0
     */
    @Test
    void testRainrateLessThan04() {
        RainSensor rain = new RainSensor(Controller.RAINFALL_FILE);
        ZonedDateTime eventTime = ZonedDateTime.now();
        DataPacket<Double> rainDP1 = new DataPacket<Double>(eventTime, "rain", "rain", 0.0000001);
        DataPacket<Double> rainDP2 = new DataPacket<Double>(eventTime.plusSeconds(1), "rain", "rain", 0.0);
        DataPacket<Double> rainDP3 = new DataPacket<Double>(eventTime.plusSeconds(2), "rain", "rain", 0.0);

        rain.getSet().add(rainDP1);
        rain.getSet().add(rainDP2);
        rain.getSet().add(rainDP3);
        RainfallRate rainfallRate = new RainfallRate(Controller.RAINRATE_FILE, rain.getSet());

        rainfallRate.run();
        double rainRate = 0;
        assertTrue(Double.compare(rainfallRate.getSet().last().getValue(), rainRate) == 0);
    }


    //	/*
//	 * Inputs a very large rainfall of 100" each measurement, which results in a rain rate greater than the max of 30.
//	 * This triggers the if branch (if rainrate > 30) in the calcRainRate() method.
//	 * We are verifying that this occurs.
//	 */
    @Test
    void testRainrateGreaterThanMax() {
        RainSensor rain = new RainSensor(Controller.RAINFALL_FILE);

        ZonedDateTime eventTime = ZonedDateTime.now();
        DataPacket<Double> rainDP1 = new DataPacket<Double>(eventTime, "rain", "rain", 100.0);
        DataPacket<Double> rainDP2 = new DataPacket<Double>(eventTime.plusSeconds(1), "rain", "rain", 100.0);
        DataPacket<Double> rainDP3 = new DataPacket<Double>(eventTime.plusSeconds(2), "rain", "rain", 100.0);
        rain.getSet().add(rainDP1);
        rain.getSet().add(rainDP2);
        rain.getSet().add(rainDP3);
        RainfallRate rainfallRate = new RainfallRate(Controller.RAINRATE_FILE, rain.getSet());

        rainfallRate.run();

        double rainRate = 30;
        assertTrue(Double.compare(rainfallRate.getSet().last().getValue(), rainRate) == 0);


    }

    /*
     * Triggers IllegalArgumentException for file parameter in constructor.
     */
    @Test
    void testConstructorNullFileException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new RainfallRate(null, new TreeSet<DataPacket<Double>>());
                });
    }

    /*
     * Triggers IllegalArgumentException if data set parameter is null in constructor.
     */
    @Test
    void testConstructorNullSetInputException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new RainfallRate(new File("test.txt"), null);
                });
    }

    /*
     * Triggers IllegalArgumentException if data set has less than 3 elements inside calcRainRate() function.
     */
    @Test
    void testCalcRainRateSetInputSizeException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    RainfallRate rain = new RainfallRate(new File("test.txt"), new TreeSet<DataPacket<Double>>());
                    rain.calcRainRate();
                });
    }
}
