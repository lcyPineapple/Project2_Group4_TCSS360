package GUI;

import GUI.forecast.Forecast;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.io.File;
import java.util.Random;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 * Main console GUI that allows the user to choose up to 8 weather stations to attach to.
 * @author yolandaxu
 * @author Benjamin Munoz
 * @author Leika Yamada
 * @author Aaron Lam
 */
public class WirelessConsole {

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
	private int outTemp = 0;
	private int outHum = 0;

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
	 * Flag to indicate whether or not the 2ND 
	 * button has been pressed.
	 */
	private boolean secondButtonActivated;
	
	/**
	 * The compass panel
	 */
	private Compass compassPanel;
	
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
	};
	
	/**
	 * The current variable selected
	 */
	private Vars currentVar;
	
	/**
	 * The 2ND button
	 */
	private JButton secondButton;
	
	private JPanel bottomStatusPanel;
	
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
	 * Create the application.
	 */
	public WirelessConsole() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Wireless Controller");
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPanel = new JPanel(new BorderLayout(10, 10));
		frame.add(contentPanel);
		
		initializeBottomStatusPanel();
		
		initializeComboButtons();
		
		initializePrimaryButtons();
		
		initializeMainPanels();
		
		currentVar = Vars.NONE;
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
        graphPanel.add(new JButton("Graph goes here"));
        leftDisplayPanel.add(graphPanel);

        // Forecast
        JPanel forecastPanel = new JPanel();
        Forecast forecast = new Forecast();
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
	    
        updateBottomStatusPanel();
    }
	
    /**
     * Update the bottom status panel
     */
	private void updateBottomStatusPanel() {
	    String temp = "Cool";
        if (outTemp > 85) {
            temp = "Hot";
        } else if (outTemp > 70) {
            temp = "Warm";
        } else {
            temp = "Cool";
        }
        
        String hum = "not humid";
        if (outTemp > 65) {
            hum = "Very Humid";
        } else if (outTemp > 50) {
            hum = "Humid";
        } else {
            hum = "Not Humid";
        }
        
        JLabel statusLabel = new JLabel(temp + " and " + hum);
        bottomStatusPanel.add(statusLabel);
	}

	/**
	 * Initialize the combo buttons
	 */
    private void initializeComboButtons() {
        JPanel weatherStationsPanel = new JPanel();
        contentPanel.add(weatherStationsPanel, BorderLayout.NORTH);
        
        JLabel stationsLabel = new JLabel("Stations:");
        weatherStationsPanel.add(stationsLabel);
        
        JComboBox station1 = new JComboBox();
        station1.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station1);
        
        JComboBox station2 = new JComboBox();
        station2.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station2);
        
        JComboBox station3 = new JComboBox();
        station3.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station3);
        
        JComboBox station4 = new JComboBox();
        station4.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station4);
        
        JComboBox station5 = new JComboBox();
        station5.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station5);
        
        JComboBox station6 = new JComboBox();
        station6.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station6);
        
        JComboBox station7 = new JComboBox();
        station7.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station7);
        
        JComboBox station8 = new JComboBox();
        station8.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
        weatherStationsPanel.add(station8);
    }

    /**
     * Initialize the primary buttons
     */
    private void initializePrimaryButtons() {
	  //the control buttons
        JPanel controlsPanel = new JPanel();
        contentPanel.add(controlsPanel, BorderLayout.EAST);
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
        
        // 2ND Button
        secondButton = new JButton("2ND");
        secondButton.setFocusable(false);
        deactivateSecondFunctions();
        secondButton.addActionListener(e -> {
            if (secondButtonActivated) {
                deactivateSecondFunctions();
            } else {
                secondButtonActivated = true;
                secondButton.setBackground(Color.GREEN);
            }
        });
        controlsPanel.add(secondButton);
        
        // Temperature and Heat Indices button
        JButton tempHeatButton = new JButton("TEMP/HEAT");
        tempHeatButton.addActionListener(e -> {
            if (secondButtonActivated) {
                if (currentVar == Vars.HEAT_INDEX) {
                    currentVar = Vars.THSW_INDEX;
                    System.out.println("Temperature-Humidity-Sun-Wind Index selected");
                } else {
                    currentVar = Vars.HEAT_INDEX;
                    System.out.println("Heat Index selected");
                }
                deactivateSecondFunctions();
            } else {
                if (currentVar == Vars.OUTER_TEMP) {
                    currentVar = Vars.INNER_TEMP;
                    System.out.println("Inside temperature selected");
                } else {
                    currentVar = Vars.OUTER_TEMP;
                    System.out.println("Outside temperature selected");
                }
            }
        });
        controlsPanel.add(tempHeatButton);
        
        // Humidity and Dew Point button
        JButton humDewButton = new JButton("HUM/DEW");
        humDewButton.addActionListener(e -> {
            if (secondButtonActivated) {
                currentVar = Vars.DEW_POINT;
                System.out.println("Dew point selected");
                deactivateSecondFunctions();
            } else {
                if (currentVar == Vars.OUTER_HUMIDITY) {
                    currentVar = Vars.INNER_HUMIDITY;
                    System.out.println("Inside humidity selected");
                } else {
                    currentVar = Vars.OUTER_HUMIDITY;
                    System.out.println("Outside humidity selected");
                }
            }
        });
        controlsPanel.add(humDewButton);
        
        // Wind and Wind Chill button
        JButton windChillButton = new JButton("WIND/CHILL");
        windChillButton.addActionListener(e -> {
            if (secondButtonActivated) {
                currentVar = Vars.WIND_CHILL;
                System.out.println("Wind Chill selected");
                deactivateSecondFunctions();
            } else {
                if (currentVar == Vars.WIND_SPEED) {
                    currentVar = Vars.WIND_DIRECTION;
                    compassPanel.toggleSpeedDirection();
                    System.out.println("Wind Direction selected");
                    
                } else {
                    currentVar = Vars.WIND_SPEED;
                    compassPanel.toggleSpeedDirection();
                    System.out.println("Wind Speed selected");
                }
            }
        });
        controlsPanel.add(windChillButton);
        
        // Daily Rain and Solar Radiation button
        JButton rainSolarButton = new JButton("RAINDAY/SOLAR");
        rainSolarButton.addActionListener(e -> {
            if (secondButtonActivated) {
                currentVar = Vars.CURRENT_SOLAR_RADIATION;
                System.out.println("Current Solar Radiation selected");
                deactivateSecondFunctions();
            } else {
                if (currentVar == Vars.DAILY_RAIN) {
                    currentVar = Vars.RAIN_STORM;
                    System.out.println("\"Rain Storm\" selected");
                } else {
                    currentVar = Vars.DAILY_RAIN;
                    System.out.println("Daily rain accumulation selected");
                }
            }
        });
        controlsPanel.add(rainSolarButton);
        
        // Rain rates and UV Indices buttons
        JButton rainUVButton = new JButton("RAINYR/UV");
        rainUVButton.addActionListener(e -> {
            if (secondButtonActivated) {
                if (currentVar == Vars.CURRENT_UV_INDEX) {
                    currentVar = Vars.DAILY_ACCUMULATED_UV_INDEX;
                    System.out.println("Daily Accumulated UV selected");
                } else {
                    currentVar = Vars.CURRENT_UV_INDEX;
                    System.out.println("Current UV Index selected");
                }
                deactivateSecondFunctions();
            } else {
                if (currentVar == Vars.MONTH_TO_DATE_PRECIPITATION) {
                    currentVar = Vars.YEAR_TO_DATE_PRECIPITATION;
                    System.out.println("Year-to-Date precipitation selected");
                } else if (currentVar == Vars.CURRENT_RAIN_RATE) {
                    currentVar = Vars.MONTH_TO_DATE_PRECIPITATION;
                    System.out.println("Month-to-Date precipitation selected");
                } else {
                    currentVar = Vars.CURRENT_RAIN_RATE;
                    System.out.println("Current Rain Rate selected");
                }
            }
        });
        controlsPanel.add(rainUVButton);
        
        // Barometric Pressure and Evapotranspiration button
        JButton barETButton = new JButton("BAR/ET");
        barETButton.addActionListener(e -> {
            if (secondButtonActivated) {
                if (currentVar == Vars.MONTHLY_ET) {
                    currentVar = Vars.YEARLY_ET;
                    System.out.println("Yearly Evapotranspiration selected");
                } else if (currentVar == Vars.CURRENT_ET) {
                    currentVar = Vars.MONTHLY_ET;
                    System.out.println("Montly Evapotranspiration selected");
                } else {
                    currentVar = Vars.CURRENT_ET;
                    System.out.println("Current Evapotranspiration selected");
                }
                deactivateSecondFunctions();
            } else {
                currentVar = Vars.BAROMETRIC_PRESSURE;
                System.out.println("Barometric Pressure selected");
            }
        });
        controlsPanel.add(barETButton);
        
        // Graph button
        var graphButton = new JButton("GRAPH");
        graphButton.addActionListener(e -> {
            System.out.println("GRAPH Button selected");
            if (secondButtonActivated) {
                deactivateSecondFunctions();
            }
        });
        controlsPanel.add(graphButton);
	}
	
    /**
     * Deactivate functions provided by the 2ND button
     */
    private void deactivateSecondFunctions() {
        secondButtonActivated = false;
        secondButton.setBackground(Color.RED);
    }
    
    /**
     * Loads all of the moon icon images
     */
     private void loadMoonIcons() {
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
     private void updateMoonPhaseIconAndLabel() {
         int myMoon = checkPhase();
         if(myMoon == 0) {
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
	 * Returns a number indicating the phase of the moon.
	 * For now, this number is a random integer in the 
	 * interval [0, 7]
	 * 
	 * @return A number indicating the phase of the moon
	 */
	private int checkPhase() {
		/*
		Below is the beginning of an implementation of a true moon phase calculator
		
        SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date today = new Date();
        String dateString = dateForm.format(today).toString();
        String monthString = dateString.substring(3, 5);
        String dayString = dateString.substring(0, 2);
        int month = Integer.parseInt(monthString);
        int day = Integer.parseInt(dayString);
		*/
	    return (new Random()).nextInt(8);
	}
}
