package GUI.weatherStations;

import java.util.ArrayList;
import java.util.List;

import GUI.WeatherStationIntegrater;
import GUI.iss7.sensorSuite.SensorSuite;
import GUI.iss7.views.ConsoleReceiver;

public class WeatherStation4 extends WeatherStation{
//	public static List<Double> weatherDataList7 = new ArrayList<>();;
    private static SensorSuite sensorSuite;
    private static ConsoleReceiver consoleReceiver;

	public WeatherStation4(WeatherStationIntegrater integrater) {
		super(integrater);
		
	}

	@Override
	public void run() throws InterruptedException {
		
        Thread integraterThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(2500);
                    setUp();
                    startSensorSuite();
                }
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        });
        integraterThread.start();
		
	}
	
    private static void setUp() {
        sensorSuite = new SensorSuite();
        consoleReceiver = new ConsoleReceiver(sensorSuite);
        sensorSuite.addObserver(consoleReceiver);
    }

    private static void startSensorSuite() throws InterruptedException {
        sensorSuite.run();
    }
    
//    public void setWeatherDataList(List<Double> theList) {
//    	weatherDataList = theList;
//    	for (Double data: weatherDataList) {
//    		System.out.print(data + " ");
//    	}
//    }

}
