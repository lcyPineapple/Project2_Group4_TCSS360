package GUI.iss7.sensors;

import java.util.Random;

/**
 * This class acts like a humidity sensor.
 */
public class HumiditySensor implements Sensor {
    private Random randomizer;

    public HumiditySensor() {
        this.randomizer = new Random();
    }

    @Override
    public String getFieldName() {
        return "humidity (percentage)";
    }

    /**
     * Simulates a return value from the humidity sensor.
     *
     * @return A random integer from 20 (inclusive) to 40 (inclusive).
     */
    @Override
    public int getData() {
        return 20 + randomizer.nextInt(21);
    }
}
