package GUI.iss8.data;

import java.io.Serializable;

/**
 * This class emcompasses all the weather data specified by Figure 7.6, page 203 of 
 * the textbook. That weather data includes air temperatures, rainfall, and a
 * GroundThermometer object, Anemometer object, Barometer object all holding respective
 * data
 * @author Alex Amado
 * @version Spring 2020
 * 
 * Updated the code so data updates 
 * @author Dean
 * 
 * Added rainfall and humidity.
 * @author yolandaxu
 *
 * Made runnable as a thread for the ISS to receive input from in an asynchronous fashion.
 * @author Maxfield
 */
public class WeatherData extends Thread implements Serializable {
    /**
     * Generated serial ID.
     */
    private static final long serialVersionUID = -9008953840484490065L;
    /** Represents air temperature of a specific environment measured in Fahrenheit. */
    private final AirThermometer myAirData;
    /** Represents ground temperatures of a specific environment. */
    private final GroundThermometers myGroundData;
    /** Represents wind data of a specific environment. */
    private final Anemometer myWindData;
    /** Represents air pressure data of a specific environment. */
    private final Barometer myPressureData;
    /** Represents daily rain data of a specific environment measured in inches. */
    private final int myRainfall;
    /** Represents the outside relative humidity. */
    private final RelHumidity myHumidity;
   
    /**
     * Constructs a WeatherData object instantiating all the data with random values.
     */
    public WeatherData() {
        super();
        myAirData = new AirThermometer();
        myGroundData = new GroundThermometers();
        myWindData = new Anemometer();
        myPressureData = new Barometer();
        myRainfall = 0;
        myHumidity = new RelHumidity();
    }

    /**
     * Air temperature in Fahrenheit by default.
     * @return integer for air temperature
     */
    public int getAirTemperatures() {
        return myAirData.getAirTemperature();
    }

    /**
     * Ground temperature in Fahrenheit by default.
     * @return integer for ground temperature
     */
    public int getGroundThermometerTemperature() {
        return myGroundData.getTemperature();
    }

    /**
     * Wind speed in MPH.
     * @return integer for  wind speed
     */
    public int getWindSpeed() {
        return myWindData.getWindSpeed();
    }

    /**
     * Wind direction.
     * @return String for wind direction
     */
    public String getWindDirection() {
        return myWindData.getWindDirection();
    }

    /**
     * Air pressure in inHg.
     * @return double for air pressure
     */
    public double getPressure() {
        return myPressureData.getPressure();
    }

    /**
     * Rainfall in inches.
     * @return integer for rainfall
     */
    public int getRainfall() {
        return myRainfall;
    }

    /**
     * Humidity in %.
     * @return integer for %
     */
    public int getHumidity() {
        return myHumidity.getHumidity();
    }

    /**
     * Updates the sensors.
     */
    public void updateData() {
        myAirData.setTemp(false);
        myGroundData.setTemp(false);
        myHumidity.setHumidity(false);
        myPressureData.setPressure(false);
        myWindData.setWind(false);
    }
   
    /**
     * Is executed when thread is started; calls on the write() method to generate new
     * weather data every five seconds, and write it to the weather.ser file.
     * 
     * @author Maxfield
     */
    @Override
    public void run() {	
        while (true) {
            updateData();
            try {
                //Round up from 2.5 seconds (ISS update time)
                sleep(GUI.iss8.res.R.Integers.TWOFIVETHOU);
            } catch (final InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		
    }
}