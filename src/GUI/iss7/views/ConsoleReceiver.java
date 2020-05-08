package GUI.iss7.views;

import GUI.iss7.sensorSuite.Observable;

/**
 * This class acts as the console receiver component in a weather system
 * by writing sensor output on console.
 */
public class ConsoleReceiver implements Observer {
    private Observable sensorSuite;

    public ConsoleReceiver(Observable sensorSuite) {
        this.sensorSuite = sensorSuite;
    }

    @Override
    public void update() {
//        System.out.println("Wireless Vantage Pro2 Console Receivers");
//        System.out.println(sensorSuite.getData());
    }
}
