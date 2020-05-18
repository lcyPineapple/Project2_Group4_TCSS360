package GUI.iss7.tests.sensors;

import GUI.iss7.sensors.AnemometerSensor;
import GUI.iss7.sensors.Sensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AnemometerSensorTest {
    /**
     * Test AnemometerSensor name is correct.
     */
    @Test
    void testGetFieldName() {
        final Sensor anemometerSensor = new AnemometerSensor();
        assertEquals("wind speed (miles per hour)", anemometerSensor.getFieldName());
    }

    /**
     * Test get data method is correct.
     */
    @Test
    void testGetData() {
        final Sensor anemometerSensor = new AnemometerSensor();
        for(int i = 0; i < 100; i ++) {
            final int data = anemometerSensor.getData();
            assertTrue(20 <= data);
            assertTrue(data <= 40);
        }
    }
}
