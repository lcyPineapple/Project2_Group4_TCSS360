package sensors;

import GUI.iss7.sensors.Sensor;
import GUI.iss7.sensors.TemperatureSensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureSensorTest {
    /**
     * Tests whether the temperature sensor class returns the correct field name.
     */
    @Test
    void testGetFieldName() {
        Sensor temperatureSensor = new TemperatureSensor();
        assertEquals("temperature (fahrenheit)", temperatureSensor.getFieldName());
    }

    /**
     * Tests whether the temperature sensor class returns expected values.
     */
    @Test
    void testGetData() {
        Sensor temperatureSensor = new TemperatureSensor();
        for (int i = 0; i < 1000; i++) {
            int temperature = temperatureSensor.getData();
            assertTrue(temperature >= 20 && temperature <= 90);
        }
    }
}
