package GUI.weatherStations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import GUI.iss8.model.AbstractOutputDevice;
import GUI.iss8.model.ISS;
import GUI.iss8.model.WeatherMonitoringApp;
import GUI.weatherStations.WeatherStation;
import GUI.WeatherStationIntegrater;
import GUI.iss8.data.WeatherData;


/**
 * This class integrates the group 8 weather station with the GUI.
 * It will be called Weather Station 5.
 * 
 * @author Leika Yamada
 * @version 5.9.2020
 * */

public class WeatherStation5 extends WeatherStation{
	/**Creates a new WeatherData object*/
	private static WeatherData sensor8Data = new WeatherData();
	/**Array of Doubles that holds the most current weather data*/
	private Double[] sensorDataWS8 = new Double[4];
//	private WeatherMonitoringApp testApp;
//    private ArrayList<AbstractOutputDevice> testDeviceArray;
//    private ISS myISS;
	
	/**This is the constructor inherited from the abstract class.
	 * Calls the parent constructor. 
	 * */
	public WeatherStation5(WeatherStationIntegrater integrater) {
		super(integrater);
		// Left Empty
	}
	
	/**This method returns an array containing sensor data.
	 * Used for testing purposes only.
	 * */
	public Double[] getSensorArray() {
		return sensorDataWS8;
	}
	/**This method overrides the run method of the WeatherStation class.
	 * It sets up and starts collecting data from weather station 8.
	 * @Override Overrides the abstract class WeatherStation.
	 * */
	@Override
	public void run() throws InterruptedException {	
        Thread Station8Thread = new Thread(() -> {
        	try {
        		setUpStation8();
                while (true) {
                    Thread.sleep(3000);
                    collectData();
                }
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        });
        Station8Thread.start();      
	}
	 /**This method reads the weather.ser file.
	  * @return Boolean based successful read of file, for testing purposes.
	  * */
	public synchronized boolean readDataFile() {
		boolean success = false;
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream("weather.ser"));
			sensor8Data = (WeatherData) in.readObject();
			in.close();
			success = true;
		} catch (ClassNotFoundException e) {
			// print the error
			e.printStackTrace();
		} catch (IOException e) {
			// print the error 
			e.printStackTrace();
	    } 
		return success;
	}
	
	/**
	 * This method reads the serialized file and returns the most current
	 * data from weather station 8.
	 * */
	public synchronized void collectData() {
		readDataFile();
		//: [Humidity, Temperature, Wind speed, Rain fall]
		// Index 0, 1, 2, 3, respectively
		sensorDataWS8[0] = (double)sensor8Data.getHumidity();
		sensorDataWS8[1] = (double)sensor8Data.getAirTemperatures();
		sensorDataWS8[2] = (double)sensor8Data.getWindSpeed();
		sensorDataWS8[3] = (double)sensor8Data.getRainfall();
		super.update(Arrays.asList(sensorDataWS8));
	}
	
	/**This method sets up weather station 8 and gets it ready to be run.
	 * */
	public boolean setUpStation8() {
		final WeatherMonitoringApp testApp = new WeatherMonitoringApp();

        final ArrayList<AbstractOutputDevice> testDeviceArray = 
				new ArrayList<AbstractOutputDevice>();
        testDeviceArray.add(testApp);

        new ISS(testDeviceArray);
        return true;
	}
}
