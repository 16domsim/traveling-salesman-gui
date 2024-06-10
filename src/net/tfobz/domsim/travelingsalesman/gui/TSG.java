package net.tfobz.domsim.travelingsalesman.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

/**
 * GUI zu traveling Salesman mit sehr umfangreichen Funktionen.
 * 
 * @author Domenici Simone
 *
 */
public class TSG {

	private JFrame frame;
	private GraphikFrame pnlGraph;
	private JSpinner delayspinner;
	public static final int BEGINSPINNERDELAY = 1000;
	public static final int BEGINSPINNERRADIUS = 40;

	private JTextField newvalueinput;
	private JTextField newnameinput;

	public static final String[] NODECOLORSSTRING = { "red", "green", "blue", "yellow", "orange", "cyan", "pink",
			"black", "gray", "light gray ", "purple" };
	public static final Color[] NODECOLORS = { Color.red, Color.green, Color.blue, Color.yellow, Color.orange,
			Color.cyan, Color.pink, Color.black, Color.gray, Color.lightGray, Color.magenta };
	public static int initialselecteddefaultnodecolor = 0;
	public static int initialselectedselected1nodecolor = 5;
	public static int initialselectedselected2nodecolor = 4;
	public static int initialselectedexecutionnodecolor = 1;
	public static int initialselectededgecolor = 7;
	public static int initialselectededgedescriptioncolor = 2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TSG window = new TSG();
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
	public TSG() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(0, 0, 1700, 950);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnlGraph = new GraphikFrame();
		FlowLayout flowLayout = (FlowLayout) pnlGraph.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);

		JPanel pnlOptions = new JPanel();
		pnlOptions.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton About = new JButton("About ");
		About.setFont(new Font("Verdana", Font.PLAIN, 15));

		JLabel Titel = new JLabel("Traveling Salesman GUI");
		Titel.setFont(new Font("Copperplate", Font.PLAIN, 24));

		JButton Help = new JButton("Help");
		Help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help help = new Help();
				help.setVisible(true);
			}
		});
		Help.setFont(new Font("Verdana", Font.PLAIN, 15));

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGraph.setExecutealgorithm(true);
			}
		});
		btnStart.setFont(new Font("Verdana", Font.PLAIN, 15));

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGraph.setExecutealgorithm(false);
			}
		});
		btnStop.setFont(new Font("Verdana", Font.PLAIN, 15));

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGraph.resetExecution();
			}
		});
		btnReset.setFont(new Font("Verdana", Font.PLAIN, 15));

		SpinnerModel spinnermodel = new SpinnerNumberModel(BEGINSPINNERDELAY, // initial value
				300, // min
				3000, // max
				100);
		JSpinner delayspinner = new JSpinner(spinnermodel);
		delayspinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int spinnerstate = (int) delayspinner.getModel().getValue();
//				System.out.println("Spinnerstate: " + spinnerstate);
				pnlGraph.setExecutionDelay(spinnerstate);

			}

		});

		JButton btnStep = new JButton("Step");
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGraph.nextExecutionStep();
			}
		});
		btnStep.setFont(new Font("Verdana", Font.PLAIN, 15));

		JLabel Subtitle1 = new JLabel("Traveling Salesman Simulation");
		Subtitle1.setFont(new Font("Verdana", Font.PLAIN, 16));

		JLabel lblNewLabel_2 = new JLabel("Delay (ms):");

		JLabel Subtitle2 = new JLabel("Modify Graph");
		Subtitle2.setFont(new Font("Verdana", Font.PLAIN, 16));

		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = -1;
				try {
					value = Integer.valueOf(newvalueinput.getText());
				} catch (Exception g) {
					JOptionPane.showMessageDialog(frame.getContentPane(), "Please input a valid number!");
				}
				if (value != -1) {
					pnlGraph.addEdge(value);
				}
			}
		});
		btnSet.setFont(new Font("Verdana", Font.PLAIN, 15));

		JLabel Subtitle2Subtitle2 = new JLabel("Edge");
		Subtitle2Subtitle2.setFont(new Font("Verdana", Font.PLAIN, 14));

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGraph.delEdge();
			}
		});
		btnDelete.setFont(new Font("Verdana", Font.PLAIN, 15));

		newvalueinput = new JTextField();
		newvalueinput.setColumns(10);

		JLabel newvaluetext = new JLabel("New value");
		newvaluetext.setFont(new Font("Verdana", Font.PLAIN, 14));

		JLabel Subtitle1Subtitle2 = new JLabel("Node");
		Subtitle1Subtitle2.setFont(new Font("Verdana", Font.PLAIN, 14));

		JButton btndeletenode = new JButton("Delete");
		btndeletenode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGraph.delNode();
			}
		});
		btndeletenode.setFont(new Font("Verdana", Font.PLAIN, 15));

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGraph.addNode();
			}
		});
		btnAdd.setFont(new Font("Verdana", Font.PLAIN, 15));

		JButton btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newname = "";
				try {
					newname = newnameinput.getText();
				} catch (Exception g) {
					JOptionPane.showMessageDialog(frame.getContentPane(), "Please input a valid text!");
				}
				if (newname != "")
					pnlGraph.renameNode(newname);

			}
		});
		btnRename.setFont(new Font("Verdana", Font.PLAIN, 15));

		JLabel nameinput = new JLabel("New name");
		nameinput.setFont(new Font("Verdana", Font.PLAIN, 14));

		newnameinput = new JTextField();
		newnameinput.setColumns(10);

		JButton btnDeleteAllNodes = new JButton("Delete all nodes");
		btnDeleteAllNodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGraph.delEverything();
			}
		});
		btnDeleteAllNodes.setFont(new Font("Verdana", Font.PLAIN, 15));

		JButton btnDeleteAllEdges = new JButton("Delete all edges");
		btnDeleteAllEdges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGraph.delAllEdges();
			}
		});
		btnDeleteAllEdges.setFont(new Font("Verdana", Font.PLAIN, 15));

		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlOptions
				.createSequentialGroup()
				.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlOptions.createSequentialGroup().addContainerGap().addComponent(Titel,
								GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
						.addGroup(gl_pnlOptions.createSequentialGroup().addGap(28).addGroup(gl_pnlOptions
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(Subtitle1, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
								.addGroup(gl_pnlOptions.createSequentialGroup()
										.addComponent(nameinput, GroupLayout.PREFERRED_SIZE, 86,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(newnameinput,
												GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlOptions.createSequentialGroup()
										.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
												.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 102,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnStep, GroupLayout.PREFERRED_SIZE, 102,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_2, Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
												.addComponent(Subtitle2Subtitle2)
												.addComponent(btnSet, GroupLayout.PREFERRED_SIZE, 102,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(Subtitle1Subtitle2, GroupLayout.PREFERRED_SIZE, 35,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_pnlOptions.createSequentialGroup()
														.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 60,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnRename, GroupLayout.PREFERRED_SIZE, 90,
																GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_pnlOptions.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
														.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 102,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 102,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(delayspinner, GroupLayout.PREFERRED_SIZE, 91,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 102,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btndeletenode, GroupLayout.PREFERRED_SIZE, 76,
														GroupLayout.PREFERRED_SIZE))
										.addGap(9))))
						.addGroup(gl_pnlOptions.createSequentialGroup().addGap(17).addGroup(gl_pnlOptions
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlOptions.createSequentialGroup()
										.addComponent(btnDeleteAllEdges, GroupLayout.PREFERRED_SIZE, 143,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnDeleteAllNodes,
												GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlOptions.createSequentialGroup()
										.addComponent(About, GroupLayout.PREFERRED_SIZE, 102,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE).addComponent(
												Help, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap())
				.addGroup(gl_pnlOptions.createSequentialGroup().addContainerGap(125, Short.MAX_VALUE)
						.addComponent(Subtitle2).addGap(91))
				.addGroup(gl_pnlOptions.createSequentialGroup().addGap(35)
						.addComponent(newvaluetext, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(newvalueinput, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(106, Short.MAX_VALUE)));
		gl_pnlOptions.setVerticalGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOptions.createSequentialGroup().addGap(14)
						.addComponent(Titel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(Subtitle1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_pnlOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addGroup(gl_pnlOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(delayspinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(53)
						.addGroup(gl_pnlOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnStep, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGap(70).addComponent(Subtitle2).addGap(20).addComponent(Subtitle2Subtitle2)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSet, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
								.addComponent(newvalueinput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(newvaluetext, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addComponent(Subtitle1Subtitle2, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addGap(25)
						.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlOptions.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 44,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnRename, GroupLayout.PREFERRED_SIZE, 44,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(btndeletenode, GroupLayout.PREFERRED_SIZE, 44,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pnlOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameinput, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(newnameinput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(28)
						.addGroup(gl_pnlOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDeleteAllNodes, GroupLayout.PREFERRED_SIZE, 44,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDeleteAllEdges, GroupLayout.PREFERRED_SIZE, 44,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
						.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
								.addComponent(About, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(Help, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGap(17)));
		pnlOptions.setLayout(gl_pnlOptions);

		JPanel pnlSettings = new JPanel();
		pnlSettings.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pnlSettings, 0, 0, Short.MAX_VALUE)
								.addComponent(pnlGraph, GroupLayout.DEFAULT_SIZE, 1341, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(16, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlOptions, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(pnlGraph, GroupLayout.PREFERRED_SIZE, 782, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(pnlSettings,
										GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(8, Short.MAX_VALUE)));

		JLabel Title2 = new JLabel("Settings");
		Title2.setFont(new Font("Verdana", Font.PLAIN, 16));

		SpinnerModel spinnermodel2 = new SpinnerNumberModel(BEGINSPINNERRADIUS, // initial value
				20, // min
				80, // max
				5);

		JSpinner radiusSpinner = new JSpinner(spinnermodel2);
		radiusSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				Graph.RADIUS = (int) radiusSpinner.getModel().getValue();
			}
		});

		JLabel nodeRadiusInput = new JLabel("Node radius (px):");
		nodeRadiusInput.setFont(new Font("Verdana", Font.PLAIN, 14));

		JLabel setDefaultNodecolorInput = new JLabel("Set default nodecolor:");
		setDefaultNodecolorInput.setFont(new Font("Verdana", Font.PLAIN, 14));

		JComboBox defaultColorComboBox = new JComboBox(NODECOLORSSTRING);
		defaultColorComboBox.setSelectedIndex(initialselecteddefaultnodecolor);
		defaultColorComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph.DEFAULTNODECOLOR = NODECOLORS[defaultColorComboBox.getSelectedIndex()];
				pnlGraph.refreshColors();
				System.out.println(NODECOLORS[defaultColorComboBox.getSelectedIndex()].toString());
			}
		});

		JLabel SetSelectedNodecolor1Input = new JLabel("Set selected nodecolor 1:");
		SetSelectedNodecolor1Input.setFont(new Font("Verdana", Font.PLAIN, 14));

		JComboBox selectedColor1ComboBox = new JComboBox(NODECOLORSSTRING);
		selectedColor1ComboBox.setSelectedIndex(initialselectedselected1nodecolor);
		selectedColor1ComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph.SELECTEDNODECOLOR = NODECOLORS[selectedColor1ComboBox.getSelectedIndex()];
				pnlGraph.refreshColors();
			}
		});

		JLabel SetSelectedNodecolor2Input = new JLabel("Set selected nodecolor 2:");
		SetSelectedNodecolor2Input.setFont(new Font("Verdana", Font.PLAIN, 14));

		JComboBox selectedColor2ComboBox = new JComboBox(NODECOLORSSTRING);
		selectedColor2ComboBox.setSelectedIndex(initialselectedselected2nodecolor);
		selectedColor2ComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph.SELECTEDNODECOLOR2 = NODECOLORS[selectedColor2ComboBox.getSelectedIndex()];
				pnlGraph.refreshColors();
			}
		});

		JLabel setExecutionNodecolorInput = new JLabel("Set execution nodecolor:");
		setExecutionNodecolorInput.setFont(new Font("Verdana", Font.PLAIN, 14));

		JComboBox executionColorComboBox = new JComboBox(NODECOLORSSTRING);
		executionColorComboBox.setSelectedIndex(initialselectedexecutionnodecolor);
		executionColorComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph.EXECUTIONNODECOLOR = NODECOLORS[executionColorComboBox.getSelectedIndex()];
				pnlGraph.refreshColors();
			}
		});

		JLabel SetEdgecolorInput = new JLabel("Set edgecolor:");
		SetEdgecolorInput.setFont(new Font("Verdana", Font.PLAIN, 14));

		JLabel SetEdgeDescriptioncolorInput = new JLabel("Set edge distances color:");
		SetEdgeDescriptioncolorInput.setFont(new Font("Verdana", Font.PLAIN, 14));

		JComboBox edgeColorComboBox = new JComboBox(NODECOLORSSTRING);
		edgeColorComboBox.setSelectedIndex(initialselectededgecolor);
		edgeColorComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph.EDGECOLOR = NODECOLORS[edgeColorComboBox.getSelectedIndex()];
				pnlGraph.refreshColors();
			}
		});

		JComboBox edgeDescriptionColorComboBox = new JComboBox(NODECOLORSSTRING);
		edgeDescriptionColorComboBox.setSelectedIndex(initialselectededgedescriptioncolor);
		edgeDescriptionColorComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph.EDGEDESCRIPTIONCOLOR = NODECOLORS[edgeDescriptionColorComboBox.getSelectedIndex()];
				pnlGraph.refreshColors();
			}
		});

		GroupLayout gl_pnlSettings = new GroupLayout(pnlSettings);
		gl_pnlSettings.setHorizontalGroup(gl_pnlSettings.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlSettings
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnlSettings.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlSettings.createSequentialGroup().addComponent(Title2)
								.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE))
						.addGroup(gl_pnlSettings.createSequentialGroup()
								.addComponent(nodeRadiusInput, GroupLayout.PREFERRED_SIZE, 130,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(radiusSpinner, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(60)))
				.addGroup(gl_pnlSettings.createParallelGroup(Alignment.LEADING)
						.addComponent(setDefaultNodecolorInput, GroupLayout.PREFERRED_SIZE, 173,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(setExecutionNodecolorInput, GroupLayout.PREFERRED_SIZE, 192,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlSettings.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSettings.createSequentialGroup().addGap(10).addComponent(defaultColorComboBox,
								GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlSettings.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(executionColorComboBox, GroupLayout.PREFERRED_SIZE, 123,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(39)
				.addGroup(gl_pnlSettings.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlSettings.createSequentialGroup()
								.addComponent(SetSelectedNodecolor1Input, GroupLayout.PREFERRED_SIZE, 195,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(selectedColor1ComboBox,
										GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlSettings.createSequentialGroup()
								.addComponent(SetSelectedNodecolor2Input, GroupLayout.PREFERRED_SIZE, 195,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(selectedColor2ComboBox,
										GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_pnlSettings.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSettings.createSequentialGroup()
								.addComponent(SetEdgecolorInput, GroupLayout.PREFERRED_SIZE, 195,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(edgeColorComboBox,
										GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlSettings.createSequentialGroup()
								.addComponent(SetEdgeDescriptioncolorInput, GroupLayout.PREFERRED_SIZE, 195,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(edgeDescriptionColorComboBox,
										GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(41, Short.MAX_VALUE)));
		gl_pnlSettings
				.setVerticalGroup(gl_pnlSettings.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSettings.createSequentialGroup()
								.addGroup(gl_pnlSettings.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlSettings
										.createSequentialGroup().addContainerGap().addComponent(Title2).addGap(43)
										.addGroup(gl_pnlSettings.createParallelGroup(Alignment.BASELINE)
												.addComponent(nodeRadiusInput, GroupLayout.PREFERRED_SIZE, 18,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(radiusSpinner, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(setExecutionNodecolorInput, GroupLayout.PREFERRED_SIZE,
														18, GroupLayout.PREFERRED_SIZE)
												.addComponent(selectedColor2ComboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(SetSelectedNodecolor2Input, GroupLayout.PREFERRED_SIZE,
														18, GroupLayout.PREFERRED_SIZE)
												.addComponent(executionColorComboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(SetEdgeDescriptioncolorInput, GroupLayout.PREFERRED_SIZE,
														18, GroupLayout.PREFERRED_SIZE)
												.addComponent(
														edgeDescriptionColorComboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_pnlSettings.createSequentialGroup().addGap(21)
												.addGroup(gl_pnlSettings
														.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_pnlSettings
																.createParallelGroup(Alignment.BASELINE)
																.addComponent(selectedColor1ComboBox,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(SetSelectedNodecolor1Input,
																		GroupLayout.PREFERRED_SIZE, 18,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(SetEdgecolorInput,
																		GroupLayout.PREFERRED_SIZE, 18,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		edgeColorComboBox, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_pnlSettings.createParallelGroup(Alignment.BASELINE)
																.addComponent(setDefaultNodecolorInput,
																		GroupLayout.PREFERRED_SIZE, 18,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(defaultColorComboBox,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)))))
								.addContainerGap(32, Short.MAX_VALUE)));
		pnlSettings.setLayout(gl_pnlSettings);
		frame.getContentPane().setLayout(groupLayout);
		About.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.frame.setVisible(true);
			}
		});
	}
}