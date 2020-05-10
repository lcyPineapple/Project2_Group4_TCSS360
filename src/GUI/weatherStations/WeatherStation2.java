package GUI.weatherStations;

import GUI.WeatherStationIntegrater;
import GUI.sensorSuite2.src.Sensors;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class WeatherStation2 extends WeatherStation {
    public static final int UPDATE_INTERVAL = 1000 * 10;

    public WeatherStation2(WeatherStationIntegrater integrater) {
        super(integrater);
    }

    @Override
    public void run() throws InterruptedException {
        // Kick up the sensor server running on port 9876
        new Thread(() -> {
            try {
                Sensors.main(new String[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        //
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(UPDATE_INTERVAL);
                    getDataAndUpdate("127.0.0.1", 9876);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }

    private void getDataAndUpdate(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
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
