package net.tfobz.domsim.travelingsalesman;

import java.util.ArrayList;
import java.util.List;

/**
 * Stellt ein Objekt zur Verfügung, das einem erlaubt dynamisch den Traveling
 * Salesman-Algorithmus auszuführen. Es können Konoten dynamisch hinzugefügt
 * bzw. gelöscht werden.
 * 
 * @authors Domenici Simone
 */

public class TravelingSalesman {

	/**
	 * enthät die Knoten
	 */
	private int[] trip = null;
	/**
	 * Kostmatrix die alle Kosten zwischen den einzelnen Knoten beinhaltet
	 */
	public long[][] cost_matrix = null;
	/**
	 * bester Weg
	 */
	private int[] bestTrip = null;
	/**
	 * niedrigst mögliche Kosten
	 */
	private long bestCosts = Integer.MAX_VALUE;
	/**
	 * beinhaltet den Lösungsweg des Traveling-Salesman Algorithmus
	 */
	public List<String> execution = new ArrayList<>();

	/**
	 * Erstell ein neues Teaveling-Salesman Objekt.
	 * 
	 * @param costmatrix Kostenmatrix des Objektes
	 */
	public TravelingSalesman(long[][] costmatrix) {
		setData(costmatrix);
	}

	/**
	 * Gibt die Kostenmatrix zurück.
	 * 
	 * @return Kostenmatrix
	 */
	public long[][] getCost_matrix() {
		return cost_matrix;
	}

	/**
	 * Setzt die Kostmatrix neu.
	 * 
	 * @param cost_matrix
	 */
	private void setCost_matrix(long[][] cost_matrix) {
		this.cost_matrix = new long[cost_matrix.length][cost_matrix.length];
		this.cost_matrix = cost_matrix.clone();
	}

	/**
	 * Gibt den besten Weg zurück.
	 * 
	 * @return der beste Weg
	 */
	public int[] getBestTrip() {
		return bestTrip;
	}

	/**
	 * Setzt eine neue Kante. Die Kante wird nur gesetzt wenn der Start ubd das Ziel
	 * innerhalb der existierenden Kostenmatrix sind und der start ungleich dem Ziel
	 * ist. Außerdem muss die Distanz größer als 0 und kleiner als Integer.MAX_VALUE
	 * sein.
	 * 
	 * @param from Startpunkt
	 * @param to   Endpunkt
	 * @param dis  Distanz
	 */
	public void addEdge(int from, int to, int dis) {
		if (from < this.cost_matrix.length && from >= 0 && to < this.cost_matrix.length && to >= 0 && from != to
				&& dis > 0 && dis < Integer.MAX_VALUE)
			this.cost_matrix[from][to] = dis;
	}

	/**
	 * Löscht die Kante. Die Kante wird nur gelöscht wenn der Start und das Ziel
	 * innerhalb der existierenden Kostenmatrix sind und der start ungleich dem Ziel
	 * ist.
	 * 
	 * @param from Startpunkt
	 * @param to   Endpunkt
	 */
	public void delEdge(int from, int to) {
		if (from < this.cost_matrix.length && from >= 0 && to < this.cost_matrix.length && to >= 0 && from != to)
			this.cost_matrix[from][to] = Integer.MAX_VALUE;
	}

	/**
	 * Fügt einen neuen Knoten hinzu und stellt allle Kanten mit anderen Knoten auf
	 * den Defaultwert Integer.MAX_VALUE.
	 */
	public void addVertix() {
		long[][] help = new long[this.cost_matrix.length + 1][this.cost_matrix.length + 1];

		for (int i = 0; i < this.cost_matrix.length; i++)
			for (int j = 0; j < this.cost_matrix.length; j++)
				help[i][j] = this.cost_matrix[i][j];

		for (int i = 0; i < help.length - 1; i++)
			help[i][help.length - 1] = Integer.MAX_VALUE;
		for (int j = 0; j < help.length - 1; j++)
			help[help.length - 1][j] = Integer.MAX_VALUE;
		help[help.length - 1][help.length - 1] = 0;
		this.cost_matrix = help.clone();
	}

	/**
	 * Löscht den Knoten an der Stelle pos, und damit alle Verbindungen mit jenen
	 * Knoten in der Kostenmatrix. Der Knoten wird nur gelöscht wenn die übergebene
	 * Position pos größer gleich 0 und kleiner gleich als die Anzahl an Knoten ist.
	 * 
	 * @param pos Position vom Knoten welcher gelöscht werden soll
	 */
	public void delVertix(int pos) {
		if (pos >= 0 && pos < cost_matrix.length && cost_matrix.length > 1) {
			long[][] help = new long[this.cost_matrix.length - 1][this.cost_matrix.length - 1];
			for (int i = 0; i < cost_matrix.length; i++)
				cost_matrix[i][pos] = -1;
			for (int j = 0; j < cost_matrix.length; j++)
				cost_matrix[pos][j] = -1;
			int ix = -1;
			for (int i = 0; i < cost_matrix.length; i++) {
				int jx = 0;
				for (int j = 0; j < cost_matrix.length; j++) {
					if (cost_matrix[i][j] != -1) {
						if (ix == -1 || jx == 0)
							ix++;
						help[ix][jx] = cost_matrix[i][j];
						jx++;
					}
				}
			}
			this.cost_matrix = help.clone();
		}
	}

	/**
	 * Löscht alle Kanten in der Kostmatrix
	 */
	public void delAllvertix() {
		for (int i = 0; i < cost_matrix.length; i++) {
			for (int j = 0; j < cost_matrix.length; j++) {
				if (i == j)
					cost_matrix[i][j] = 0;
				else
					cost_matrix[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	/**
	 * Setzt die Variablen costmatrix und trip.
	 * 
	 * @param costmatrix Kostenmatrix welche gesetzt werden soll
	 */
	public void setData(long[][] costmatrix) {
		this.setCost_matrix(costmatrix);
		trip = new int[this.cost_matrix.length];
	}

	/**
	 * Führt den Travelingsalesman-Algorithmus aus und gibt die Lösung in einem
	 * Array zurück.
	 * 
	 * @return Bester Weg
	 */
	public int[] execute() {
		trip = new int[this.cost_matrix.length];
		for (int i = 0; i < this.trip.length; i++)
			trip[i] = i;
		traveling(1, 0);
		if (bestTrip != null) {
			String h = "";
			for (int i = 0; i < bestTrip.length; i++)
				h += bestTrip[i];
			execution.add(h);
		}
		if (this.bestTrip == null)
			return new int[0];
		return this.bestTrip;
	}

	/**
	 * Rekursive Methode die den Traveling Salesman Algorithmus ausführt. Es werden
	 * alle Permutationen mit Abbruchbedingung, und zwar wenn die Kosten bereits
	 * höher sind als die biesherigen Kosten, ausgeführt.
	 * 
	 * @param pos   poistion der aktuellen Permutation
	 * @param costs bisherigen Kosten
	 */
	private void traveling(int pos, long costs) {
		long newCosts = 0;
		if (pos == trip.length - 1) {
			newCosts = costs + cost_matrix[trip[pos - 1]][trip[pos]] + cost_matrix[trip[pos]][trip[0]];
			if (newCosts < bestCosts) {
				bestTrip = trip.clone();
				bestCosts = newCosts;
			}
		} else {
			int h = trip[pos];
			for (int i = pos; i < trip.length; i++) {
				newCosts = costs + cost_matrix[trip[pos - 1]][trip[i]];
				if (newCosts < bestCosts) {
					trip[pos] = trip[i];
					trip[i] = h;
					outPerm(trip, pos + 1);
					traveling(pos + 1, newCosts);
					trip[i] = trip[pos];
				}
			}
			trip[pos] = h;
		}
	}

	/**
	 * Gibt den Aktuellen Trip vom Index 0 bis Index pos aus, und speicher ihn ab.
	 * 
	 * @param trip trip welcher ausgegeben wreden soll
	 * @param pos  Poition bis welcher der Trip ausgegeben werden soll
	 */
	public void outPerm(int[] trip, int pos) {
		String h = "";
		for (int i = 0; i < pos; i++)
			h += trip[i];
		execution.add(h);
		System.out.println(h);
	}

	/**
	 * Gibt ein Array aus.
	 * 
	 * @param array Array
	 */
	public static void ausgabearray(int[] array) {
		System.out.println("\nBester weg:");
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println("\n");
	}

	/**
	 * Gibt ein zweidimensionales Array aus.
	 * 
	 * @param array zweidimensionales Array
	 */
	public static void ausgabearray(long[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] == Integer.MAX_VALUE)
					System.out.print("M ");
				else
					System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}