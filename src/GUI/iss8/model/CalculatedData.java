package GUI.iss8.model;

/**
 * This class calculates the metrics put forth by he requirements in the ISS documentation
 * utilizing the data collected by the sensors.  
 * @author Dean Kelley
 * @version Spring 2020
 */
public class CalculatedData {
    
    /**
     * Converts temperature from F to C or from C to F.
     * @param theTemp - temperature in degrees Fahrenheit
     * @param theUnit - unit to convert to
     * @return degrees in Celcius
     */
    public static int convertTemp(final int theTemp, final String theUnit) {
        double temp = (double) theTemp;
        if (theUnit.equals(GUI.iss8.res.R.Strings.C)) {
            temp =  (theTemp - 32) * 5. / 9;
        } else if (theUnit.equals(GUI.iss8.res.R.Strings.F)) {
            temp = theTemp * 9. / 5 + 32;
        } else {
            temp = -273;
        }
        return (int) Math.round(temp);
    }	
    
    /**
     * Converts pressure from inHg to another unit.
     * @param thePress
     * @param theToUnit
     * @return pressure in different units
     */
    public double convertPressure(final double thePress, final String theToUnit) {
        double press = 0;
        if (theToUnit.equals(GUI.iss8.res.R.Strings.MMHG)) {
            press = thePress * GUI.iss8.res.R.Doubles.IN2MM;
        } else if (theToUnit.equals(GUI.iss8.res.R.Strings.PA)) {
            press = thePress * GUI.iss8.res.R.Doubles.INHG2PA;
        } else if (theToUnit.equals(GUI.iss8.res.R.Strings.HPA)) {
            press = thePress * GUI.iss8.res.R.Doubles.INHG2PA / GUI.iss8.res.R.Doubles.HPA2PA;
        } else if (theToUnit.equals(GUI.iss8.res.R.Strings.MBAR)) {
            press = thePress * GUI.iss8.res.R.Doubles.INHG2MB;
        } else {
            press = thePress;
        }
        return round(press, 2);
    }
    
    /** 
     * Calculates the dew point temperature.
     * @param theTemp - temperature in Celcius
     * @param theHum - relative humidity in %
     * @return dew point temperature
     */
    public int dewPoint(final int theTemp, final int theHum, final String theUnit) {
        double T = (double) theTemp;
        final double RH = (double) theHum / 100;
        if (theUnit.equals(GUI.iss8.res.R.Strings.F)) {
            T = (double) convertTemp(theTemp, GUI.iss8.res.R.Strings.C);
        }
        final double a = GUI.iss8.res.R.Doubles.DEWA;
        final double b = GUI.iss8.res.R.Doubles.DEWB;
        int dewP = (int) Math.round((b * (a * T / (b + T) + Math.log(RH))) / (a - (a * T / (b + T) + 
                Math.log(RH)))); 
        if (theUnit.equals(GUI.iss8.res.R.Strings.F)) {
            dewP = convertTemp(dewP, GUI.iss8.res.R.Strings.F);
        }
        return dewP;
    }
    
    /**
     * Calculates the dew point temperature.
     * @param theTemp - temperature in Celcius
     * @param theHum - relative humidity in %
     * @param theUnit - temperature unit
     * @return heat index - "feels like temperature"
     */
    public int heatIndex(final int theTemp, final int theHum, final String theUnit) {
        double T = 0;
        if (theUnit.equals(GUI.iss8.res.R.Strings.C)) {
            T = convertTemp(theTemp, GUI.iss8.res.R.Strings.F);
        } else {
            T = theTemp;
        }
        final double RH = (double) theHum;
        double HI = 0;
        HI=	-42.379 + 2.04901523 * T + 10.14333127 * RH - .22475541 * T * RH - 
                .00683783 * T * T - .05481717 * RH * RH + .00122874 * T * T * RH + 
                .00085282 * T * RH * RH - .00000199 * T * T * RH * RH;
        if (T > 80 && T < 112 && RH < 13) {
            HI -= ((13. - RH) / 4.) * Math.sqrt((17. - Math.abs(T - 95.)) / 17.);
        } else if (T >= 80 && T <= 87 && RH > 85) {
            HI += ((RH - 85.) / 10.) * ((87. - T) / 5.);
        } else if (T < 80) {
            HI = theTemp;
        } else {
            HI *= 1;
        }
        int retT = (int) Math.round(HI);
        if (theUnit.equals(GUI.iss8.res.R.Strings.C)) {
            retT = convertTemp(retT, GUI.iss8.res.R.Strings.C);
        }
        return retT;
    }
    
    /**
     * Converts wind speed to different units.
     * @param theSpeed - speed in MPH
     * @param theUnit - unit to convert to
     * @return converted speed
     */
    public int convertWindSpeed(final int theSpeed, final String theUnit) {
        double speed = (double) theSpeed;
        if (theUnit.equals(GUI.iss8.res.R.Strings.KPH)) {
            speed = speed * GUI.iss8.res.R.Doubles.MPH2KPH;
        } else if (theUnit.equals(GUI.iss8.res.R.Strings.MPS)) {
            speed = speed * GUI.iss8.res.R.Doubles.MPH2MPS;
        } else {
            speed = speed * GUI.iss8.res.R.Doubles.MPH2KNOT;
        }
        return (int) Math.round(speed);
    }
    
    /**
     * Calculates the apparent temperature caused by wind chill.
     * @param theTemp - temperature in Fahrenheit
     * @param theSpeed - speed in MPH
     * @return wind chill temperature in Fahrenheit
     */
    public int windChill(final int theTemp, final int theSpeed) {
        final double T = theTemp;
        final double V = theSpeed;
        double WC = T;
        if (T <= 40) {
            WC = 35.74 + 0.6215 * T - 35.75 * Math.pow(V, 0.16) + 0.4275 * T * Math.
                    pow(V, 0.16);			
        } else {
            WC = T;
        }
        return (int) Math.round(WC);
    }
    
    /**
     * Rounds to nearest desired tens place.
     * @param theVal - value to round
     * @param thePlace desired tens place
     * @return number rounded to the desired tens place
     */
    public double round(final double theVal, final int thePlace) {  
        return Math.round(theVal * Math.pow(10, thePlace)) / Math.pow(10, thePlace);
    }
}
