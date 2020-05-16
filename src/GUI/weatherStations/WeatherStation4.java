package GUI.weatherStations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import GUI.WeatherStationIntegrater;
import GUI.iss7.sensorSuite.SensorSuite;
import GUI.iss7.views.ConsoleReceiver;


/**
 * Weather Station for ISS7.
 * @author yolandaxu
 *
 */
public class WeatherStation4 extends WeatherStation{
	
    private static SensorSuite sensorSuite;
    private static ConsoleReceiver consoleReceiver;
    File f = GUI.iss7.sensorSuite.SensorSuite.OUT4;
    Scanner sc = new Scanner(f);

	public WeatherStation4(WeatherStationIntegrater integrater) throws Exception {
		super(integrater);
	}

	@Override
	public void run() throws Exception {
        Thread integraterThread = new Thread(() -> {
            try {
                while (true) {
                    setUp();
                    sensorSuite.run();
                }
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        integraterThread.start();
        Thread integraterThread2 = new Thread(() -> {
            try {
                while (true) {
                	Thread.sleep(3000);
                	updateList();
                }
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        integraterThread2.start();
	}
	
	
    private boolean setUp() throws Exception {
        sensorSuite = new SensorSuite();
        consoleReceiver = new ConsoleReceiver(sensorSuite);
        sensorSuite.addObserver(consoleReceiver);
        return true;
    }
    
    private List<Double> updateList() {
        List<Double> weatherDataList4 = new ArrayList<>();
        weatherDataList4.add(sc.nextDouble());
        weatherDataList4.add(sc.nextDouble());
        weatherDataList4.add(sc.nextDouble());
        weatherDataList4.add(sc.nextDouble());
        super.update(weatherDataList4);
        return weatherDataList4;
    }
    
}