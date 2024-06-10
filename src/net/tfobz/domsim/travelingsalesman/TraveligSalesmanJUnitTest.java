package net.tfobz.domsim.travelingsalesman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * Testet das Travelingsalesman Objekt.
 * 
 * @author Domenici Simone
 *
 */
class TraveligSalesmanJUnitTest {

	TravelingSalesman s = null;

	static long[][] costmatrix = { { 0, 1, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, 3 },
			{ 1, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 4 },
			{ 1, Integer.MAX_VALUE, 0, 3, 5, Integer.MAX_VALUE },
			{ Integer.MAX_VALUE, 2, Integer.MAX_VALUE, 0, 6, Integer.MAX_VALUE },
			{ Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 6, 0, Integer.MAX_VALUE },
			{ 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 4, 0 } };

//	@BeforeAll
//	public void instance() {
//		s = new TravelingSalesman(costmatrix);
//	}

	@Test
	public void execute_algorithm() {
		s = new TravelingSalesman(costmatrix);
		int[] richtig = { 0, 5, 4, 2, 3, 1 };
		int[] ausgabe = s.execute();
		assertEquals(ausgabe[0], richtig[0]);
		assertEquals(ausgabe[1], richtig[1]);
		assertEquals(ausgabe[2], richtig[2]);
		assertEquals(ausgabe[3], richtig[3]);
		assertEquals(ausgabe[4], richtig[4]);
		assertEquals(ausgabe[5], richtig[5]);
	}

	@Test
	public void add_edge() {
		s = new TravelingSalesman(costmatrix);
		s.addEdge(0, 1, 2);
		assertEquals(s.getCost_matrix()[0][1], 2);
		s.addEdge(0, 1, 0);
		assertEquals(s.getCost_matrix()[0][1], 2);
		s.addEdge(0, 1, Integer.MAX_VALUE);
		assertEquals(s.getCost_matrix()[0][1], 2);
		s.addEdge(0, 0, 1);
		assertEquals(s.getCost_matrix()[0][0], 0);
		// Wen keine Exceptions geworfen werden stimmt der Folgenede Code
		s.addEdge(0, -1, 1);
		s.addEdge(-1, 0, 1);
		s.addEdge(-1, -1, 1);
		s.addEdge(0, 6, 1);
		s.addEdge(6, 0, 1);
		s.addEdge(6, 6, 1);
	}

	@Test
	public void del_edge() {
		s = new TravelingSalesman(costmatrix);
		s.delEdge(0, 1);
		assertEquals(s.getCost_matrix()[0][1], Integer.MAX_VALUE);
		s.delEdge(0, 0);
		assertEquals(s.getCost_matrix()[0][0], 0);
		// Wen keine Exceptions geworfen werden stimmt der Folgenede Code
		s.delEdge(0, -1);
		s.delEdge(-1, 0);
		s.delEdge(-1, -1);
		s.delEdge(0, 6);
		s.delEdge(6, 0);
		s.delEdge(6, 6);
	}

	@Test
	public void addVertix() {
		long[][] costmatrix2 = { { 0, 2, 3 }, { Integer.MAX_VALUE, 0, 9 }, { 1, 2, Integer.MAX_VALUE } };
		s = new TravelingSalesman(costmatrix2);
		s.addVertix();
		assertEquals(s.getCost_matrix()[0][0], 0);
		assertEquals(s.getCost_matrix()[0][1], 2);
		assertEquals(s.getCost_matrix()[2][0], 1);
		assertEquals(s.getCost_matrix()[2][2], Integer.MAX_VALUE);
		assertEquals(s.getCost_matrix()[3][1], Integer.MAX_VALUE);
		assertEquals(s.getCost_matrix()[3][2], Integer.MAX_VALUE);
		assertEquals(s.getCost_matrix()[1][3], Integer.MAX_VALUE);
		assertEquals(s.getCost_matrix()[2][3], Integer.MAX_VALUE);
		assertEquals(s.getCost_matrix()[3][3], 0);

	}

	@Test
	public void delVertix1() {
		long[][] costmatrix2 = { { 0, 2, 3 }, { Integer.MAX_VALUE, 0, 9 }, { 1, 2, Integer.MAX_VALUE } };
		s = new TravelingSalesman(costmatrix2);
		s.delVertix(1);
		assertEquals(s.getCost_matrix().length, 2);
		assertEquals(s.getCost_matrix()[0][0], 0);
		assertEquals(s.getCost_matrix()[0][1], 3);
		assertEquals(s.getCost_matrix()[1][0], 1);
		assertEquals(s.getCost_matrix()[1][1], Integer.MAX_VALUE);
	}

	@Test
	public void delVertix2() {
		long[][] costmatrix2 = { { 0, 2, 3 }, { Integer.MAX_VALUE, 0, 9 }, { 1, 2, Integer.MAX_VALUE } };
		s = new TravelingSalesman(costmatrix2);
		s.delVertix(0);
		assertEquals(s.getCost_matrix().length, 2);
		assertEquals(s.getCost_matrix()[0][0], 0);
		assertEquals(s.getCost_matrix()[0][1], 9);
		assertEquals(s.getCost_matrix()[1][0], 2);
		assertEquals(s.getCost_matrix()[1][1], Integer.MAX_VALUE);
	}
}
