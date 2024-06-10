package net.tfobz.domsim.travelingsalesman.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Nur zu Testzwecken um Graphikframe zu testen.
 * 
 * @author Domenici Simone
 *
 */
public class GraphikFrameTest extends JFrame {
	private static final int HOEHE = 780;
	private static final int BREITE = 1500;

	public GraphikFrameTest() {
		add(new GraphikFrame());
		setResizable(false);
		pack();
		setTitle("x");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame s = new GraphikFrameTest();
			s.setVisible(true);
		});
	}
}
