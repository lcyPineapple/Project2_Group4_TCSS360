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
    private float myMaxWidth;
    /** The increments for the time variable to display as vertical lines */
    private int increments = 24;

    /** The upper maximum of data point values to display */
    private int myMaximum = 12;
    /** The lower minimum of data point values to display */
    private int myMinimum = -24;

    /** The units that are being displayed */
    private String myUnitLabel;
    /** The time unit being used */
    private String myTimeLabel;

    /** The data points that are going to be drawn */
    private List<DataPoint> values = new LinkedList<>();

    /**
     * Creates a graph component.
     *
     * @param width - The width of the graph in units of time.
     * @param theUnitName - The unit of the value being measured.
     * @param timeUnit - The units of time being used (hr, min, ...)
     */
    GraphComponent(float width, String theUnitName, String timeUnit) {
        myMaxWidth = width;
        myUnitLabel = theUnitName;
        myTimeLabel = timeUnit;
        setPreferredSize(new Dimension(500,500));
    }

    /**
     * Removes all the data in the graph.
     */
    synchronized public void resetData() {
        values = new LinkedList<>();
    }

    /**
     * Add a data point to the graph.
     *
     * @param theIndex - The time value for this data point.
     * @param theValue - The value to be graphed.
     */
    synchronized public void addDataPoint(double theIndex, double theValue) {
        values.add(new DataPoint(theIndex, theValue));
    }

    /**
     * Shifts all the values over by the specified increment.
     *
     * @param increment - The amount to shift everything over by.
     */
    synchronized public void incrementOffset(double increment) {
        // Shift all the data point over by a set amount.
        for (DataPoint data : values) {
            data.myIndex += increment;
        }
        // Remove old data points that have left the display.
        while (values.get(0).myIndex > myMaxWidth) {
            values.remove(0);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.clearRect(0,0, getWidth(), getHeight());
        g2.setBackground(Color.WHITE);
        g2.setColor(Color.BLACK);
        drawGrid(g2, 0, increments);
        drawData(g2);
        drawLabels(g2);
        super.paintComponent(g);
    }

    /**
     * Draws the data points to a Graphics2D object.
     *
     * @param g - the graphics 2D object
     */
    synchronized private void drawData(Graphics2D g) {
        int height = getGridHeight();
        int width = getGridWidth();

        int range = myMaximum - myMinimum;
        for (DataPoint point : values) {
            int y = (int) (((point.myValue - myMinimum) / range) * height);
            y = height - y;

            int x = (int) ((point.myIndex / myMaxWidth) * width);
            // Reverse the graph so new values show up on the right
            x = LEFT_MARGIN + (width - x);
            g.drawOval(x - POINT_RAIDUS / 2, y - POINT_RAIDUS / 2, POINT_RAIDUS,POINT_RAIDUS);
        }
    }

    /**
     * Draws the labels for the data graph using the Graphics2D object.
     *
     * @param g - The Graphics 2D object.
     */
    private void drawLabels(Graphics2D g) {
        // Draw maximum value label
        String maximumUnitLabel = myMaximum + myUnitLabel + " ";
        g.drawString(maximumUnitLabel,
                  LEFT_MARGIN - g.getFontMetrics().stringWidth(maximumUnitLabel),
                     g.getFontMetrics().getHeight());
        // Draw minimum value
        String minimumUnitLabel = myMinimum + myUnitLabel + " ";
        g.drawString(minimumUnitLabel,
                  LEFT_MARGIN - g.getFontMetrics().stringWidth(minimumUnitLabel),
                     getGridHeight());

        // Draw maximum past value index
        String earliestString = -increments + " " + myTimeLabel;
        g.drawString(earliestString,
                  LEFT_MARGIN - g.getFontMetrics().stringWidth(earliestString) / 2,
                  getGridHeight() + g.getFontMetrics().getHeight());
        // Draw minimum past value index (0)
        String latestString = "Now";
        g.drawString("Now",
                  getGridWidth() + LEFT_MARGIN - g.getFontMetrics().stringWidth(latestString) / 2,
                  getGridHeight() + g.getFontMetrics().getHeight());

    }

    /**
     * Draws a grid on the graph.
     *
     * @param g - The Graphics2D object to use to draw.
     * @param rows - The number of rows to draw.
     * @param cols - The number of columns to draw.
     */
    private void drawGrid(Graphics2D g, int rows, int cols) {
        // Draw the horizontal lines for the x values
        for (int i = 1; i <= rows; i++) {
            int y = (int) (i * ((double)getGridHeight() / rows));
            g.drawLine(0, y, getGridWidth(), y);
        }

        // Draw the vertical lines for the y values
        for (int i = 0; i <= cols; i++) {
            int x = LEFT_MARGIN + (int) (i * ((double)getGridWidth() / cols));
            g.drawLine(x, 0, x, getGridHeight());
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
