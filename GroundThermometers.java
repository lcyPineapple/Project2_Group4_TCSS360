package GUI.iss8.data;

import java.io.Serializable;

/**
 * This class represents a ground thermometer gathering temperature readings from the
 * ground.
 * Updated code to allow for static value unit testing
 * @author Alex Amado
 * @version Spring 2020
 * 
 * Updated code to produce more realistic data.
 * @author Dean Kelley
 */
public class GroundThermometers implements Serializable {
    /**
     * Generated serial ID.
     */
    private static final long serialVersionUID = 2551705589558192863L;
    /** Represents temperature of a specific environment. */
    private int myTemperature;

    public GroundThermometers() {
        myTemperature = 0;
    }

    /**
     * Uses res.R.Integers.HDAY-hour sinusoid to determine temperature.
     * @param theToggleStaticValue - boolean for unit testing static value 
     */
    public void setTemp(final boolean theToggleStaticValue) {
        if (theToggleStaticValue) {
            myTemperature = GUI.iss8.res.R.Integers.AIRGROUNDTEMPTEST;
        } else {
            final int secInDay = GUI.iss8.res.R.Integers.HDAY * GUI.iss8.res.R.Integers.SIXTY 
                    * GUI.iss8.res.R.Integers.SIXTY;
            final int timeSec = java.time.LocalTime.now().toSecondOfDay();
            myTemperature = (int) (GUI.iss8.res.R.Integers.FIFTY + Math.sin(2. * Math.PI 
                    * (double) timeSec / secInDay - Math.
                    PI / 2) * GUI.iss8.res.R.Integers.FIVE);
        }
    }

    public int getTemperature() {
        return myTemperature;
    }
}
