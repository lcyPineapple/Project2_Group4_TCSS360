/*
 * A test case demonstrating the warning function of the
 * SolarRadiation class.
 */
package GUI.sensorSuite6.test;

import GUI.sensorSuite6.src.WeatherData.Sensor;
import GUI.sensorSuite6.src.WeatherData.SolarRadiation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author Paras Sharma
 */
public class SolarRadiationTest {
    /**
     * Test method for {@link WeatherData.UltraViolet#alarmWarning(double)}.
     */
    @Test
    public void testAlarmWarning() {
        String expected = "WARNING!, Higher Radiations than usual";
        SolarRadiation test = new SolarRadiation(Sensor.OUTSIDE);
        String actual = test.alarmWarning(200);
        Assert.assertEquals(expected, actual);
    }

}

