package GUI.iss8.tests;

import GUI.iss8.model.CalculatedData;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * This cass runs unit tests on each of the methods in the CalculateData class to
 * ensure that they work correctly.
 *
 * @author Dean Kelley
 * @version Spring 2020
 */
public class CalculationUnitTests {
    /**
     * calculatedData object.
     */
    private final CalculatedData myData = new CalculatedData();

    /**
     * Tests the conversion method for Fahrenheit and Celsius.
     */
    @Test
    void convertTempTests() {
        /** Temperature in Fahrenheit */
        final int F1 = 80;
        /** Temperature in Celsius */
        final int C1 = 27;
        assertEquals(GUI.iss8.res.R.Strings.RSM, C1, CalculatedData.convertTemp(F1, GUI.iss8.res.R.
                Strings.C), 0);
        /** Temperature in Fahrenheit */
        final int F2 = 104;
        /** Temperature in Celsius */
        final int C2 = 40;
        assertEquals(GUI.iss8.res.R.Strings.RSM, F2, CalculatedData.convertTemp(C2, GUI.iss8.res.R.Strings.
                F), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, -273, CalculatedData.convertTemp(C2, "a"), 0);
    }

    /**
     * Tests the conversion method for pGUI.iss8.ressure.
     */
    @Test
    void convertPessureTests() {
        final double inHG = 30.0;
        final double mmHG = 762.0;
        final double Pa = 101591.7;
        final double hPa = 1015.92;
        final double mBar = 1015.92;
        assertEquals(GUI.iss8.res.R.Strings.RSM, Pa, myData.convertPressure(inHG, GUI.iss8.res.R.Strings.
                PA), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, mmHG, myData.convertPressure(inHG, GUI.iss8.res.R.Strings.
                MMHG), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, hPa, myData.convertPressure(inHG, GUI.iss8.res.R.Strings.
                HPA), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, mBar, myData.convertPressure(inHG, GUI.iss8.res.R.Strings.
                MBAR), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, inHG, myData.convertPressure(inHG, GUI.iss8.res.R.Strings.
                INHG), 0);
    }

    /**
     * Tests the dew point method for temperatuGUI.iss8.res in both Fahrenheit and Celsius.
     */
    @Test
    void dewPointTests() {
        final int RH = 70;
        final int F1 = 80;
        final int FDP1 = 70;
        final int F2 = 41;
        final int FDP2 = 32;
        final int C1 = 30;
        final int CDP1 = 24;
        final int C2 = 10;
        final int CDP2 = 5;
        assertEquals(GUI.iss8.res.R.Strings.RSM, FDP1, myData.dewPoint(F1, RH, GUI.iss8.res.R.
                Strings.F), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, FDP2, myData.dewPoint(F2, RH, GUI.iss8.res.R.
                Strings.F), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, CDP1, myData.dewPoint(C1, RH, GUI.iss8.res.R.
                Strings.C), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, CDP2, myData.dewPoint(C2, RH, GUI.iss8.res.R.
                Strings.C), 0);
    }

    /**
     * Tests the heat index method for both Fahrenheit and Celsius.
     */
    @Test
    void heatIndexTests() {
        final int RH1 = 70;
        final int F1 = 80;
        final int FHI1 = 83;
        final int F2 = 82;
        final int FHI2 = 79;
        final int F3 = 70;
        final int FHI3 = 70;
        final int RH2 = 12;
        final int C1 = 30;
        final int CHI1 = 41;
        final int CHI2 = 28;
        final int RH3 = 90;
        assertEquals(GUI.iss8.res.R.Strings.RSM, FHI1, myData.heatIndex(F1, RH1, GUI.iss8.res.R.
                Strings.F), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, FHI2, myData.heatIndex(F2, RH2, GUI.iss8.res.R.
                Strings.F), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, FHI3, myData.heatIndex(F3, RH1, GUI.iss8.res.R.
                Strings.F), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, CHI1, myData.heatIndex(C1, RH3, GUI.iss8.res.R.
                Strings.C), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, CHI2, myData.heatIndex(C1, RH2, GUI.iss8.res.R.
                Strings.C), 0);
    }

    /**
     * Tests the wind speed method.
     */
    @Test
    void convertWindSpeedTests() {
        final int mph = 50;
        final int kph = 80;
        final int mps = 22;
        final int knot = 43;
        assertEquals(GUI.iss8.res.R.Strings.RSM, kph, myData.convertWindSpeed(mph, GUI.iss8.res.R.
                Strings.KPH), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, mps, myData.convertWindSpeed(mph, GUI.iss8.res.R.
                Strings.MPS), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, knot, myData.convertWindSpeed(mph, GUI.iss8.res.R.
                Strings.KNOT), 0);
    }

    /**
     * Tests wind chill method.
     */
    @Test
    void windChillTest() {
        final int T1 = 30;
        final int T2 = 46;
        final int V = 20;
        final int WC1 = 17;
        final int WC2 = 46;
        assertEquals(GUI.iss8.res.R.Strings.RSM, WC1, myData.windChill(T1, V), 0);
        assertEquals(GUI.iss8.res.R.Strings.RSM, WC2, myData.windChill(T2, V), 0);
    }
}
