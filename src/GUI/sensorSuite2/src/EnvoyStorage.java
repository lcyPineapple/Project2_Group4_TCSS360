package GUI.sensorSuite2.src;
/*
 * Nicolas Roberts / Faiz Ahmed
 * TCSS 360 Group Project.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class used to store all weather data.
 * 
 * @author Nicolas Roberts / Faiz Ahmed
 * @version 4/11/2020
 *
 */
public class EnvoyStorage {
	
	/** Array List of SensorData Objects */
	private ArrayList<SensorData> myStorage;
		
	/**
	 * Constructor to initialize the weather data storage.
	 */
	public EnvoyStorage() {
		myStorage = new ArrayList<SensorData>();
		
	}


    /**
     * Method to populate storage data struct. with SensorData objects.
     * 
     * @throws FileNotFoundException if SensorData.txt cannot be found.
     */
    public void getMyWeatherData(String theDataPath) throws FileNotFoundException {
    	Scanner sc = new Scanner(new File(theDataPath));
        while (sc.hasNextLine()) {
        	String temp = sc.nextLine();
        	System.out.println(temp);
        	SensorData sd = new SensorData(temp);
        	myStorage.add(sd);
        }
        sc.close();
      
    }
    
    /**
     * Returns total amount of data.
     * 
     * @return ArrayList of weather data storage.
     */
    public ArrayList<SensorData> getStorage() {
    	return myStorage;
    }
    
    /**
     * a toString representation that shows the contents of whats in the storage array.
     * Format: [Parameter]: Value
     */
    @Override
    public String toString() {
    	String temp = "";
    	for (SensorData obj: myStorage) {
    		temp += "Wind Speed: " + obj.getWindSpeed() + "mph. Wind Direction: "
    	+ obj.getWindDirection() + ". Temperature: " + obj.getTemp()
    	+ "F. Humidity: " +  obj.getHumidity() + "%. Barometric Pressure: " +
    	obj.getBar() + "Pa." + " Rain Fall: " + obj.getDailyRain() + "\n\n";
    		
    	}
    	return temp;
    }

}
