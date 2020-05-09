package GUI.iss8.driver;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import GUI.iss8.model.AbstractOutputDevice;
import GUI.iss8.model.ISS;
import GUI.iss8.model.WeatherMonitoringApp;
import GUI.weatherStations.WeatherStation;
import GUI.WeatherStationIntegrater;
import GUI.iss8.data.WeatherData;

/**
 * Launches the ISS program.
 * @author Adam Amado
 * @version Spring 2020
 */
final class Main {
	/**
	 * Empty private constructor.
	 */
    private Main() {
    }
	
	/**
	 * 
	 * Runs the program.
	 * @param theArgs used for command line arguments
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
    public static void main(final String[] theArgs) throws FileNotFoundException, IOException {

        //Test output device used exclusively for testing purposes.
        final WeatherMonitoringApp testApp = new WeatherMonitoringApp();

        final ArrayList<AbstractOutputDevice> testDeviceArray = 
				new ArrayList<AbstractOutputDevice>();
        testDeviceArray.add(testApp);

        new ISS(testDeviceArray);
    }

}
