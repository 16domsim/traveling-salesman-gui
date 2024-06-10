package net.tfobz.domsim.travelingsalesman.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * Enthaltet das About Frame welches in TSG aufgerufen werden kann.
 * 
 * @author Domenici Simone
 *
 */
public class About {

	public JDialog frame;

	/**
	 * Create the application.
	 */
	public About() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 177);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JTextArea txtrThisProgramWas = new JTextArea();
		txtrThisProgramWas.setEditable(false);
		txtrThisProgramWas.setText("This Program was made during a school\nproject. It shows how the Traveling Salesman Algoritm works with\nan advanced graphical users interface.\n\nCopyright by Simone Domenici and Christian Liso\n\n\nmade with MacBook Pro");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(txtrThisProgramWas, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(txtrThisProgramWas, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
