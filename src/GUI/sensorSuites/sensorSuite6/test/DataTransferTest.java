package weatherstation6.test;/*
 * A set of unit tests covering the serialization features of the
 * DataRelay class.
 */

import weatherstation6.src.DataRelay;
import weatherstation6.src.WeatherData.DataType;
import org.junit.Assert;
import org.junit.Test;
import weatherstation6.src.WeatherData.HistoricalDataPoint;

import java.io.*;
import java.util.*;

/**
 * Tests the DataRelay objects persistence when serialized and
 * written to local disk (or by extension when sent OTW to the
 * data console). Note that this is in addition to the easily
 * parsed txt file automatically produced by DataRelay.
 */

public class DataTransferTest {

    /** The static testCalendar used in the testTextFileGeneration method. */
    private final GregorianCalendar testCal = new GregorianCalendar(0, 0, 0, 0, 0, 0);

    /**
     * Tests the serialization of DataRelay by writing a
     * test instance to disk then recovering it and
     * verifying it's equality with the original object.
     */
    @Test
    public void testSerialization() {
        DataRelay testRelay = genRandomRelay(), reserialized = null;
        try {
            FileOutputStream out = new FileOutputStream(testRelay + "_serialized");
            ObjectOutputStream dataOut = new ObjectOutputStream(out);
            dataOut.writeObject(testRelay);

            FileInputStream in = new FileInputStream(testRelay + "_serialized");
            ObjectInputStream dataIn = new ObjectInputStream(in);
            reserialized = (DataRelay) dataIn.readObject();

            Assert.assertEquals(testRelay, reserialized);
            File clean = new File(testRelay + "_serialized");
            clean.delete();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred while creating file for DataRelay serialization.");
            Assert.fail();
        } catch (IOException e) {
            System.out.println("Error occurred while writing DataRelay to file (during serialization).");
            Assert.fail();
        } catch (ClassNotFoundException e) {
            System.out.println("Error occurred while reading DataRelay object from file (during reserialization).");
            Assert.fail();
        }
    }

    @Test
    public void testTextFileGeneration() {
        DataRelay dataSet = new DataRelay(testCal); // calendar needs to be constant or offset can adjust hourly readings
        try {
            File inputFile = new File("test10000.txt");
            Scanner s = new Scanner(inputFile);
            int iterations = 0;
            while (s.hasNext()) {
                String next = s.next();
                double data = Double.parseDouble(next);
                dataSet.acceptDataPoint(data, DataType.ALL_TYPES[iterations % DataType.ALL_TYPES.length]);
                dataSet.incrementCal(Calendar.MINUTE, 15); // simulate time passing
                iterations++;
            }
            FileInputStream relayIn = new FileInputStream(dataSet.getLastUrl());
            byte[] relayBytes = new byte[relayIn.available()];
            relayIn.read(relayBytes);

            FileInputStream testFileIn = new FileInputStream("WDATA_TEST.txt");
            byte[] testFileBytes = new byte[testFileIn.available()];
            testFileIn.read(testFileBytes);

            Assert.assertArrayEquals(relayBytes, testFileBytes);

            File clean = new File(dataSet.getLastUrl());
            clean.delete();
        } catch (IOException iox) {
            System.out.println("Error occurred while processing test file.");
            Assert.fail();
        }
    }

    /**
     * Tests the acceptDataPoint method to ensure
     * it sends appropriate readings to a certain
     * data type.
     */
    @Test
    public void testAcceptDataPoint() {
        DataRelay testRelay;
        Random gen = new Random();
        for (int i = 0; i < gen.nextInt(1000); i++) {
            testRelay = new DataRelay();
            DataType type = DataType.ALL_TYPES[gen.nextInt(DataType.ALL_TYPES.length)];
            testRelay.acceptDataPoint(1.00, type);
            for (HistoricalDataPoint dp : testRelay.getDataPoints()) {
                if (dp.getAllReadings().contains(1.0)) {
                    Assert.assertEquals(type, dp.getType());
                }
            }
        }
    }

    /**
     * Tests the increment calendar method
     * by comparing two toStrings of a calendar
     * before and after the method is called.
     */
    @Test
    public void testIncrementCal() {

        DataRelay testRelay = genRandomRelay();

        GregorianCalendar cal = testRelay.getDataPoints().get(1).getCalendar();
        String string = cal.toString();
        testRelay.incrementCal(Calendar.DAY_OF_YEAR, 11);
        String stringChanged = cal.toString();

        Assert.assertNotEquals(string,stringChanged);
    }

    /**
     * Tests the equals() method for a false
     * result when DataRelay object compared
     * to object of different class.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsFalse() {
        DataRelay testRelay = genRandomRelay();
        String string = new String();
        Assert.assertFalse(string.equals(testRelay));
    }

    /**
     * Tests the equals() method for a true result.
     */
    @Test
    public void testEqualsTrue() {
        DataRelay testRelay = genRandomRelay();
        Assert.assertTrue(testRelay.equals(testRelay));
    }

    /**
     * Tests the equals() method for a false
     * result when DataRelay object is null.
     */
    @Test
    public void testEqualsNPE() {
        DataRelay testRelay = genRandomRelay();
        DataRelay temp = null;
        Assert.assertFalse(testRelay.equals(temp));
    }

    /**
     * Creates a new DataRelay with all possible sensor types and
     * populates the data points with random values.
     * @return a new DataRelay populated with random values
     */
    private DataRelay genRandomRelay() {
        DataRelay gen = new DataRelay();
        Random numGen = new Random();
        for (HistoricalDataPoint dp : gen.getDataPoints()) {
            for (int i = 0; i < numGen.nextInt(1000); i++) {
                double data = dp.getLowerBound()
                        + (dp.getUpperBound() - dp.getLowerBound()) * numGen.nextDouble();
                gen.acceptDataPoint(data, dp.getType()); // could be direct call, included gen for clarity
            }
        }
        return gen;
    }
}
