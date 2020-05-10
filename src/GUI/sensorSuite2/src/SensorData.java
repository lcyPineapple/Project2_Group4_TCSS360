package GUI.sensorSuite2.src;/*
 * Nicolas ROberts
 * TCSS 360 Group Project
 */
/**
 * @author Nicol
 * SensorData collects a line of data for a certain time period and parses the data into manageable pieces.
 *
 */
public class SensorData {
	
	/**
	 * Regulate how many parameters of data are in the string.
	 * If this number does not match the number of parsed strings in parseData method,
	 * an error will occur.
	 */
	private static final int PARAMETERS = 6; 
	
	/** Instance field to store a line of data. */
	private String myData;
	
	/** Wind Speed. */
	private String myWindSpeed;
	
	/** Wind Direction. */
	private String myWindDirection;
	
	/** Temperature. */
	private String myTemperature;
	
	/** Humidity. */
	private String myHumidity;
	
	/** Barometric Pressure */
	private String myBar;
	
	/** Daily Rain fall */
	private String myRainFall;
	
	/**
	 * Constructor to initialize a line of Sensor Data.
	 * [0] = WindSpeed, [1] = myWindDirection, [2] = myTemp, [3] = Humidity
	 * [4] = Barometric Pressure. ADD MORE AND DOCUMENT HERE AS NEEDED.
	 * 
	 * @param theData A String representing the line of data.
	 * @throws IllegalArugmentException if the file contains illegal number of parameters.
	 */
	public SensorData(String theData) {
		myData = theData;
		parseData(myData);
	}
	
	private void parseData(String myData2) {
		String[] data = myData2.split(" ");
		int length = data.length;
		if (PARAMETERS == length) {
			myWindSpeed = data[0];
	        myWindDirection = data[1];
	        myTemperature = data[2];
	        myHumidity = data[3];
	        myBar = data[4];
	        myRainFall = data[5];
		} else {
			throw new IllegalArgumentException();
		}
		
		
	}

	/** An access method for a string of Sensor Data */
	public String getData() {
		return myData;
	}
	
	/** An access method wind speed */
	public String getWindSpeed() {
		return myWindSpeed;
	}
	
	/** An access method for WindDirection */
	public String getWindDirection() {
		return myWindDirection;
	}
	
	/** An access method for temperature. */
	public String getTemp() {
		return myTemperature;
	}
	
	/** An access method for humidity */
	public String getHumidity() {
		return myHumidity;
	}
	
	/** An access method for Barometric Pressure */
	public String getBar() {
		return myBar;
	}
	
	/** An access method for daily rain fall */
	public String getDailyRain() {
		return myRainFall;
	}
	
	//ADD MORE BELOW AS NEEDED.

}
