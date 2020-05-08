package GUI.iss7.sensors;

import java.util.Random;

/**
 * This class acts like a rain sensor.
 */
public class RainSensor implements Sensor {
    private Random randomizer;

    public RainSensor() {
        this.randomizer = new Random();
    }

    @Override
    public String getFieldName() {
        return "rainfall (inches)";
    }

    /**
     * Return some randomized int value to simulate rainfall.
     */
    @Override
    public int getData() {
        return 40 + randomizer.nextInt(3);
    }
}
