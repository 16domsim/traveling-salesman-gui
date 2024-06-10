package net.tfobz.domsim.travelingsalesman.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;

/**
 * Enthaltet das Help Frame welches in TSG aufgerufen werden kann.
 * 
 * @author Domenici Simone
 *
 */
public class Help extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Help() {
		setTitle("Help TSG");
		setBounds(100, 100, 396, 139);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JTextArea txtrUserGuide = new JTextArea();
		txtrUserGuide.setEditable(false);
		txtrUserGuide.setText("\n\nAsk me personally if there are any incerties!\n\n");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(txtrUserGuide, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addComponent(txtrUserGuide, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(23, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
	}

}
