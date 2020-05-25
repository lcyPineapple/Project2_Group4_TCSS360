package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sun.jdi.Method;

import GUI.WirelessConsole;

/**
 * Moon Phase Tests
 *
 * @author Leika Yamada
 */

class MoonPhaseTest {

	/**This test makes sure that a number between 0 and 7 is chosen 
	 * to determine the phase of the moon.
	 * @throws Exception 
	 * */
	@Test
	void checkPhasetest() throws Exception {
			WirelessConsole myConsole = new WirelessConsole();
			int myNum = myConsole.checkPhase();
			assertTrue(myNum >= 0);
			assertTrue(myNum < 8);
			
	}

	/**This test ensures the moon icons are loaded.
	 * @throws Exception 
	 * */
	@Test
	void loadMoonIconsTest() throws Exception {
			WirelessConsole myConsole = new WirelessConsole();
			try {
				myConsole.loadMoonIcons();
			}catch(Exception e) {
				fail("Moon Icons were failed to load");
			}
	}
	/**This test makes sure that the GUI is loaded with the moon icon and lable.
	 * @throws Exception 
	 * */
	@Test
	void updateMoonPhaseIconTest() throws Exception{
			WirelessConsole myConsole = new WirelessConsole();
			try {
				myConsole.updateMoonPhaseIconAndLabel();
			}catch(Exception e) {
				fail("Moon phase was not updated");
			}
	}
	/**This tests the overloaded version of the updateMoonPhaseIconAndLabel.
	 * This method only exists as a demonstration that the original version
	 * tested above works properly for all branches. The original depends on
	 * random number generation called internally so all branches cannot be
	 * tested.
	 * @throws Exception 
	 * */
	@Test
	void updateMoonPhaseIconOverloadedVersionTest() throws Exception{
			WirelessConsole myConsole = new WirelessConsole();
			try {
				myConsole.updateMoonPhaseIconAndLabel(0);
				myConsole.updateMoonPhaseIconAndLabel(1);
				myConsole.updateMoonPhaseIconAndLabel(2);
				myConsole.updateMoonPhaseIconAndLabel(3);
				myConsole.updateMoonPhaseIconAndLabel(4);
				myConsole.updateMoonPhaseIconAndLabel(5);
				myConsole.updateMoonPhaseIconAndLabel(6);
				myConsole.updateMoonPhaseIconAndLabel(7);
			}catch(Exception e) {
				fail("Moon phase was not updated");
			}
	}
}
