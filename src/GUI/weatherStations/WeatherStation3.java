package GUI.weatherStations;

import GUI.WeatherStationIntegrater;
import GUI.sensorSuites.sensorSuite6.src.DataRelay;
import GUI.sensorSuites.sensorSuite6.src.WeatherData.DataType;
import GUI.sensorSuites.sensorSuite6.src.WeatherData.Sensor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WeatherStation3 extends WeatherStation {
    private static final int INTERVAL = 5000;

    public WeatherStation3(WeatherStationIntegrater integrater) {
        super(integrater);
    }

    /**
     * Read data every 5 seconds.
     */
    public void run() {
        Thread integraterThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(INTERVAL);
                    readData();
                }
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        });
        integraterThread.start();
    }

    private void readData() {
        DataRelay dataSet = new DataRelay(DataType.ALL_TYPES, Sensor.OUTSIDE);
        String inputFileLocation = "test10000.txt";
        try {
            File inputFile = new File(inputFileLocation);
            Scanner s = new Scanner(inputFile);
            int iterations = 0;
            while (s.hasNext()) {
                String next = s.next();
                Double data = Double.parseDouble(next);
                dataSet.acceptDataPoint(data, DataType.ALL_TYPES[iterations % DataType.ALL_TYPES.length]);
                dataSet.incrementCal(Calendar.MINUTE, 15); // simulate time passing
                List<Double> fakeData = new ArrayList<>();
                Random random = new Random();
                fakeData.add(random.nextDouble() * 10);
                fakeData.add(random.nextDouble() * 40);
                fakeData.add(random.nextDouble() * 60);
                fakeData.add(random.nextDouble() * 20);
                fakeData.add(random.nextDouble() * 70);
                super.update(fakeData);
                iterations++;
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File can not be found.");
        } catch (NumberFormatException e) {
            System.out.println("File contains invalid data, please enter only numbers.");
        }
    }
}
