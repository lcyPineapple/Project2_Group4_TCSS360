package GUI.weatherStations;

import GUI.WeatherStationIntegrater;
import GUI.sensorSuite6.src.DataRelay;
import GUI.sensorSuite6.src.WeatherData.DataType;
import GUI.sensorSuite6.src.WeatherData.HistoricalDataPoint;
import GUI.sensorSuite6.src.WeatherData.Sensor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WeatherStation3 extends WeatherStation {
    private static final int INTERVAL = 10000;
    private Map<String, Integer> indexMap;
    private Thread integraterThread;

    public WeatherStation3(WeatherStationIntegrater integrater) {
        super(integrater);
        this.indexMap = new HashMap<>();
        indexMap.put("humidity", 0);
        indexMap.put("temperature", 1);
        indexMap.put("wind speed", 2);
        indexMap.put("rain fall", 3);
    }

    /**
     * Read data every 5 seconds.
     */
    @Override
    public void run() {
        integraterThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(INTERVAL);
                    readData();
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });
        integraterThread.start();
    }

    /**
     * Interrupt thread.
     */
    @Override
    public void kill() {
        if (integraterThread != null) {
            integraterThread.interrupt();
        }
    }

    public void readData() {
        DataRelay dataSet = new DataRelay(DataType.ALL_TYPES, Sensor.OUTSIDE);
        String inputFileLocation = "test10000.txt";
        try {
            File inputFile = new File(inputFileLocation);
            Scanner s = new Scanner(inputFile);
            int iterations = 0;
            Double[] weatherDataList = new Double[4];
            while (s.hasNext()) {
                String next = s.next();
                Double data = Double.parseDouble(next);
                dataSet.acceptDataPoint(data, DataType.ALL_TYPES[iterations % DataType.ALL_TYPES.length]);
                dataSet.incrementCal(Calendar.MINUTE, 15); // simulate time passing
                iterations++;
            }
            for (HistoricalDataPoint dp : dataSet.getDataPoints()) {
                String type = dp.getType().toString();
                if (indexMap.containsKey(type)) {
                    weatherDataList[indexMap.get(type)] = dp.getYearlyLow();
                }
            }
            super.update(Arrays.asList(weatherDataList));
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File can not be found.");
        } catch (NumberFormatException e) {
            System.out.println("File contains invalid data, please enter only numbers.");
        }
    }
}
