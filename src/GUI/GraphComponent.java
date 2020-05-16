package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * A JComponent for graphing discrete data over time where data is graphed as it is
 * given.
 *
 * @author Daniel Machen
 * @version 5/1/2020
 */
public class GraphComponent extends JComponent {
    /** The left margin around the graph */
    public static final int LEFT_MARGIN = 64;
    /** The right margin around the graph */
    public static final int RIGHT_MARGIN = 64;
    /** The bottom margin around the graph */
    public static final int LOWER_MARGIN = 32;
    /** The radius of drawn data points */
    public static final int POINT_RAIDUS = 12;

    /** The max width of the value of the time variable for display */
    private double myMaxWidth;
    /** The increments for the time variable to display as vertical lines */
    private int myIncrements;

    /** The upper maximum of data point values to display */
    private double myMaximum = 1;
    /** The lower minimum of data point values to display */
    private double myMinimum = -1;

    /** The units that are being displayed */
    private String myUnitLabel;
    /** The time unit being used */
    private String myTimeLabel;

    /** The data points that are going to be drawn */
    private List<DataPoint> myValues = new LinkedList<>();

    /**
     * Creates a graph component.
     *
     * @param theWidth - The width of the graph in units of time.
     * @param theUnitName - The unit of the value being measured.
     * @param theTimeUnit - The units of time being used (hr, min, ...)
     */
    public GraphComponent(float theWidth, int theIncrements, String theUnitName, String theTimeUnit) {
        myMaxWidth = theWidth;
        myIncrements = theIncrements;
        myUnitLabel = theUnitName;
        myTimeLabel = theTimeUnit;

        setPreferredSize(new Dimension(500,500));
    }

    /**
     * Removes all the data in the graph.
     */
    synchronized public void resetData() {
        myValues = new LinkedList<>();
    }

    /**
     * Add a data point to the graph.
     *
     * @param theIndex - The time value for this data point.
     * @param theValue - The value to be graphed.
     */
    synchronized public void addDataPoint(double theIndex, double theValue) {
        myValues.add(new DataPoint(theIndex, theValue));
    }

    /**
     * Sets the upper limit for the graphing window.
     *
     * @param theNewMaximum - The maximum value after which the point will be off
     *                        the graph.
     */
    synchronized public void setMaximum(double theNewMaximum) {
        if (theNewMaximum < myMinimum) {
            throw new IllegalArgumentException("The maximum must be greater than the minimum.");
        }
        myMaximum = theNewMaximum;
    }
    /**
     * Sets the lower limit for the graphing window.
     *
     * @param theNewMinimum - The lower value below which the point will be off
     *                        the graph.
     */
    synchronized public void setMinimum(double theNewMinimum) {
        if (theNewMinimum > myMaximum) {
            throw new IllegalArgumentException("The minimum must be less than the maximum.");
        }
        myMinimum = theNewMinimum;
    }

    /**
     * Shifts all the values over by the specified increment.
     *
     * @param theIncrement - The amount to shift everything over by.
     */
    synchronized public void incrementOffset(double theIncrement) {
        if (theIncrement <= 0) {
            throw new IllegalArgumentException("The increment must be greater than zero.");
        }
        // Shift all the data point over by a set amount.
        for (DataPoint data : myValues) {
            data.myIndex += theIncrement;
        }
        // Remove old data points that have left the display.
        if (myValues.size() > 0) {
            while (myValues.get(0).myIndex > myMaxWidth) {
                myValues.remove(0);
            }
        }
    }

    @Override
    public void paintComponent(Graphics theG) {
        Graphics2D g2 = (Graphics2D) theG;
        g2.clearRect(0,0, getWidth(), getHeight());
        g2.setBackground(Color.WHITE);
        g2.setColor(Color.BLACK);
        drawGrid(g2, 0, myIncrements);
        drawData(g2);
        drawLabels(g2);
        super.paintComponent(theG);
    }

    /**
     * Draws the data points to a Graphics2D object.
     *
     * @param theG - the graphics 2D object
     */
    synchronized private void drawData(Graphics2D theG) {
        int height = getGridHeight();
        int width = getGridWidth();

        double range = myMaximum - myMinimum;
        for (DataPoint point : myValues) {
            int y = (int) (((point.myValue - myMinimum) / range) * height);
            y = height - y;

            int x = (int) ((point.myIndex / myMaxWidth) * width);
            // Reverse the graph so new values show up on the right
            x = LEFT_MARGIN + (width - x);
            theG.drawOval(x - POINT_RAIDUS / 2, y - POINT_RAIDUS / 2, POINT_RAIDUS,POINT_RAIDUS);
        }
    }

    /**
     * Draws the labels for the data graph using the Graphics2D object.
     *
     * @param theG - The Graphics 2D object.
     */
    private void drawLabels(Graphics2D theG) {
        // Draw maximum value label
        String maximumUnitLabel = myMaximum + myUnitLabel + " ";
        theG.drawString(maximumUnitLabel,
                LEFT_MARGIN - theG.getFontMetrics().stringWidth(maximumUnitLabel),
                theG.getFontMetrics().getHeight());
        // Draw minimum value
        String minimumUnitLabel = myMinimum + myUnitLabel + " ";
        theG.drawString(minimumUnitLabel,
                LEFT_MARGIN - theG.getFontMetrics().stringWidth(minimumUnitLabel),
                getGridHeight());

        // Draw maximum past value index
        String earliestString = -myIncrements + " " + myTimeLabel;
        theG.drawString(earliestString,
                LEFT_MARGIN - theG.getFontMetrics().stringWidth(earliestString) / 2,
                getGridHeight() + theG.getFontMetrics().getHeight());
        // Draw minimum past value index (0)
        String latestString = "Now";
        theG.drawString("Now",
                getGridWidth() + LEFT_MARGIN - theG.getFontMetrics().stringWidth(latestString) / 2,
                getGridHeight() + theG.getFontMetrics().getHeight());

    }

    /**
     * Draws a grid on the graph.
     *
     * @param theG - The Graphics2D object to use to draw.
     * @param theRows - The number of rows to draw.
     * @param theCols - The number of columns to draw.
     */
    private void drawGrid(Graphics2D theG, int theRows, int theCols) {
        // Draw the horizontal lines for the x values
        for (int i = 1; i <= theRows; i++) {
            int y = (int) (i * ((double)getGridHeight() / theRows));
            theG.drawLine(0, y, getGridWidth(), y);
        }

        // Draw the vertical lines for the y values
        for (int i = 0; i <= theCols; i++) {
            int x = LEFT_MARGIN + (int) (i * ((double)getGridWidth() / theCols));
            theG.drawLine(x, 0, x, getGridHeight());
        }
    }

    /**
     * Get the height of the grid part of the component.
     *
     * @return - The height in pixels.
     */
    private int getGridHeight() {
        return getHeight() - LOWER_MARGIN;
    }

    /**
     * Get the width of the grid part of the component.
     *
     * @return - The width in pixels.
     */
    private int getGridWidth() {
        return getWidth() - LEFT_MARGIN - RIGHT_MARGIN;

    }

    /**
     * DataPoint class that stores two doubles for each data point.
     */
    private class DataPoint {
        /** The y (time) value of the data */
        public double myIndex;
        /** The x (magnitude) value of the data */
        public double myValue;

        /**
         * Construct a DataPoint object.
         *
         * @param theIndex - The time value for this data point.
         * @param theValue - The value for this data point.
         */
        DataPoint(double theIndex, double theValue) {
            myIndex = theIndex;
            myValue = theValue;
        }
    }
}