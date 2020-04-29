
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

/**
 * Main console GUI that allows the user to choose up to 8 weather stations to attach to.
 * @author yolandaxu
 *
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
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		
		
		//the bottom status
		String temp = "Cool";
		String hum = "not humid";
		if (outTemp > 70) temp = "Warm";
		if (outTemp > 85) temp = "Hot";
		if (outHum > 50) hum = "humid";
		if (outHum > 65) hum = "very humid";
		
		JLabel statusLabel = new JLabel(temp + " and " + hum);
		statusPanel.add(statusLabel);
		
		//the control buttons
		JPanel controlsPanel = new JPanel();
		frame.getContentPane().add(controlsPanel, BorderLayout.EAST);
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		
		JButton heatTempButton = new JButton("HEAT/TEMP");
		heatTempButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		controlsPanel.add(heatTempButton);
		
		JButton dewHumButton = new JButton("DEW/HUM");
		controlsPanel.add(dewHumButton);
		
		JButton chillWindButton = new JButton("CHILL/WIND");
		controlsPanel.add(chillWindButton);
		
		JButton solarRainButton = new JButton("SOLAR/RAINDAY");
		solarRainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		controlsPanel.add(solarRainButton);
		
		JButton UvRainButton = new JButton("UV/RAINYR");
		controlsPanel.add(UvRainButton);
		
		JButton etBarButton = new JButton("ET/BAR");
		controlsPanel.add(etBarButton);
		
		
		//weather station combo boxes
		
		JPanel weatherSationsPanel = new JPanel();
		frame.getContentPane().add(weatherSationsPanel, BorderLayout.NORTH);
		
		JLabel stationsLabel = new JLabel("Stations:");
		weatherSationsPanel.add(stationsLabel);
		
		JComboBox station1 = new JComboBox();
		station1.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
		weatherSationsPanel.add(station1);
		
		JComboBox station2 = new JComboBox();
		station2.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
		weatherSationsPanel.add(station2);
		
		JComboBox station3 = new JComboBox();
		station3.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
		weatherSationsPanel.add(station3);
		
		JComboBox station4 = new JComboBox();
		station4.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
		weatherSationsPanel.add(station4);
		
		JComboBox station5 = new JComboBox();
		station5.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
		weatherSationsPanel.add(station5);
		
		JComboBox station6 = new JComboBox();
		station6.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
		weatherSationsPanel.add(station6);
		
		JComboBox station7 = new JComboBox();
		station7.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
		weatherSationsPanel.add(station7);
		
		JComboBox station8 = new JComboBox();
		station8.setModel(new DefaultComboBoxModel(new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4", "TYPE 5"}));
		weatherSationsPanel.add(station8);
		
		JPanel MainDisplay = new JPanel();
		FlowLayout flowLayout = (FlowLayout) MainDisplay.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		frame.getContentPane().add(MainDisplay, BorderLayout.CENTER);
		
		JPanel leftDisplayPanel = new JPanel();
		MainDisplay.add(leftDisplayPanel);
		
		JPanel compassPanel = new JPanel();
		leftDisplayPanel.add(compassPanel);
		
		JPanel graphPanel = new JPanel();
		leftDisplayPanel.add(graphPanel);
		
		leftDisplayPanel.setLayout(new BoxLayout(leftDisplayPanel, BoxLayout.Y_AXIS));
		
		JPanel rightDisplayPanel = new JPanel();
		MainDisplay.add(rightDisplayPanel, BorderLayout.EAST);
		
		JPanel moonAndDatePanel = new JPanel();
		rightDisplayPanel.add(moonAndDatePanel);
		rightDisplayPanel.setLayout(new BoxLayout(rightDisplayPanel, BoxLayout.Y_AXIS));
		
		JPanel moonPhase = new JPanel();
		moonAndDatePanel.add(moonPhase);
		
		timeDate = new JTextField();
		moonAndDatePanel.add(timeDate);
		timeDate.setText("Time/Date");
		timeDate.setEditable(false);
		
		JPanel tempHumBaroPanel = new JPanel();
		rightDisplayPanel.add(tempHumBaroPanel);
		
		JPanel tempOutPanel = new JPanel();
		tempHumBaroPanel.add(tempOutPanel);
		
		JLabel outTempLabel = new JLabel("TEMP OUT (F)");
		tempOutPanel.add(outTempLabel);
		
		outTempValue = new JTextField();
		tempOutPanel.add(outTempValue);
		tempOutPanel.setLayout(new BoxLayout(tempOutPanel, BoxLayout.Y_AXIS));
		
		JPanel outHumPanel = new JPanel();
		tempHumBaroPanel.add(outHumPanel);
		
		JLabel outHumLabel = new JLabel("HUM OUT (%)");
		outHumPanel.add(outHumLabel);
		
		outHumValue = new JTextField();
		outHumPanel.add(outHumValue);
		outHumPanel.setLayout(new BoxLayout(outHumPanel, BoxLayout.Y_AXIS));
		
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
		
		JPanel inTempPanel = new JPanel();
		tempHumChillPanel.add(inTempPanel);
		
		JLabel inTempLabel = new JLabel("TEMP IN (F)");
		inTempPanel.add(inTempLabel);
		
		inTempValue = new JTextField();
		inTempPanel.add(inTempValue);
		inTempPanel.setLayout(new BoxLayout(inTempPanel, BoxLayout.Y_AXIS));
		
		JPanel inHumPanel = new JPanel();
		tempHumChillPanel.add(inHumPanel);
		
		JLabel inHumLabel = new JLabel("HUM IN  (%)");
		inHumPanel.add(inHumLabel);
		
		inHumValue = new JTextField();
		inHumPanel.add(inHumValue);
		inHumPanel.setLayout(new BoxLayout(inHumPanel, BoxLayout.Y_AXIS));
		
		JPanel chillPanel = new JPanel();
		tempHumChillPanel.add(chillPanel);
		
		JLabel chillLabel = new JLabel("CHILL (F)");
		chillPanel.add(chillLabel);
		
		chillValue = new JTextField("N/A");
		chillPanel.add(chillValue);
		chillPanel.setLayout(new BoxLayout(chillPanel, BoxLayout.Y_AXIS));
		
		JPanel rainPanels = new JPanel();
		rightDisplayPanel.add(rainPanels);
		
		JPanel dayRainPanel = new JPanel();
		rainPanels.add(dayRainPanel);
		
		JLabel dayRainLabel = new JLabel("DAILY RAIN (in)");
		dayRainPanel.add(dayRainLabel);
		
		dayRainValue = new JTextField();
		dayRainValue.setText("");
		dayRainPanel.add(dayRainValue);
		dayRainPanel.setLayout(new BoxLayout(dayRainPanel, BoxLayout.Y_AXIS));
		
		JPanel monthRainPanel = new JPanel();
		rainPanels.add(monthRainPanel);
		
		JLabel monthRainLabel = new JLabel("RAIN MO (in)");
		monthRainPanel.add(monthRainLabel);
		
		monthRainValue = new JTextField();
		monthRainValue.setText("N/A");
		monthRainPanel.add(monthRainValue);
		monthRainPanel.setLayout(new BoxLayout(monthRainPanel, BoxLayout.Y_AXIS));
	}

}
