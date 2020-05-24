package GUI.weatherStations;

import GUI.WeatherStationIntegrater;
import GUI.sensorSuite2.src.Sensors;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A weather station integration for group 3 weather station. Provides functionality
 * needed for an integrater.
 *
 * @author Daniel Machen
 * @version 5/24/2020
 */
public class WeatherStation2 extends WeatherStation {
    public static final int UPDATE_INTERVAL = 1000 * 10;
    public static Thread myServer;
    public static Thread myUpdater;

    public WeatherStation2(WeatherStationIntegrater integrater) {
        super(integrater);
    }

    @Override
    public void run() throws InterruptedException {
        // Kick up the sensor server running on port 9876
        myServer = new Thread(() -> {
            try {
                Sensors.main(new String[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        myServer.start();
        // Start the data giving thread
        myUpdater = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(UPDATE_INTERVAL);
                    getDataAndUpdate("127.0.0.1", 9876);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        myUpdater.start();
    }

    // Kills the processes
    public void kill() {
        if (myUpdater != null) {
            myUpdater.interrupt();
        }
        if (myServer != null) {
            myServer.interrupt();
        }
    }

    /**
     * Connects to a server that provides weather data and passes it along
     *
     * @param theHost - The host of the server
     * @param thePort - The port of the server
     */
    private void getDataAndUpdate(String theHost, int thePort) {
        try {
            Socket socket = new Socket(theHost, thePort);
            Scanner in = new Scanner(socket.getInputStream());

            int windSpeed = in.nextInt();
            String direction = in.next();
            int temperature = in.nextInt();
            int humidity = in.nextInt();
            int bar = in.nextInt();
            int rain = in.nextInt();
            update(Arrays.asList((double) humidity,
                    (double) temperature,
                    (double) windSpeed,
                    (double) rain));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}