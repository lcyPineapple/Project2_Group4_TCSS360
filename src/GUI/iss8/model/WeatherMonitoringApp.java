package GUI.iss8.model;

//import java.io.File;
import java.util.ArrayList;

/**
 * This class is the weather monitoring application that gets the data from
 * the Console Receiver or envoy.
 * @author yolandaxu
 *
 * Updated as a child of the OutputDevice class and modified to be able
 * to be notified by the ISS for data collection, handled in parent behavior.
 * @author Maxfield England
 *
 */
public class WeatherMonitoringApp extends AbstractOutputDevice {
	
	/**
	 * Prints header information for continuous console output.
	 */
	public WeatherMonitoringApp() {
		System.out.println("Air Temp\tWind Chill\tHeat Index\tDew Point\tGround Temp"
				+ "\tAir Pressure\tWind Speed\tWind Direction\tRainfall\tHumidity");
		System.out.println("(F)\t\t(F)\t\t(F)\t\t(F)\t\t(F)\t\t(inHg)\t\t(mph)\t\t" 
				+ "(NSEW)\t\t(in)\t\t(%)");
		System.out.println("---------------------------------------------------------" 
				+ "------------------------------------------------------------------"
				+ "-------------------------------");
		
	}
	
	/**
	 * Prints out the weather data.
	 * @param data the weather data
	 */
	public void printData(ArrayList<String> data) {
		for (String s : data) {
			System.out.print(s + "\t\t");
		}
		System.out.println();
	}
	
	/**
	 * Reads file data when notified by the ISS.
	 * Prints the received data from file.
	 */
	public void ping(String fileName) {
		read(fileName);
		printData((ArrayList<String>) this.getOutputData());
		
	}

}
