package GUI;

import GUI.forecast.Forecast;
import GUI.weatherStations.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main console GUI that allows the user to choose up to 8 weather stations to attach to.
 *
 * @author yolandaxu
 * @author Benjamin Munoz
 * @author Leika Yamada
 * @author Aaron Lam
 * @author Daniel Machen
 */
public class WirelessConsole {
    private WeatherStationIntegrater integrater;

    /*The Weather Stations chosen by the user in the combo boxes.*/
    private WeatherStation comboStation1;
    private WeatherStation comboStation2;
    private WeatherStation comboStation3;
    private WeatherStation comboStation4;
    private WeatherStation comboStation5;
    private WeatherStation comboStation6;
    private WeatherStation comboStation7;
    private WeatherStation comboStation8;

    private JFrame frame;
    private JPanel contentPanel;

    private JTextField timeDate;
    private JTextField outTempValue;
    private JTextField barometerValue;
    private JTextField outHumValue;
    private JTextField inTempValue;
    private JTextField inHumValue;
    private JTextField chillValue;
    private JTextField dayRainValue;
    private JTextField monthRainValue;
    private JLabel statusLabel = new JLabel("Status message.");

    /**
     * Image Icons for showing the moon phase
     */
    private ImageIcon firstQuarterImageIcon;
    private ImageIcon fullMoonImageIcon;
    private ImageIcon lastQuarterImageIcon;
    private ImageIcon newMoonImageIcon;
    private ImageIcon waningCrescentImageIcon;
    private ImageIcon waxingCrescentImageIcon;
    private ImageIcon waningGibbousImageIcon;
    private ImageIcon waxingGibbousImageIcon;

    /**
     * JLabels for showing the moon phase
     */
    private JLabel moonPhaseTextLbl = new JLabel("New Moon");
    private JLabel moonLabel;

    /**
     * The compass panel
     */
    private Compass compassPanel;

    /**
     * The graph component
     */
    private GraphComponent graphComponent;

    /**
     * The forecast component
     */
    private Forecast forecast;
    /**
     * The current variable selected
     */
    private Vars currentVar;


    private JPanel bottomStatusPanel;

    /**
     * Create the application.
     *
     * @throws Exception
     */
    public WirelessConsole() throws Exception {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WirelessConsole window = new WirelessConsole();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     *
     * @throws Exception
     */
    private void initialize() throws Exception {
        frame = new JFrame("Wireless Controller");
        frame.setBounds(100, 100, 1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPanel = new JPanel(new BorderLayout(10, 10));
        frame.add(contentPanel);

        initializeBottomStatusPanel();

        initializeComboButtons();

        initializePrimaryButtons();

        initializeMainPanels();

        currentVar = Vars.NONE;

        initializeWeatherStations();
    }

    /**
     * Initialize weather stations and weather station integrater
     *
     * @throws Exception
     */
    private void initializeWeatherStations() throws Exception {
        this.integrater = new WeatherStationIntegrater(this);

        comboStation1 = new WeatherStation1(integrater);
        comboStation2 = new WeatherStation2(integrater);
        comboStation3 = new WeatherStation3(integrater);
        comboStation4 = new WeatherStation4(integrater);
        comboStation5 = new WeatherStation5(integrater);
        comboStation6 = new WeatherStation3(integrater);
        comboStation7 = new WeatherStation4(integrater);
        comboStation8 = new WeatherStation5(integrater);

        integrater.run();
    }

    /**
     * Initialize the Main Panels
     */
    private void initializeMainPanels() {
        //JPanel mainPanels = new JPanel(new BorderLayout());
        JPanel mainPanels = new JPanel(new BorderLayout(10, 0));

        // CONTENT ON THE LEFT SIDE
        JPanel leftDisplayPanel = new JPanel(new GridLayout(3, 1));
        // Wind Compass
        compassPanel = new Compass();
        leftDisplayPanel.add(compassPanel);

        // Weather Variable Graph
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphComponent = new GraphComponent(60, 30, "C", "Secs");
        graphComponent.setMaximum(24);
        graphComponent.setMinimum(24);
        graphPanel.add(graphComponent);
        leftDisplayPanel.add(graphPanel);

        // Forecast
        JPanel forecastPanel = new JPanel();
        forecast = new Forecast();
        forecastPanel.add(forecast);
        leftDisplayPanel.add(forecastPanel);

        mainPanels.add(leftDisplayPanel, BorderLayout.CENTER);

        // CONTENT ON THE RIGHT SIDE
        JPanel rightDisplayPanel = new JPanel();
        rightDisplayPanel.setLayout(new BoxLayout(rightDisplayPanel, BoxLayout.Y_AXIS));

        JPanel moonAndDatePanel = new JPanel();
        moonAndDatePanel.setLayout(new BoxLayout(moonAndDatePanel, BoxLayout.Y_AXIS));
        // Time and Date
        JPanel tdPanel = new JPanel();
        timeDate = new JTextField();
        timeDate.setText("Time/Date");
        timeDate.setEditable(false);
        
        Timer timeDateTimer = new Timer();
        timeDateTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                timeDate.setText((new Date()).toString());
            }
        }, 0, 1000);
        
        tdPanel.add(timeDate);
        moonAndDatePanel.add(tdPanel);
        // Moon
        moonLabel = new JLabel();
        moonPhaseTextLbl = new JLabel("Moon Phase");
        loadMoonIcons();
        updateMoonPhaseIconAndLabel();
        moonLabel.setAlignmentX(0.5f);
        moonPhaseTextLbl.setAlignmentX(0.5f);
        moonAndDatePanel.add(moonLabel);
        moonAndDatePanel.add(moonPhaseTextLbl);

        rightDisplayPanel.add(moonAndDatePanel);

        JPanel tempHumBaroPanel = new JPanel(new GridLayout(1, 3));
        // Outside Temperature
        JPanel tempOutPanel = new JPanel();
        JLabel outTempLabel = new JLabel("TEMP OUT (F)");
        outTempValue = new JTextField();
        tempOutPanel.setLayout(new BoxLayout(tempOutPanel, BoxLayout.Y_AXIS));
        tempOutPanel.add(outTempLabel);
        tempOutPanel.add(outTempValue);
        tempHumBaroPanel.add(tempOutPanel);
        // Outside Humidity
        JPanel outHumPanel = new JPanel();
        JLabel outHumLabel = new JLabel("HUM OUT (%)");
        outHumValue = new JTextField();
        outHumPanel.setLayout(new BoxLayout(outHumPanel, BoxLayout.Y_AXIS));
        outHumPanel.add(outHumLabel);
        outHumPanel.add(outHumValue);
        tempHumBaroPanel.add(outHumPanel);
        // Barometric Pressure
        JPanel barometerPanel = new JPanel();
        JLabel barometerLabel = new JLabel("BAROMETER (in)");
        barometerValue = new JTextField("N/A");
        barometerPanel.setLayout(new BoxLayout(barometerPanel, BoxLayout.Y_AXIS));
        barometerPanel.add(barometerLabel);
        barometerPanel.add(barometerValue);
        tempHumBaroPanel.add(barometerPanel);

        rightDisplayPanel.add(tempHumBaroPanel);

        JPanel tempHumChillPanel = new JPanel(new GridLayout(1, 3));
        tempHumChillPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        // Inside Temperature
        JPanel inTempPanel = new JPanel();
        JLabel inTempLabel = new JLabel("TEMP IN (F)");
        inTempValue = new JTextField();
        inTempPanel.setLayout(new BoxLayout(inTempPanel, BoxLayout.Y_AXIS));
        inTempPanel.add(inTempLabel);
        inTempPanel.add(inTempValue);
        tempHumChillPanel.add(inTempPanel);
        // Inside Humidity
        JPanel inHumPanel = new JPanel();
        JLabel inHumLabel = new JLabel("HUM IN  (%)");
        inHumValue = new JTextField("");
        inHumPanel.setLayout(new BoxLayout(inHumPanel, BoxLayout.Y_AXIS));
        inHumPanel.add(inHumLabel);
        inHumPanel.add(inHumValue);
        tempHumChillPanel.add(inHumPanel);
        // Wind Chill Panel
        JPanel chillPanel = new JPanel();
        JLabel chillLabel = new JLabel("CHILL (F)");
        chillValue = new JTextField("N/A");
        chillPanel.setLayout(new BoxLayout(chillPanel, BoxLayout.Y_AXIS));
        chillPanel.add(chillLabel);
        chillPanel.add(chillValue);
        tempHumChillPanel.add(chillPanel);

        rightDisplayPanel.add(tempHumChillPanel);

        JPanel rainPanels = new JPanel(new GridLayout(1, 2));
        // Daily Rain
        JPanel dayRainPanel = new JPanel();
        JLabel dayRainLabel = new JLabel("DAILY RAIN (in)");
        dayRainPanel.add(dayRainLabel);
        dayRainValue = new JTextField();
        dayRainValue.setText("");
        dayRainPanel.add(dayRainValue);
        dayRainPanel.setLayout(new BoxLayout(dayRainPanel, BoxLayout.Y_AXIS));
        rainPanels.add(dayRainPanel);

        // Montly Rain
        JPanel monthRainPanel = new JPanel();
        JLabel monthRainLabel = new JLabel("RAIN MO (in)");
        monthRainPanel.add(monthRainLabel);
        monthRainValue = new JTextField();
        monthRainValue.setText("N/A");
        monthRainPanel.add(monthRainValue);
        monthRainPanel.setLayout(new BoxLayout(monthRainPanel, BoxLayout.Y_AXIS));
        rainPanels.add(monthRainPanel);

        rightDisplayPanel.add(rainPanels);

        mainPanels.add(rightDisplayPanel, BorderLayout.EAST);
        contentPanel.add(mainPanels);
    }

    /**
     * Initialize the bottom status panel
     */
    private void initializeBottomStatusPanel() {
        bottomStatusPanel = new JPanel();
        bottomStatusPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        contentPanel.add(bottomStatusPanel, BorderLayout.SOUTH);
        bottomStatusPanel.add(statusLabel);
//        updateBottomStatusPanel();
    }

    /**
     * Update the bottom status panel
     */
    private String updateBottomStatusPanel(double outHum, double outTemp) {
        String temp = "Cool";
        if (outTemp > 85) {
            temp = "Hot";
        } else if (outTemp > 70) {
            temp = "Warm";
        } else {
            temp = "Cool";
        }

        String hum = "not humid";
        if (outHum > 65) {
            hum = "Very Humid";
        } else if (outHum > 50) {
            hum = "Humid";
        } else {
            hum = "Not Humid";
        }
        return temp + " and " + hum + ". Temperature is " + outTemp + " degrees.";
    }

    /**
     * Initialize the combo buttons based on user choice.
     */
    private void initializeComboButtons() throws Exception {
        JPanel weatherStationsPanel = new JPanel();
        contentPanel.add(weatherStationsPanel, BorderLayout.NORTH);

        JLabel stationsLabel = new JLabel("Stations:");
        weatherStationsPanel.add(stationsLabel);

        JComboBox station1 = new JComboBox();
        station1.setModel(new DefaultComboBoxModel(new String[]{"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station1);
        station1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event1) {
                String choice = station1.getSelectedItem().toString();
                try {
                    initializeComboSelection(choice, comboStation1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JComboBox station2 = new JComboBox();
        station2.setModel(new DefaultComboBoxModel(new String[]{"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station2);
        station2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
                String choice = station2.getSelectedItem().toString();
                try {
                    initializeComboSelection(choice, comboStation2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        JComboBox station3 = new JComboBox();
        station3.setModel(new DefaultComboBoxModel(new String[]{"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station3);
        station3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event3) {
                String choice = station3.getSelectedItem().toString();
                try {
                    initializeComboSelection(choice, comboStation3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        JComboBox station4 = new JComboBox();
        station4.setModel(new DefaultComboBoxModel(new String[]{"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station4);
        station4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event4) {
                String choice = station4.getSelectedItem().toString();
                try {
                    initializeComboSelection(choice, comboStation4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        JComboBox station5 = new JComboBox();
        station5.setModel(new DefaultComboBoxModel(new String[]{"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station5);
        station5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event5) {
                String choice = station5.getSelectedItem().toString();
                try {
                    initializeComboSelection(choice, comboStation5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        JComboBox station6 = new JComboBox();
        station6.setModel(new DefaultComboBoxModel(new String[]{"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station6);
        station6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event6) {
                String choice = station6.getSelectedItem().toString();
                try {
                    initializeComboSelection(choice, comboStation6);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        JComboBox station7 = new JComboBox();
        station7.setModel(new DefaultComboBoxModel(new String[]{"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station7);
        station7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event7) {
                String choice = station7.getSelectedItem().toString();
                try {
                    initializeComboSelection(choice, comboStation7);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        JComboBox station8 = new JComboBox();
        station8.setModel(new DefaultComboBoxModel(new String[]{"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station8);
        station1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event8) {
                String choice = station8.getSelectedItem().toString();
                try {
                    initializeComboSelection(choice, comboStation8);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    /**
     * This method changes the weather station the GUI is connected to based
     * on the user's choice in the comboBoxes. Type 1 corresponds to station 1,
     * Type 2 corresponds to station 2, Type 3 corresponds to station 3, etc.
     *
     * @param theChoice    - the users choice for which station they want to connect.
     * @param comboStation - the weatherStation object associated with the combo box.
     */
    public void initializeComboSelection(String theChoice, WeatherStation comboStation) throws Exception {
        comboStation.setConnected(false);
        comboStation.kill();
        if (theChoice.equals("TYPE 1")) {
            comboStation = new WeatherStation1(integrater);
        } else if (theChoice.equals("TYPE 2")) {
            comboStation = new WeatherStation2(integrater);
        } else if (theChoice.equals("TYPE 3")) {
            comboStation = new WeatherStation3(integrater);
        } else if (theChoice.equals("TYPE 4")) {
            comboStation = new WeatherStation4(integrater);
        } else if (theChoice.equals("TYPE 5")) {
            comboStation = new WeatherStation5(integrater);
        }
        try {
            comboStation.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        comboStation.setConnected(true);
    }

    /**
     * Initialize the primary buttons
     */
    private void initializePrimaryButtons() {
        //the control buttons
        JPanel controlsPanel = new JPanel();
        contentPanel.add(controlsPanel, BorderLayout.EAST);
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

        // Temperature and Heat Indices button
        JButton tempHeatButton = new JButton("TEMP");
        tempHeatButton.addActionListener(e -> {
            graphComponent.setUnits("C");
            currentVar = Vars.OUTER_TEMP;
        });
        controlsPanel.add(tempHeatButton);

        // Humidity and Dew Point button
        JButton humDewButton = new JButton("HUMIDITY");
        humDewButton.addActionListener(e -> {
            graphComponent.setUnits("%");
            currentVar = Vars.OUTER_HUMIDITY;
        });
        controlsPanel.add(humDewButton);

        // Wind and Wind Chill button
        JButton windChillButton = new JButton("WIND SPEED");
        windChillButton.addActionListener(e -> {
            currentVar = Vars.WIND_SPEED;
            graphComponent.setUnits("MPH");
        });
        controlsPanel.add(windChillButton);

        // Daily Rain and Solar Radiation button
        JButton rainSolarButton = new JButton("RAIN RATE");
        rainSolarButton.addActionListener(e -> {
            currentVar = Vars.CURRENT_RAIN_RATE;
            graphComponent.setUnits("in/min");
        });
        controlsPanel.add(rainSolarButton);

    }

    /**
     * Loads all of the moon icon images
     */
    public void loadMoonIcons() {
        try {
            firstQuarterImageIcon = new ImageIcon(new File("moonImages/first-quarter.png").toURI().toURL());
            fullMoonImageIcon = new ImageIcon(new File("moonImages/full-moon.png").toURI().toURL());
            lastQuarterImageIcon = new ImageIcon(new File("moonImages/last-quarter.png").toURI().toURL());
            newMoonImageIcon = new ImageIcon(new File("moonImages/new-moon.png").toURI().toURL());
            waningCrescentImageIcon = new ImageIcon(new File("moonImages/waning-crescent.png").toURI().toURL());
            waxingCrescentImageIcon = new ImageIcon(new File("moonImages/waxing-crescent.png").toURI().toURL());
            waningGibbousImageIcon = new ImageIcon(new File("moonImages/waning-gibbous.png").toURI().toURL());
            waxingGibbousImageIcon = new ImageIcon(new File("moonImages/waxing-gibbous.png").toURI().toURL());
        } catch (Exception e) {
            System.out.println("WARNING: Some or all of the moon images failed to load");
        }
    }

    /**
     * Updates the moon phase and label icons
     */
    public void updateMoonPhaseIconAndLabel() {
        int myMoon = checkPhase();
        if (myMoon == 0) {
            moonPhaseTextLbl = new JLabel("New Moon");
            moonLabel.setIcon(newMoonImageIcon);
        } else if (myMoon == 1) {
            moonPhaseTextLbl = new JLabel("Waxing Crescent");
            moonLabel.setIcon(waxingCrescentImageIcon);
        } else if (myMoon == 2) {
            moonPhaseTextLbl = new JLabel("First Quarter");
            moonLabel.setIcon(firstQuarterImageIcon);
        } else if (myMoon == 3) {
            moonPhaseTextLbl = new JLabel("Waxing Gibbous");
            moonLabel.setIcon(waxingGibbousImageIcon);
        } else if (myMoon == 4) {
            moonPhaseTextLbl = new JLabel("Full Moon");
            moonLabel.setIcon(fullMoonImageIcon);
        } else if (myMoon == 5) {
            moonPhaseTextLbl = new JLabel("Waning Gibbous");
            moonLabel.setIcon(waningGibbousImageIcon);
        } else if (myMoon == 6) {
            moonPhaseTextLbl = new JLabel("Last Quarter");
            moonLabel.setIcon(lastQuarterImageIcon);
        } else {
            moonPhaseTextLbl = new JLabel("Waning Crescent");
            moonLabel.setIcon(waningCrescentImageIcon);
        }
    }

    /**
     * This method is the overloaded version of the updateMoonPhase method.
     * It exists for testing purposes only. To ensure version above works
     * for any value chosen by the checkPhase method.
     *@Overload Updates the moon phase and label icons
     */
    public void updateMoonPhaseIconAndLabel(int myMoon) {
        if (myMoon == 0) {
            moonPhaseTextLbl = new JLabel("New Moon");
            moonLabel.setIcon(newMoonImageIcon);
        } else if (myMoon == 1) {
            moonPhaseTextLbl = new JLabel("Waxing Crescent");
            moonLabel.setIcon(waxingCrescentImageIcon);
        } else if (myMoon == 2) {
            moonPhaseTextLbl = new JLabel("First Quarter");
            moonLabel.setIcon(firstQuarterImageIcon);
        } else if (myMoon == 3) {
            moonPhaseTextLbl = new JLabel("Waxing Gibbous");
            moonLabel.setIcon(waxingGibbousImageIcon);
        } else if (myMoon == 4) {
            moonPhaseTextLbl = new JLabel("Full Moon");
            moonLabel.setIcon(fullMoonImageIcon);
        } else if (myMoon == 5) {
            moonPhaseTextLbl = new JLabel("Waning Gibbous");
            moonLabel.setIcon(waningGibbousImageIcon);
        } else if (myMoon == 6) {
            moonPhaseTextLbl = new JLabel("Last Quarter");
            moonLabel.setIcon(lastQuarterImageIcon);
        } else{
            moonPhaseTextLbl = new JLabel("Waning Crescent");
            moonLabel.setIcon(waningCrescentImageIcon);
        }
    }
    
    /**
     * Returns a number indicating the phase of the moon.
     * For now, this number is a random integer in the
     * interval [0, 7]
     *
     * @return A number indicating the phase of the moon
     */
    public int checkPhase() {
        return (new Random()).nextInt(8);
    }

    /**
     * Receives data from integrater and update GUI views.
     */
    public void updateGUI() {
        // only one thread at a time can update the GUI.
        List<List<Double>> lists = this.integrater.getWeatherDataListsCopy();
        updateJTextFields(lists);
        updateForecast(lists);
        updateCompass(lists);
        updateGraph(lists);
        printLog(lists);
    }

    /**
     * Update weather data fields on GUI.
     */
    private void updateJTextFields(List<List<Double>> lists) {
        double outHum = 0;
        double outTemp = 0;
        for (int i = 0; i < lists.size(); i++) {
            List<Double> list = lists.get(i);
            String average = getAverage(list);
            switch (i) {
                case 0:
                    outHumValue.setText(average);
                    inHumValue.setText(average);
                    outHum = Double.parseDouble(average);
                    break;
                case 1:
                    outTempValue.setText(average);
                    inTempValue.setText(average);
                    outTemp = Double.parseDouble(average);
                    break;
                case 3:
                    dayRainValue.setText(average);
                    break;
            }
        }
        statusLabel.setText(updateBottomStatusPanel(outHum, outTemp));
    }

    private void updateForecast(List<List<Double>> lists) {
        List<Double> weatherData = new ArrayList<>();
        for (List list : lists) {
            String average = getAverage(list);
            weatherData.add(Double.parseDouble(average));
        }
        double humidity = weatherData.get(0);
        double temperature = weatherData.get(1);
        double windSpeed = weatherData.get(2);
        double rainfall = weatherData.get(3);
        forecast.updateWeather(humidity, windSpeed, temperature, rainfall);
    }

    private void updateCompass(List<List<Double>> lists) {
        var windSpeedList = lists.get(2);
        // getAverage returns a string, but my
        //    setDisplayData requires a doubles.
        var avg = 0;
        for (var entry : windSpeedList) {
            avg += entry;
        }
        if (windSpeedList.size() > 0) {
            avg /= windSpeedList.size();
        }
        compassPanel.setDisplayData(0, avg, "mph");
    }

    /**
     * Updates the graph with temperature data
     */
    private void updateGraph(List<List<Double>> lists) {
        // Reset all the data
        graphComponent.resetData();
        // Reprint the data
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        int index = 1;
        switch (currentVar) {
            case WIND_SPEED:
                index = 2;
                break;
            case OUTER_TEMP:
                index = 1;
                break;
            case OUTER_HUMIDITY:
                index = 0;
                break;
            case CURRENT_RAIN_RATE:
                index = 3;
                break;
            default:
                index = 1;
                break;

        }
        // Average data withen a second
        int samplesSoFar = 0;
        Double currentClusterSample = null;
        Double lastTime = null;

        for (int i = 0; i < lists.get(index).size(); i++) {
            double value = lists.get(index).get(i);
            if (currentClusterSample == null) {
                // average the value for this sample
                currentClusterSample = value;
                samplesSoFar += 1;
                lastTime = lists.get(4).get(i);
            } else if (lists.get(4).get(i) - lastTime > 1000) {
                if (value > max) {
                    max = value;
                } else if (value < min) {
                    min = value;
                }
                graphComponent.addDataPoint(0, currentClusterSample);
                graphComponent.incrementOffset(1);
                currentClusterSample = null;
            } else {
                // average the value for this sample
                currentClusterSample = currentClusterSample * samplesSoFar + value;
                samplesSoFar += 1;
                currentClusterSample /= samplesSoFar;
            }

        }

        if (min < max) {
            graphComponent.setMinimum(min - Math.abs((max - min) * 0.3));
            graphComponent.setMaximum(max + Math.abs((max - min) * 0.3));
        }
        graphComponent.repaint();
    }

    /**
     * Log weather data from weather station integrater.
     */
    private synchronized void printLog(List<List<Double>> lists) {
        System.out.println("Data from weather station integrater:");
        System.out.print("[ ");
        for (List<Double> list : lists) {
            System.out.print(getAverage(list) + " ");
        }
        System.out.print("] \n");
    }

    /**
     * @return the average of the list in string representation.
     */
    private String getAverage(List<Double> list) {
        double sum = getSum(list);
        double average = list.isEmpty() ? 0 : sum / list.size();
        return String.format("%.2f", average);
    }

    /**
     * @return the sum of the list.
     */
    private double getSum(List<Double> list) {
        double sum = 0.0;
        for (Double num : list) {
            sum += num;
        }
        return sum;
    }

    /**
     * Enum for determining which weather variables have been selected
     */
    private static enum Vars {
        NONE,
        WIND_SPEED,
        WIND_DIRECTION,
        OUTER_TEMP,
        INNER_TEMP,
        OUTER_HUMIDITY,
        INNER_HUMIDITY,
        WIND_CHILL,
        DEW_POINT,
        BAROMETRIC_PRESSURE,
        CURRENT_UV_INDEX,
        DAILY_ACCUMULATED_UV_INDEX,
        HEAT_INDEX,
        THSW_INDEX, // Temperature Humidity Sun Wind Index
        CURRENT_RAIN_RATE,
        MONTH_TO_DATE_PRECIPITATION,
        YEAR_TO_DATE_PRECIPITATION,
        DAILY_RAIN,
        RAIN_STORM,
        CURRENT_SOLAR_RADIATION,
        CURRENT_ET,
        MONTHLY_ET,
        YEARLY_ET,
    }
}
