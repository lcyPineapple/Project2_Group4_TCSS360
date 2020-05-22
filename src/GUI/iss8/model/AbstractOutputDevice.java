package GUI.iss8.model;

import GUI.iss8.data.WeatherData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Output devices that obtains information from the ISS.
 *
 * @author yolandaxu
 * @author Maxfield England
 * @version Spring 2020
 * <p>
 * Promoted to parent abstract class with more generic behavior, that extends
 * to all child output devices.
 */
public abstract class AbstractOutputDevice {
    /**
     * CalculatedData object to determine calculated metrics.
     */
    private final CalculatedData myCalculatedData;
    /**
     * WeatherData object to read values into.
     */
    private WeatherData myReadWeatherData;
    /**
     * The weather information stored in the Output.
     */
    private List<String> myOutputWeatherData;

    /**
     * Constructor super object.
     */
    public AbstractOutputDevice() {
        myCalculatedData = new CalculatedData();
    }

    /**
     * Gets the weather information from the Output.
     *
     * @return the weather information
     */
    public List<String> getOutputData() {
        return (ArrayList<String>) myOutputWeatherData;
    }

    /**
     * Reads serialized data from weather file, and updates the OutputDevice's
     * state of weather data.
     *
     * @param theFileName - file name
     */
    public void read(final String theFileName) {
        try {
            //Read the serialized array of weather data from weather.ser
            final FileInputStream fileInputStream = new FileInputStream(theFileName);
            final ObjectInputStream in = new ObjectInputStream(fileInputStream);
            myReadWeatherData = (WeatherData) in.readObject();
            in.close();
            fileInputStream.close();
            final ArrayList<String> dataList = new ArrayList<String>();

            dataList.add(Integer.toString(myReadWeatherData.getAirTemperatures()));
            dataList.add(Integer.toString(myCalculatedData.windChill(myReadWeatherData.
                    getAirTemperatures(), myReadWeatherData.getWindSpeed())));
            dataList.add(Integer.toString(myCalculatedData.heatIndex(myReadWeatherData.
                    getAirTemperatures(), myReadWeatherData.getHumidity(), GUI.iss8.res.R.Strings.F)));
            dataList.add(Integer.toString(myCalculatedData.dewPoint(myReadWeatherData.
                    getAirTemperatures(), myReadWeatherData.getHumidity(), GUI.iss8.res.R.Strings.F)));
            dataList.add(Integer.toString(myReadWeatherData.
                    getGroundThermometerTemperature()));
            dataList.add(Double.toString(myReadWeatherData.getPressure()));
            dataList.add(Integer.toString(myReadWeatherData.getWindSpeed()));
            dataList.add(myReadWeatherData.getWindDirection());
            dataList.add(Integer.toString(myReadWeatherData.getRainfall()));
            dataList.add(Integer.toString(myReadWeatherData.getHumidity()));

            myOutputWeatherData = dataList;
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException c) {
            c.printStackTrace();
        }
    }

    /**
     * Returns full WeatherData object.
     *
     * @return WeatherData Object
     */
    public WeatherData getWeatherData() {
        return myReadWeatherData;
    }

    /**
     * Notifies the object that new data is available for update;
     * the object can read the file and must have some child-defined
     * behavior for handling the incoming new data.
     *
     * @param theFileName The string representation of the name of the file
     *                    to be read
     */
    public abstract void ping(String theFileName);
}
