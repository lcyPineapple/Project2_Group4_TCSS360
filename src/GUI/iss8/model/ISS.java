package GUI.iss8.model;

import GUI.iss8.data.WeatherData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * This class uses the simulated data classes and serializes them and then de-serializes them
 * to simulate the act of the data be being sent from the sensors to the ISS program.
 *
 * @author Dean Kelley
 * @author yolandaxu
 * <p>
 * Rewritten to run sensor data collection on a separate thread, and
 * to accept more generalized output devices in varying numbers.
 * Now exclusively 'writes' the serialized file.
 * @author Maxfield England
 * <p>
 * Changed write method to a synchronized method to reduce issues with
 * concurrency.
 * @author Leika Yamada
 * @version Spring 2020
 * <p>
 * Added rainfall, humidity, weatherDataList, console receiver, and envoy.
 */
public class ISS {
    /**
     * myWeatherThread - used to run the thread and receive update data
     */
    final private WeatherData myWeatherThread;
    /**
     * myDevices - the list of all OutputDevices connected to the ISS
     */
    final private List<AbstractOutputDevice> myDevices;

    /**
     * A runnable constructor for the ISS class; takes connected output devices
     * as a list of output device objects.
     *
     * @param theOutputs A collection of output devices attached to the ISS.
     */
    public ISS(final List<AbstractOutputDevice> theOutputs) {
        this.myDevices = theOutputs;

        myWeatherThread = new WeatherData();
        final Runnable myRunTimer = () -> {
            try {
                final Timer weatherTimer = new Timer();
                weatherTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        write();
                    }
                }, GUI.iss8.res.R.Integers.TWOFIVETHOU, GUI.iss8.res.R.Integers.TWOFIVETHOU);
            } catch (Exception e) {
                // TODO: handle exception
            }
        };
        final Thread myDataThread = new Thread(myRunTimer);
        myWeatherThread.start();
        myDataThread.start();
    }


    /**
     * Writes serialized data from sensors to weather file, and then notifies
     * all connected output devices of the update.
     */
    public synchronized void write() {
        try {

            final FileOutputStream fileOutputStream = new FileOutputStream("weather.ser");
            final ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(myWeatherThread);
            out.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Update every one of our output devices with the new data
        //Update behavior is defined by the specific output device.
        for (final AbstractOutputDevice output : myDevices) {
            output.ping("weather.ser");
        }
    }
}