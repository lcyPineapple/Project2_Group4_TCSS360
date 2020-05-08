package GUI.iss7.sensors;

import java.util.Random;

/**
 * This class acts like a temperature sensor.
 */
public class TemperatureSensor implements Sensor {
    private Random randomizer;

    public TemperatureSensor() {
        this.randomizer = new Random();
    }

    @Override
    public String getFieldName() {
        return "temperature (fahrenheit)";
    }

    /**
     * Return some randomized int value to simulate temperature.
     */
    @Override
    public int getData() {
        return 20 + randomizer.nextInt(70);
    }
}
