package GUI.iss7.tests.sensors;

import GUI.iss7.sensors.HumiditySensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HumiditySensorTest {

    /**
     * Tests whether the humidity sensor class returns the correct field name.
     */
    @Test
    void testGetFieldName() {
        final HumiditySensor sensor = new HumiditySensor();
        final String fieldName = sensor.getFieldName();
        assertEquals("humidity (percentage)", fieldName);
    }

    /**
     * Tests whether the humidity sensor class returns expected values.
     */
    @Test
    void testGetData() {
        final HumiditySensor sensor = new HumiditySensor();
        final int iterations = 100_000;
        for (int i = 0; i < iterations; i++) {
            final int data = sensor.getData();
            assertTrue(20 <= data, "The humidity sensor's return value of " + data +
                    " was less than 20.");
            assertTrue(data <= 40, "The humidity sensor's return value of " + data +
                    " was greater than 40.");
        }
    }
}
