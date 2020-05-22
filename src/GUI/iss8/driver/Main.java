package GUI.iss8.driver;

import GUI.iss8.model.AbstractOutputDevice;
import GUI.iss8.model.ISS;
import GUI.iss8.model.WeatherMonitoringApp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Launches the ISS program.
 *
 * @author Adam Amado
 * @version Spring 2020
 * <p>
 * <p>
 * Comment by Leika Yamada: This class is not used by the GUI Console in weather station integration.
 * The reason is that this class was created for the purposes of demonstration by the original team.
 * It was included in the final project as a part of the original code, and integrated
 * and renamed as WeatherStation5. The original group name was group 8.
 */
final class Main {
    /**
     * Empty private constructor.
     */
    private Main() {
    }

    /**
     * Runs the program.
     *
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
