package GUI.iss7.tests.sensors;

import GUI.iss7.sensors.RainSensor;
import GUI.iss7.sensors.Sensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for rain sensor.
 */
class RainSensorTest {

    /**
     * Ensure rain sensor's data field name is correct.
     */
    @Test
    void testGetFieldName() {
        Sensor rainSensor = new RainSensor();
        assertEquals("rainfall (inches)", rainSensor.getFieldName());
    }

    /**
     * Ensure rainfall data generated from rain sensor is in expected range.
     */
    @Test
    void testGetData() {
        Sensor rainSensor = new RainSensor();
        for (int i = 0; i < 1000; i++) {
            int rainfall = rainSensor.getData();
            assertTrue(rainfall >= 40 && rainfall <= 42);
        }
    }
}
