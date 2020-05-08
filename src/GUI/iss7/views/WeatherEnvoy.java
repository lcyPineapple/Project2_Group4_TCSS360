package GUI.iss7.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import GUI.iss7.sensorSuite.Observable;

/**
 * This class acts as the weather envoy component in a weather system
 * by writing sensor output to a file.
 */
public class WeatherEnvoy implements Observer {

	/**
	 * The sensor suite that the envoy receives updates from.
	 */
    private Observable sensorSuite;
    
    /**
     * The file name that the weather envoy writes sensor data to.
     */
    private static final String FILE_NAME = "sensor-suite-data.txt";
    
	private PrintWriter writer;

    /**
     * Constructor for a weather envoy.
     * @param sensorSuite The sensor suite to observe.
     */
    public WeatherEnvoy(Observable sensorSuite) {
		this.sensorSuite = sensorSuite;
        try {
			this.writer = new PrintWriter(FILE_NAME, StandardCharsets.UTF_8);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to write output file.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}
    }

    /**
     * Appends sensor suite data to "sensor-suite-data.txt" after every update.
     */
    @Override
    public void update() {
    	writer.println("Wireless Vantage Pro2 Console Receivers");
		writer.println(sensorSuite.getData());
		writer.flush();
    }
}
