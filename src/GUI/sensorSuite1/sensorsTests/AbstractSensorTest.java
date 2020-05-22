/*
 * ABstractSensor Test class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.sensorsTests;

import GUI.sensorSuite1.sensors.AbstractSensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;


/**
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the AbstractSensor class for the VantagePro2 Weather Station.
 */
class AbstractSensorTest {


    /*
     * Ensures rounding method operates correctly.
     */
    @Test
    void testRoundMethod() {

        AbstractSensor<Integer> instanceToTest = new AbstractSensorTestable();

        //ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        double val = 43.257;
        double roundedVal = instanceToTest.round(val, 1);
        System.out.println("roundedVal is " + roundedVal);
        if (Double.compare(roundedVal, 43.3) != 0) {
            fail("values dont match");
        }
    }

    /**
     * This is needed, because we cannot construct abstract class directly
     */
    class AbstractSensorTestable extends AbstractSensor<Integer> {
    }
}
