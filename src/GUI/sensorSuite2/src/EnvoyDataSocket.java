package GUI.sensorSuite2.src;/*
 * Nicolas Roberts
 * TCSS 360 Group Project 
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;


/**
 * @author Nicol
 * Gathers the respective weather data from the ISS data sensors wireless connection.
 *
 */
public class EnvoyDataSocket {
	
	/**
	 * A constant that will determine how much data is given in the output file.
	 */
	private static final int DATA_FREQUENCY = 365;
	
	/**
	 * A constant that determines the interval data is gathered from the Sensors.
	 * Time interval is measured in milliseconds.
	 */
	private static final int TIME_INTERVAL = 1;
	
	/**
	 * The correct data socket number.
	 */
	private static final int SOCKET = 9876;
	
	/**
	 * Correct file path where the .txt file will show up.
	 */
	private static final String FILEPATH = "src/SensorData.txt";

	/**
	 * Accepts server socket to transfer data file.
	 * @param theArgs used for command line input.
	 */
	public static void main(String[] theArgs) throws IOException, ClassNotFoundException {
		
		new Thread(new Runnable() { //at a given interval data will write to output file	
            public void run() {
            	
            	//file to write output data to.
            	EnvoyDataIO output = new EnvoyDataIO();
            	//PrintStream outputFile = output.getOutputStream("//Desktop");
            	PrintStream outputFile = output.getOutputStream(FILEPATH);

            	//fills output file.
            	try {
					output.fillFile(SOCKET, DATA_FREQUENCY, TIME_INTERVAL, outputFile);
				} catch (ConnectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            	//Storage
                EnvoyStorage st = new EnvoyStorage();
                try {
					st.getMyWeatherData(FILEPATH);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //System.out.println(st.toString());
            }
            
            
        }).start();
		     
	}

}
