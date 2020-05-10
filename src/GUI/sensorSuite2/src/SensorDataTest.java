package GUI.sensorSuite2.src;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test Cases for the Sensor Data Class.
 * @author Nicol
 *
 */
public class SensorDataTest {
	
	
	private static final String TEST_DATA = "29 Southwest 64 44 51 1";
	
	private static final String WRONG_DATA = "4 29 Southwest 64 44 51 1";
	
	private SensorData mySensorData;
	

    /**
     * Creating instances of item to use throughout JUnit testing. Runs before every test case.
     */
	@Before
	public void setUp() {
		mySensorData = new SensorData(TEST_DATA);
      

   
  }
	

	/**
	 * Testing the parse data method for illegal argument exception.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testParseDataIllegalArg() {
		new SensorData(WRONG_DATA);        
	}
	
	/**
	 * Test for getters.
	 */
	@Test 
	public void testData1() {
		assertEquals("29", mySensorData.getWindSpeed());       
	}
	
	/**
	 * Test for getters.
	 */
	@Test 
	public void testData2() {
		assertEquals("Southwest", mySensorData.getWindDirection());       
	}
	
	/**
	 * Test for getters.
	 */
	@Test 
	public void testDataLine() {
		assertEquals(TEST_DATA, mySensorData.getData());  
	}
	
	/**
	 * Test for getters.
	 */
	@Test 
	public void testData3() {
		assertEquals("64", mySensorData.getTemp());  
	}
	
	/**
	 * Test for getters.
	 */
	@Test 
	public void testData4() {
		assertEquals("44", mySensorData.getHumidity());  
	}
	
	/**
	 * Test for getters.
	 */
	@Test 
	public void testData5() {
		assertEquals("51", mySensorData.getBar());  
	}
	
	/**
	 * Test for getters.
	 */
	@Test 
	public void testData6() {
		assertEquals("1", mySensorData.getDailyRain());  
	}
  
  
}



	
