package GUI.iss7.sensors;

import java.util.Random;

/**
 * This class acts like an anemometer sensor.
 */
public class AnemometerSensor implements Sensor {
    private Random randomizer;

    public AnemometerSensor() {
        this.randomizer = new Random();
    }

    @Override
    public String getFieldName() {
        return "wind speed (miles per hour)";
    }

    /**
     * Return some randomized int value to simulate wind speed.
     * Set the randomized range to be 20 to 40.
     */
    @Override
    public int getData() {
        return randomizer.nextInt(21) + 20;
    }
}
