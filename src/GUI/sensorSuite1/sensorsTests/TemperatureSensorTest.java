/*
 * TemperatureSensor Test class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.sensorsTests;

import GUI.sensorSuite1.sensors.TemperatureSensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Daniel Machen
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the TemperatureSensor class for the VantagePro2 Weather Station.
 */
class TemperatureSensorTest {


    /*
     * Triggers IllegalArgumentException for file parameter in constructor.
     */
    @Test
    void testConstructorNullFileException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new TemperatureSensor(null);
                });
    }
}
