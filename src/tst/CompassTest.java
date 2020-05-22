package tst;

import GUI.Compass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the Compass component
 *
 * @author Benjamin Munoz
 */
public class CompassTest {

    private static Compass testedCompass;
    private static JFrame containingFrame;

    /**
     * initialize the compass, placing it within
     * a jframe to allow the compass to
     * consult the paint(Graphics)
     * method.
     */
    @BeforeAll
    public static void initializeCompass() {
        try {
            testedCompass = new Compass();
        } catch (Exception e) {
            fail("Construction of Compass failed");
        }
        // EventQueue.invokeLater(() -> {
        containingFrame = new JFrame();
        containingFrame.setSize(100, 100);
        containingFrame.add(testedCompass);
        containingFrame.setVisible(true);
        // });
    }

    /**
     * Determine if painting causes any exceptions
     */
    @Test
    void paintingDoesNotCauseExceptions() {
        try {
            testedCompass.paint(null);
            testedCompass.paint(testedCompass.getGraphics());
            testedCompass.toggleSpeedDirection(true);
            testedCompass.paint(testedCompass.getGraphics());

            testedCompass.toggleSpeedDirection(false);
            testedCompass.paint(testedCompass.getGraphics());
        } catch (Exception e) {
            fail("Exception was encounted on repainting the Compass");
        }
    }

    /**
     * Determine if setting relevant data causes exceptions.
     */
    @Test
    void settingDataDoesNotCauseExceptions() {
        try {
            for (int i = 0; i < 100; i++) {
                testedCompass.setDataVisible(true);

                testedCompass.toggleSpeedDirection(true);
                testedCompass.setDisplayData(Math.random(), Math.random(), null);
                testedCompass.setDisplayData(Math.random() - 10, Math.random(), "ab");
                testedCompass.setDisplayData(0, Math.random(), null);
                testedCompass.paint(testedCompass.getGraphics());

                testedCompass.toggleSpeedDirection(false);
                testedCompass.setDisplayData(Math.random(), Math.random(), null);
                testedCompass.setDisplayData(Math.random() - 10, Math.random(), "ab");
                testedCompass.setDisplayData(0, Math.random(), null);
                testedCompass.paint(testedCompass.getGraphics());

                testedCompass.setDataVisible(false);

                testedCompass.toggleSpeedDirection(true);
                testedCompass.setDisplayData(Math.random(), Math.random(), null);
                testedCompass.setDisplayData(Math.random() - 10, Math.random(), "ab");
                testedCompass.setDisplayData(0, Math.random(), null);
                testedCompass.paint(testedCompass.getGraphics());

                testedCompass.toggleSpeedDirection(false);
                testedCompass.setDisplayData(Math.random(), Math.random(), null);
                testedCompass.setDisplayData(Math.random() - 10, Math.random(), "ab");
                testedCompass.setDisplayData(0, Math.random(), null);
                testedCompass.paint(testedCompass.getGraphics());
            }
        } catch (Exception e) {
            fail("Exception was encounted on setting data visiblity");
        }
    }

}
