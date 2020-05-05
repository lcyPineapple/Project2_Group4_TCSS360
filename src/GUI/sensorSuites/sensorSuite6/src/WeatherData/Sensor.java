/*
 * An enum that models the different types of sensors
 * that are available on the weather station.
 */

package GUI.sensorSuites.sensorSuite6.src.WeatherData;

/**
 * Distinguishes different types of sensor based on
 * their respective location in the system, see
 * station specification for details.
 * @author Spencer Little
 * @version 0.0.0
 */
public enum Sensor {
    /** WeatherData.Sensor located in console (see spec for details). */
    INSIDE,
    /** WeatherData.Sensor located in ISS. */
    OUTSIDE,
    /** WeatherData.Sensor located inside respective station. */
    EXTRA
}
