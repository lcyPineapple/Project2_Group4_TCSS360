package GUI;

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
 */
public class WirelessConsole {

	private JFrame frame;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{0};
		gbl_panel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
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
        JPanel MainDisplay = new JPanel();
        frame.getContentPane().add(MainDisplay, BorderLayout.CENTER);
        MainDisplay.setLayout(null);
        
        // CONTENT ON THE LEFT SIDE
        JPanel leftDisplayPanel = new JPanel();
        leftDisplayPanel.setBounds(5, 78, 100, 200);
        MainDisplay.add(leftDisplayPanel);
        
        // Wind Compass
        var compassPanel = new Compass();
        compassPanel.setDataVisible(false);
        leftDisplayPanel.add(compassPanel);
        
        // Weather Variable Graph
        JPanel graphPanel = new JPanel();
        leftDisplayPanel.add(graphPanel);
        
        leftDisplayPanel.setLayout(new BoxLayout(leftDisplayPanel, BoxLayout.Y_AXIS));
        
        // ---
        
        // CONTENT ON THE RIGHT SIDE
        JPanel rightDisplayPanel = new JPanel();
        rightDisplayPanel.setBounds(200, 5, 254, 166);
        MainDisplay.add(rightDisplayPanel);
        
        // Moon and Date Panel
        JPanel moonAndDatePanel = new JPanel();
        rightDisplayPanel.add(moonAndDatePanel);
        rightDisplayPanel.setLayout(new BoxLayout(rightDisplayPanel, BoxLayout.Y_AXIS));
        
        // JPanel moonPhase = new JPanel();
        // moonAndDatePanel.add(moonPhase);
        
        // Time and Date
        timeDate = new JTextField();
        moonAndDatePanel.add(timeDate);
        timeDate.setText("Time/Date");
        timeDate.setEditable(false);
        
        JPanel tempHumBaroPanel = new JPanel();
        rightDisplayPanel.add(tempHumBaroPanel);
        
        // Outside Temperature
        JPanel tempOutPanel = new JPanel();
        tempHumBaroPanel.add(tempOutPanel);
        JLabel outTempLabel = new JLabel("TEMP OUT (F)");
        tempOutPanel.add(outTempLabel);
        outTempValue = new JTextField();
        tempOutPanel.add(outTempValue);
        tempOutPanel.setLayout(new BoxLayout(tempOutPanel, BoxLayout.Y_AXIS));
        
        // Outside Humidity
        JPanel outHumPanel = new JPanel();
        tempHumBaroPanel.add(outHumPanel);
        JLabel outHumLabel = new JLabel("HUM OUT (%)");
        outHumPanel.add(outHumLabel);
        outHumValue = new JTextField();
        outHumPanel.add(outHumValue);
        outHumPanel.setLayout(new BoxLayout(outHumPanel, BoxLayout.Y_AXIS));
        
        // Barometric Pressure
        JPanel barometerPanel = new JPanel();
        tempHumBaroPanel.add(barometerPanel);
        JLabel barometerLabel = new JLabel("BAROMETER (in)");
        barometerPanel.add(barometerLabel);
        barometerValue = new JTextField("N/A");
        barometerPanel.add(barometerValue);
        barometerPanel.setLayout(new BoxLayout(barometerPanel, BoxLayout.Y_AXIS));
        
        
        JPanel tempHumChillPanel = new JPanel();
        tempHumChillPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        rightDisplayPanel.add(tempHumChillPanel);
        
        // Inside Temperature
        JPanel inTempPanel = new JPanel();
        tempHumChillPanel.add(inTempPanel);
        JLabel inTempLabel = new JLabel("TEMP IN (F)");
        inTempPanel.add(inTempLabel);
        inTempValue = new JTextField();
        inTempPanel.add(inTempValue);
        inTempPanel.setLayout(new BoxLayout(inTempPanel, BoxLayout.Y_AXIS));
        
        // Inside Humidity
        JPanel inHumPanel = new JPanel();
        tempHumChillPanel.add(inHumPanel);
        JLabel inHumLabel = new JLabel("HUM IN  (%)");
        inHumPanel.add(inHumLabel);
        inHumValue = new JTextField();
        inHumPanel.add(inHumValue);
        inHumPanel.setLayout(new BoxLayout(inHumPanel, BoxLayout.Y_AXIS));
        
        // Wind Chill Panel
        JPanel chillPanel = new JPanel();
        tempHumChillPanel.add(chillPanel);
        JLabel chillLabel = new JLabel("CHILL (F)");
        chillPanel.add(chillLabel);
        chillValue = new JTextField("N/A");
        chillPanel.add(chillValue);
        chillPanel.setLayout(new BoxLayout(chillPanel, BoxLayout.Y_AXIS));
        
        JPanel rainPanels = new JPanel();
        rightDisplayPanel.add(rainPanels);

        // Daily Rain
        JPanel dayRainPanel = new JPanel();
        rainPanels.add(dayRainPanel);
        JLabel dayRainLabel = new JLabel("DAILY RAIN (in)");
        dayRainPanel.add(dayRainLabel);
        dayRainValue = new JTextField();
        dayRainValue.setText("");
        dayRainPanel.add(dayRainValue);
        dayRainPanel.setLayout(new BoxLayout(dayRainPanel, BoxLayout.Y_AXIS));
        
        // Montly Rain
        JPanel monthRainPanel = new JPanel();
        rainPanels.add(monthRainPanel);
        JLabel monthRainLabel = new JLabel("RAIN MO (in)");
        monthRainPanel.add(monthRainLabel);
        monthRainValue = new JTextField();
        monthRainValue.setText("N/A");
        monthRainPanel.add(monthRainValue);
        monthRainPanel.setLayout(new BoxLayout(monthRainPanel, BoxLayout.Y_AXIS));
        
        // Moon Phase Icon and Label
        moonLabel = new JLabel("Moon Icon");
        moonPhaseTextLbl = new JLabel("Moon Phase");        
        loadMoonIcons();
        updateMoonPhaseIconAndLabel();

        moonLabel.setBounds(526, 5, 64, 64);
        MainDisplay.add(moonLabel);
        
        moonPhaseTextLbl.setBounds(522, 78, 100, 20);
        MainDisplay.add(moonPhaseTextLbl);
    }
	
    /**
     * Initialize the bottom status panel
     */
    private void initializeBottomStatusPanel() {
	    bottomStatusPanel = new JPanel();
        bottomStatusPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        frame.getContentPane().add(bottomStatusPanel, BorderLayout.SOUTH);
	    
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
        frame.getContentPane().add(weatherStationsPanel, BorderLayout.NORTH);
        
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
        frame.getContentPane().add(controlsPanel, BorderLayout.EAST);
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
                    System.out.println("Wind Direction selected");
                } else {
                    currentVar = Vars.WIND_SPEED;
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
