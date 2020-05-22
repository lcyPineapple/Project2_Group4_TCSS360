/*
 * An enum that models the different types of sensor
 * data outputs.
 */

package GUI.sensorSuite6.src.WeatherData;

/**
 * Distinguishes different types of data
 * measured by all the sensors for the
 * weather station.
 *
 * @author Deline Zent
 * @version 0.0.0
 */
public enum DataType {
    /**
     * The humidity in the air.
     */
    HUMIDITY,
    /**
     * The amount of radiation in the air.
     */
    SOLAR_RADIATION,
    /**
     * The temperature in celsius/fahrenheit.
     */
    TEMPERATURE,
    /**
     * The amount of UV measured from the sun.
     */
    ULTRAVIOLET,
    /**
     * The amount of rain that has fallen in inches/mm.
     */
    RAIN_FALL,
    /**
     * The rate the rain is falling in a time frame.
     */
    RAIN_RATE,
    /**
     * The moisture levels in the soil.
     */
    SOIL_MOISTURE,
    /**
     * The direction of the wind.
     */
    WIND_DIRECTION,
    /**
     * The speed of the wind.
     */
    WIND_SPEED;

    /**
     * An array containing all types associated with the DataType
     * enum.
     */
    public static final DataType[] ALL_TYPES = new DataType[]{
            DataType.HUMIDITY, DataType.TEMPERATURE, DataType.SOIL_MOISTURE,
            DataType.RAIN_FALL, DataType.ULTRAVIOLET, DataType.SOLAR_RADIATION,
            DataType.WIND_DIRECTION, DataType.WIND_SPEED, DataType.RAIN_RATE};

    /**
     * Returns a string representation of this DataType.
     *
     * @return a string describing the DataType
     */
    public String toString() {
        String decriptor;
        switch (this) {
            case HUMIDITY:
                decriptor = "humidity";
                break;
            case SOLAR_RADIATION:
                decriptor = "solar radiation";
                break;
            case TEMPERATURE:
                decriptor = "temperature";
                break;
            case ULTRAVIOLET:
                decriptor = "ultra violet";
                break;
            case RAIN_FALL:
                decriptor = "rain fall";
                break;
            case RAIN_RATE:
                decriptor = "rain rate";
                break;
            case SOIL_MOISTURE:
                decriptor = "soil moisture";
                break;
            case WIND_DIRECTION:
                decriptor = "wind direction";
                break;
            case WIND_SPEED:
                decriptor = "wind speed";
                break;
            default:
                decriptor = "null DataType";
                break;
        }
        return decriptor;
    }
}
