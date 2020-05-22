package GUI.iss8.data;

import java.io.Serializable;

/**
 * This class represents a ground thermometer gathering temperature readings from the
 * air.
 *
 * @author Dean Kelley
 * @author Adam Amado
 * @version Spring 2020
 * <p>
 * Updated code to allow for static value unit testing
 */
public class AirThermometer implements Serializable {

    /**
     * Generiated serial ID.
     */
    private static final long serialVersionUID = 1277845658233704763L;
    /**
     * Represents temperature of a specific environment.
     */
    private int myTemperature;

    public AirThermometer() {
        myTemperature = 0;
    }

    /**
     * Uses 24-hour sinusoid to determine temperature.
     *
     * @param theToggleStaticValue - boolean for unit testing static value
     */
    public void setTemp(final boolean theToggleStaticValue) {
        if (theToggleStaticValue) {
            myTemperature = GUI.iss8.res.R.Integers.AIRGROUNDTEMPTEST;
        } else {
            final int secInDay = 24 * 60 * 60;
            final int timeSec = java.time.LocalTime.now().toSecondOfDay();
            myTemperature = (int) (50 + Math.sin(2. * Math.PI * (double) timeSec / secInDay - Math.
                    PI / 2) * 15);
        }
    }

    public int getAirTemperature() {
        return myTemperature;
    }
}
