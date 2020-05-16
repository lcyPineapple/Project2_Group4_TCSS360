package tst;

import GUI.GraphComponent;
import GUI.forecast.Forecast;
import GUI.forecast.weathers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphComponentTest {

    @Test
    void testAddData() {
        GraphComponent a =new GraphComponent(24,24,"Temp","Hrs");
        a.addDataPoint(0,0);
    }

    @Test
    void testClearData() {
        GraphComponent a =new GraphComponent(24,24,"Temp","Hrs");
        a.addDataPoint(0,0);
        a.resetData();
    }
    @Test
    void testSetMinimum() {
        GraphComponent a =new GraphComponent(24,24,"Temp","Hrs");
        a.setMinimum(-100);
    }

    @Test
    void testSetMinimumInvalid() {
        GraphComponent a =new GraphComponent(24,24,"Temp","Hrs");
        assertThrows(IllegalArgumentException.class, () -> {
            a.setMinimum(100);
        });
    }

    @Test
    void testSetMaximum() {
        GraphComponent a =new GraphComponent(24,24,"Temp","Hrs");
        a.setMaximum(100);
    }


    @Test
    void testSetMaximumInvalid() {
        GraphComponent a =new GraphComponent(24,24,"Temp","Hrs");
        assertThrows(IllegalArgumentException.class, () -> {
            a.setMaximum(-100);
        });
    }

    @Test
    void testOffsetIncrementZeroElements() {
        GraphComponent a =new GraphComponent(24,24,"Temp","Hrs");
        a.incrementOffset(1);
    }


    @Test
    void testOffsetIncrementOneElement() {
        GraphComponent a =new GraphComponent(24,24,"Temp","Hrs");
        a.addDataPoint(0,0);
        a.incrementOffset(1);
    }

    @Test
    void testOffsetIncrementInvalid() {
        GraphComponent a =new GraphComponent(24,24,"Temp","Hrs");
        assertThrows(IllegalArgumentException.class, () -> {
            a.incrementOffset(-1);
        });
    }
}
