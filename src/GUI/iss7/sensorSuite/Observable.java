package GUI.iss7.sensorSuite;

import GUI.iss7.views.Observer;

public interface Observable {
    void addObserver(Observer observer);
    void notifyAllObservers();
    String getData();
}
