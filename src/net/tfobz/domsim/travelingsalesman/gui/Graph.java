package net.tfobz.domsim.travelingsalesman.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import net.tfobz.domsim.travelingsalesman.TravelingSalesman;

/**
 * Klasse die ein Graph Objekt zur verfügung stellt mit welchen man einen Graph
 * zeichnen kann. Hinzugefügte Knoten werden automatisch hinzugefügt, man kann
 * die einzelnen Knoten außerdem mit integrierter Kollision detection
 * verschieben.
 * 
 * 
 * @author Domenici Simone
 *
 */
public class Graph {

	/**
	 * Radius eines Knotens in der Graphischen Darstellung
	 */
	public static int RADIUS = TSG.BEGINSPINNERRADIUS;
	private static final int DEFAULTNEXTX = 500;
	private static final int DEFAULTNEXTY = 200;
	/**
	 * Textstil der Knotenbeschreibung
	 */
	private static final Font EDGEDESCRIPTION = new Font("", 0, 30);
	/**
	 * Textstil der Kantenbeschreibung
	 */
	private static final Font NODEDESCRIPTION = new Font("", 0, 20);
	/**
	 * Default Farbe eines Knotens in der Graphischen Darstellung
	 */
	public static Color DEFAULTNODECOLOR = TSG.NODECOLORS[TSG.initialselecteddefaultnodecolor];
	/**
	 * Markierte Farbe eines Knotens in der Graphischen Darstellung
	 */
	public static Color SELECTEDNODECOLOR = TSG.NODECOLORS[TSG.initialselectedselected1nodecolor];
	/**
	 * Markierte Farbe eines zweiten Knotens in der Graphischen Darstellung
	 */
	public static Color SELECTEDNODECOLOR2 = TSG.NODECOLORS[TSG.initialselectedselected2nodecolor];
	/**
	 * Farbe eines Knotens in der Graphischen Darstellung wenn Traveling
	 * Salesman-Algorithmus ausgeführt wird
	 */
	public static Color EXECUTIONNODECOLOR = TSG.NODECOLORS[TSG.initialselectedexecutionnodecolor];
	/**
	 * Farbe einer Kante
	 */
	public static Color EDGECOLOR = TSG.NODECOLORS[TSG.initialselectededgecolor];
	/**
	 * Farbe einer Kantebeschreibung
	 */
	public static Color EDGEDESCRIPTIONCOLOR = TSG.NODECOLORS[TSG.initialselectededgedescriptioncolor];
	private static int nodeNameDefault = 0;

	/**
	 * X-Positionen der Knoten
	 */
	private List<Integer> xPos = new ArrayList<Integer>();
	/**
	 * Y-Positionen der Knoten
	 */
	private List<Integer> yPos = new ArrayList<Integer>();
	private int nextX = 10;
	private int nextY = 10;
	/**
	 * Namen der Knoten
	 */
	private List<String> nodeNames = new ArrayList<String>();
	private int selectedItemIndex = -1;
	private int selectedItemIndex2 = -1;
	private int staticposmovement = -1;
	/**
	 * Farben der Knoten
	 */
	private List<Color> nodeColors = new ArrayList<Color>();

	/**
	 * Zur Algorithmusexecution notwendig
	 */
	public TravelingSalesman traveling;
	private int executionindex = 0;
	private int[] executionpos = null;

	/**
	 * Überprüft ob neue Knoten hinzugekommen sind und fügt sie gegebenfalls im
	 * Graph ein.
	 */
	public void refresh() {
		int am = -1;
		try {
			am = traveling.getCost_matrix().length;
		} catch (Exception e) {
		}
//		System.out.println(am);
		for (int i = xPos.size(); i < am; i++) {
			boolean control;
			xPos.add(0);
			yPos.add(0);
			nodeNames.add(String.valueOf(nodeNameDefault));
			nodeNameDefault++;
			nodeColors.add(DEFAULTNODECOLOR);
			boolean orderreached = false;
			int attempts = 0;
			do {
				control = setXYpos(nextX, nextY, xPos.size() - 1);
				nextX += DEFAULTNEXTX;
				if (nextX >= GraphikFrame.EWIDTH) {
					nextX = 10;
					nextY += DEFAULTNEXTY;
				}
				if (orderreached || nextY >= GraphikFrame.EHEIGHT) {
					orderreached = true;
					nextX = (int) (Math.random() * GraphikFrame.EWIDTH);
					nextY = (int) (Math.random() * GraphikFrame.EHEIGHT);
				}
				attempts++;
			} while (attempts < 10000 && !control);
		}
		nextX = 10;
		nextY = 10;
	}

	/**
	 * Setzt die X- und Y- Koordinate eines Knotens neu. Dabei wird überprüft dass
	 * die neuen Koordinaten nicht außerhalb vom Rand gehen und dass der Knoten
	 * nicht mit anderen Knoten zusammenstoßt.
	 * 
	 * @param x   neue x-Koordinate des Konotens
	 * @param y   neue y-Koordinate des Konotens
	 * @param pos Position des zu verschiebenden Knotens
	 */
	private boolean setXYpos(int x, int y, int pos) {
//		System.out.println("x: " + x + ", y: " + y + ", pos: " + pos);
		if (x >= 0 && x + 2 * RADIUS < GraphikFrame.EWIDTH && y >= 0 && y + 2 * RADIUS < GraphikFrame.EHEIGHT) {
			boolean valid = true;
			try {
				xPos.get(pos);
			} catch (IndexOutOfBoundsException e) {
				valid = false;
			}
			if (valid) {
				for (int i = 0; i < xPos.size() && valid; i++) {
					if (i != pos) {
						if (Math.abs(xPos.get(i) - x) <= RADIUS * 2 && Math.abs(yPos.get(i) - y) <= RADIUS * 2)
							valid = false;
					}
				}
//				System.out.println(valid);
//				System.out.println("Befor:	x: " + xPos.get(pos) + ", y: " + yPos.get(pos));
				if (valid) {
					xPos.set(pos, x);
					yPos.set(pos, y);
//					System.out.println("After:	x: " + xPos.get(pos) + ", y: " + yPos.get(pos));
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * Bewegt einen Knoten, die Positionsüberprüfung wird deaktiviert wenn ein
	 * Knoten markiert ist und der übergebene Parameter statics auf true ist. Um die
	 * gültigkeit der übergebenen Koordinaten zu überprüfen wird intern die Methode
	 * setXYpos aufgerufen.
	 * 
	 * @param x       neue x-Koordinate des Konotens
	 * @param y       neue y-Koordinate des Konotens
	 * @param statics beschreibt ob die Positionsüberprüfung aktiviert werden soll
	 */
	public void moveNode(int x, int y, boolean statics) {
//		System.out.println(staticposmovement);
		if (!statics || staticposmovement == -1) {
			selectNode(x, y);
			staticposmovement = selectedItemIndex;
//			System.out.println(staticposmovement);
		}
		if (staticposmovement != -1)
			setXYpos(x, y, staticposmovement);
	}

	/**
	 * Markiert einen Knoten wenn er sich innerhalb der übergebenen Koordinaten
	 * befindet.
	 * 
	 * @param x zu überprüfende x-Koordinate
	 * @param y zu überprüfende y-Koordinate
	 */
	public void selectNode(int x, int y) {
		if (selectedItemIndex != -1 && selectedItemIndex < xPos.size())
			nodeColors.set(selectedItemIndex, DEFAULTNODECOLOR);
		if (selectedItemIndex2 != -1 && selectedItemIndex2 < xPos.size())
			nodeColors.set(selectedItemIndex2, DEFAULTNODECOLOR);

		selectedItemIndex2 = selectedItemIndex;

		selectedItemIndex = -1;
		for (int i = 0; i < xPos.size() && selectedItemIndex == -1; i++) {
//			System.out.println("x: "+xPos.get(i)+", y: "+yPos.get(i));
			if (Math.abs(xPos.get(i) - x) <= RADIUS * 2 && Math.abs(yPos.get(i) - y) <= RADIUS * 2)
				selectedItemIndex = i;
		}
		if (selectedItemIndex2 != -1 && selectedItemIndex2 < xPos.size())
			nodeColors.set(selectedItemIndex2, SELECTEDNODECOLOR2);
		if (selectedItemIndex != -1)
			nodeColors.set(selectedItemIndex, SELECTEDNODECOLOR);
//		System.out.println(selectedItemIndex);
	}

	/**
	 * Zeichnet den Graphen.
	 * 
	 * @param g Zeichnenoberfläche
	 */
	public void paintGraph(Graphics g) {
		g.setFont(EDGEDESCRIPTION);
		if (traveling.cost_matrix != null) {
			for (int i = 0; i < traveling.cost_matrix.length; i++) {
				for (int j = 0; j < traveling.cost_matrix.length; j++) {
					if (traveling.cost_matrix[i][j] != 0 && traveling.cost_matrix[i][j] != Integer.MAX_VALUE) {
						boolean executioncolor = false;
						if (executionindex > 0 && executionpos != null) {
							int ipos = -2;
							int jpos = -2;
							for (int i2 = 0; i2 < executionpos.length && ipos == -2; i2++) {
								if (executionpos[i2] == i)
									ipos = i2;
							}
							for (int i2 = 0; i2 < executionpos.length && jpos == -2; i2++) {
								if (executionpos[i2] == j)
									jpos = i2;
							}
							if (Math.abs(ipos - jpos) < 2 && ipos != -2 && jpos != -2)
								executioncolor = true;
						}
						if (executioncolor)
							g.setColor(EXECUTIONNODECOLOR);
						else
							g.setColor(EDGEDESCRIPTIONCOLOR);
						g.drawString(String.valueOf(traveling.cost_matrix[i][j]),
								((xPos.get(i) + RADIUS) + (xPos.get(j) + RADIUS)) / 2,
								((yPos.get(i) + RADIUS) + (yPos.get(j) + RADIUS)) / 2);
						if (executioncolor)
							g.setColor(EXECUTIONNODECOLOR);
						else
							g.setColor(EDGECOLOR);
						g.drawLine(xPos.get(i) + RADIUS, yPos.get(i) + RADIUS, xPos.get(j) + RADIUS,
								yPos.get(j) + RADIUS);
					}
				}
			}
			g.setFont(NODEDESCRIPTION);
			for (int i = 0; i < xPos.size(); i++) {
				g.setColor(nodeColors.get(i));
				g.fillOval(xPos.get(i), yPos.get(i), RADIUS * 2, RADIUS * 2);
				if (nodeColors.get(i) != Color.black)
					g.setColor(Color.black);
				else
					g.setColor(Color.white);
				g.drawString(nodeNames.get(i),
						xPos.get(i) + RADIUS - NODEDESCRIPTION.getSize() / 4 * nodeNames.get(i).length(),
						yPos.get(i) + RADIUS + NODEDESCRIPTION.getSize() / 4);
			}
		}

	}

	/**
	 * Passt die Farben der Knoten entsprechend den TravelingSalesman Algorithmus
	 * an.
	 */
	public void nextStep() {
		if (traveling.execution.isEmpty() || executionindex == 0)
			traveling.execute();
		if (executionindex < traveling.execution.size()) {
			if (executionindex < traveling.execution.size() - 1) {
				for (int i = 0; i < nodeColors.size(); i++)
					nodeColors.set(i, DEFAULTNODECOLOR);
				if (executionpos == null)
					executionpos = new int[xPos.size()];
				for (int i = 0; i < executionpos.length; i++)
					executionpos[i] = -1;
				for (int i = 0; i < traveling.execution.get(executionindex).length() && i < nodeColors.size(); i++) {
					executionpos[i] = Integer
							.valueOf((String.valueOf(traveling.execution.get(executionindex).charAt(i))));
					nodeColors.set(executionpos[i], EXECUTIONNODECOLOR);
				}
			} else {
				for (int i = 0; i < nodeColors.size(); i++)
					nodeColors.set(i, DEFAULTNODECOLOR);
				if (traveling.getBestTrip() != null) {
					for (int i = 0; i < traveling.execution.get(executionindex).length()
							&& i < nodeColors.size(); i++) {
						executionpos[i] = Integer
								.valueOf((String.valueOf(traveling.execution.get(executionindex).charAt(i))));
						nodeColors.set(executionpos[i], EXECUTIONNODECOLOR);
					}
				}
			}
			executionindex++;
		} else {
			executionpos = null;
			if (executionindex < traveling.execution.size() + 2) {
				for (int i = 0; i < nodeColors.size(); i++)
					nodeColors.set(i, DEFAULTNODECOLOR);
				executionindex++;
			}
		}
	}

	public void reloadColors() {
		for (int i = 0; i < nodeColors.size(); i++)
			nodeColors.set(i, DEFAULTNODECOLOR);
		if (selectedItemIndex != -1 && selectedItemIndex < xPos.size())
			nodeColors.set(selectedItemIndex, SELECTEDNODECOLOR);
		if (selectedItemIndex2 != -1 && selectedItemIndex2 < xPos.size())
			nodeColors.set(selectedItemIndex2, SELECTEDNODECOLOR2);
	}

	/**
	 * Beim aufruf dieser Methode wird die Veranschaulichung der GUI annulliert.
	 */
	public void deleteExecution() {
		executionindex = 0;
		for (int i = 0; i < nodeColors.size(); i++)
			nodeColors.set(i, DEFAULTNODECOLOR);
	}

	public void delEdge() {
		if (selectedItemIndex != -1 && selectedItemIndex2 != -1) {
			traveling.delEdge(selectedItemIndex, selectedItemIndex2);
			traveling.delEdge(selectedItemIndex2, selectedItemIndex);
		}
	}

	public void addEdge(int size) {
		if (selectedItemIndex != -1 && selectedItemIndex2 != -1) {
			traveling.addEdge(selectedItemIndex, selectedItemIndex2, size);
		}
	}

	public void delNode() {
		if (selectedItemIndex != -1) {
			traveling.delVertix(selectedItemIndex);
			xPos.remove(selectedItemIndex);
			yPos.remove(selectedItemIndex);
			nodeColors.remove(selectedItemIndex);
			nodeNames.remove(selectedItemIndex);
		}
	}

	/**
	 * Setzt den Namen des markierten Konotens neu. Dabei muss der übergebene
	 * Parametre ungleich null und nicht leer sein, ansonsten wird der Namen nicht
	 * gesetzt und der bereits vorhandene Nmae wird beibehalten.
	 * 
	 * @param newname zu setztender neuer name
	 */
	public void setNodeName(String newname) {
		if (newname != null && !newname.isEmpty())
			nodeNames.set(selectedItemIndex, newname);
	}

	public void delAllNodes() {
		for (int i = 0; i + 1 < traveling.cost_matrix.length;)
			traveling.delVertix(i);
		xPos = new ArrayList<Integer>();
		xPos.add(10);
		yPos = new ArrayList<Integer>();
		yPos.add(10);
		nodeColors = new ArrayList<Color>();
		nodeColors.add(DEFAULTNODECOLOR);
		nodeNames = new ArrayList<String>();
		Graph.nodeNameDefault = 0;
		nodeNames.add(String.valueOf(Graph.nodeNameDefault));
		Graph.nodeNameDefault++;
	}
}