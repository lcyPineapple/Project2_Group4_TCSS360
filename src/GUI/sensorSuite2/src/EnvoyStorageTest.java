package GUI.sensorSuite2.src;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/*
 * @author Faiz Ahmed
 *
 */

class EnvoyStorageTest {

    private ArrayList<SensorData> myStorageTest;

    /*
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        myStorageTest = new ArrayList<SensorData>();
    }

    /*
     * Test method for {@link bitbuckettest.EnvoyStorage#getStorage()}.
     */
    @Test
    void testGetStorage() {
        ArrayList<SensorData> myTestStorage2 = new ArrayList<SensorData>();
        assertEquals(myStorageTest, myTestStorage2);
    }

    /**
     * Tests the toString method on EnvoyStorage
     */
    @Test
    void testToString() {
        String result = myStorageTest.toString();
        assertEquals(myStorageTest.toString(), result);
    }

}
