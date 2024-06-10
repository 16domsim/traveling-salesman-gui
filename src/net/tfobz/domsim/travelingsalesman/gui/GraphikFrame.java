package net.tfobz.domsim.travelingsalesman.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.swing.*;

import net.tfobz.domsim.travelingsalesman.TravelingSalesman;

/**
 * Stellte einen JPanel zur Verfügung mit welchen man einen Graph visualisieren
 * kann.
 * 
 * @author Domenici Simone
 *
 */
public class GraphikFrame extends JPanel implements ActionListener {

	public static final int EHEIGHT = 800;
	public static final int EWIDTH = 1340;

	private int WIDTH;
	private int HEIGHT;
	private int BREAK = 10;
	private Timer timer;
	private Graph graph;

	private boolean mousepressed1 = false;
	private boolean mousepressed2 = false;

	private boolean executealgorithm = false;
	private int executiondelay = TSG.BEGINSPINNERDELAY;
	private int tempdelay = 0;
	private boolean nextstepmode = false;
	private boolean currentnextstep = false;

	public GraphikFrame() {
		graph = new Graph();
		long[][] costmatrix = { { 0, 1, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, 3 },
				{ 1, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 4 },
				{ 1, Integer.MAX_VALUE, 0, 3, 5, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, 2, Integer.MAX_VALUE, 0, 6, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 6, 0, Integer.MAX_VALUE },
				{ 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 4, 0 } };
		graph.traveling = new TravelingSalesman(costmatrix);
		this.HEIGHT = EHEIGHT;
		this.WIDTH = EWIDTH;
		addMouseListener(new Maus());
		addMouseMotionListener(new Mausmotion());
		setBackground(Color.white);
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		timer = new Timer(BREAK, this);

		timer.start();
	}

	/**
	 * Setzt die Graphische Execution neu.
	 */
	public void resetExecution() {
		graph.deleteExecution();
		this.executealgorithm = false;

	}

	public void setExecutealgorithm(boolean executealgorithm) {
		this.executealgorithm = executealgorithm;
		nextstepmode = false;
		currentnextstep = false;
	}

	public void setExecutionDelay(int newdelay) {
		this.executiondelay = newdelay;
	}

	public void nextExecutionStep() {
		nextstepmode = true;
		currentnextstep = true;
		executealgorithm = true;
	}

	public void addEdge(int size) {
		if (!executealgorithm)
			graph.addEdge(size);
	}

	public void delEdge() {
		if (!executealgorithm)
			graph.delEdge();
	}

	public void addNode() {
		if (!executealgorithm)
			graph.traveling.addVertix();
	}

	public void delNode() {
		if (!executealgorithm)
			graph.delNode();
	}

	public void renameNode(String newname) {
		if (!executealgorithm)
			graph.setNodeName(newname);
	}

	public void delAllEdges() {
		if (!executealgorithm)
			graph.traveling.delAllvertix();
	}

	public void delEverything() {
		if (!executealgorithm)
			graph.delAllNodes();
	}

	public void refreshColors() {
		graph.reloadColors();
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		graph.paintGraph(g);

	}

	public void actionPerformed(ActionEvent e) {
		graph.refresh();
		if (executealgorithm) {
			if (!nextstepmode) {
				if (tempdelay >= executiondelay) {
					graph.nextStep();
					tempdelay = 0;
				} else
					tempdelay += BREAK;
			} else {
				if (currentnextstep) {
					graph.nextStep();
					currentnextstep = false;
				}
			}
		} else
			tempdelay = 0;
		repaint();

	}

	private class Maus implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked");
			graph.selectNode(e.getX(), e.getY());

		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("pressed");

//			graph.setName("Hallllo");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("released");
			mousepressed1 = false;
			mousepressed2 = false;

		}

		public void mouseMoved() {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("entered");

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	private class Mausmotion implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (mousepressed2 == false)
				mousepressed2 = true;
			else
				mousepressed1 = true;
//			System.out.println("bool1: "+mousepressed1+", bool2: "+mousepressed2);

			graph.moveNode(e.getX(), e.getY(), mousepressed1);

		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

	}
}
