package net.tfobz.domsim.travelingsalesman;

public class TravelingSalesmanTest {
	
	static long[][] costmatrix = { { 0, 1, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, 3 },
			{ 1, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 4 },
			{ 1, Integer.MAX_VALUE, 0, 3, 5, Integer.MAX_VALUE },
			{ Integer.MAX_VALUE, 2, Integer.MAX_VALUE, 0, 6, Integer.MAX_VALUE },
			{ Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 6, 0, Integer.MAX_VALUE },
			{ 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 4, 0 } };
	
	public static void main(String[] args) {
		TravelingSalesman s = new TravelingSalesman(costmatrix);
		TravelingSalesman.ausgabearray(s.execute());
	}
}
