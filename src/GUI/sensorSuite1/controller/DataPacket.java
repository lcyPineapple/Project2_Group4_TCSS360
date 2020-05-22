/*
 * DataPacket class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.controller;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author Gregory Hablutzel
 * @version 1.0
 * This class represents the data packets for the VantagePro2 Weather Station.
 * These data packets are stored in treesets for each sensor or calculation thread.
 * Currently, we serialized the last 60 seconds of the treeset for each thread,
 * but in the future we can serialized these data packets as well.
 * <p>
 * These data packets store the time of the measurement, sensor name, sensor measurement,
 * and the measurement itself.
 * <p>
 * It has various getters, and also a toString to print its contents.
 * Data Packets are sorted using the Comparable interface and the eventTime.
 */

public class DataPacket<T> implements Comparable<DataPacket<T>>, Serializable {

    private static final long serialVersionUID = 1L;
    // protected means that this class and any subclasses may access the property.
    protected ZonedDateTime eventTime;
    protected String sensor;
    protected String measurement;
    protected T value;

    /**
     * Construct a Data Packet.
     *
     * @param eventTime:   time measurement recorded.
     * @param sensor:      sensor type
     * @param measurement: name of measurement.
     * @param value:       value recorded.
     * @throws IllegalArgumentException if eventTime is null
     * @throws IllegalArgumentException if sensor is null
     * @throws IllegalArgumentException if measurement is null
     */
    public DataPacket(ZonedDateTime eventTime, String sensor, String measurement, T value) {
        if (eventTime == null) {
            throw new IllegalArgumentException("eventTime cannot be null");
        }
        if (sensor == null) {
            throw new IllegalArgumentException("sensor cannot be null");
        }
        if (measurement == null) {
            throw new IllegalArgumentException("measurement cannot be null");
        }
        this.eventTime = eventTime;
        this.measurement = measurement;
        this.value = value;
        this.sensor = sensor;
    }

    /**
     * Sorted order based off of event time.
     */
    @Override
    public int compareTo(DataPacket<T> o) {
        return eventTime.compareTo(o.eventTime);
    }

    /*
     * returns sensor type for the given data packet
     */
    public String getSensor() {
        return sensor;
    }

    /*
     * returns the measurement description for the given data packet
     */
    public String getMeasurement() {
        return measurement;
    }

    /*
     * returns value for the given data packet
     */
    public T getValue() {
        return value;
    }

    /*
     * return time when data packet was recorded
     */
    public ZonedDateTime getEventTime() {
        return eventTime;
    }

    /*
     * Return a string representation of the value the data packet holds.
     */
    public String toString() {
        return "[" + value + "]";
    }

}
