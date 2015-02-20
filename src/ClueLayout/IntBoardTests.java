package ClueLayout;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntBoardTests {
	private IntBoard board;
	private BoardCell cell;
	
	@Before
	public void setUp(){
		System.out.println("In @Before");
		board = new IntBoard();
	}
	@Test
	public void testTargets0_3()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertEquals(4, targets.size());

	}
	@Test
	public void testTargets1_1()
	{
		BoardCell cell = board.getCell(1, 0);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
		Assert.assertTrue(targets.contains(board.getCell(1, 1)));
		Assert.assertTrue(targets.contains(board.getCell(0, 0)));
	}
	@Test
	public void testTargets1_2()
	{
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		Assert.assertTrue(targets.contains(board.getCell(0, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
		Assert.assertTrue(targets.contains(board.getCell(0, 2)));

	}
	@Test
	public void testTargets2_4()
	{
		BoardCell cell = board.getCell(2, 0);
		board.calcTargets(cell, 4);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		Assert.assertTrue(targets.contains(board.getCell(0, 2)));

	}
	@Test
	public void testTargets2_1()
	{
		BoardCell cell = board.getCell(2, 3);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));

	}
	@Test
	public void testTargets3_2()
	{
		BoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
	
	}
	@Test
	public void testAdjacency0()
	{
		BoardCell cell = board.getCell(0,0);
		board.calcAdjacencies(cell);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency1()
	{
		BoardCell cell = board.getCell(3,3);
		board.calcAdjacencies(cell);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency2()
	{
		BoardCell cell = board.getCell(1,3);
		board.calcAdjacencies(cell);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));
		Assert.assertTrue(testList.contains(board.getCell(0, 3)));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency3()
	{
		BoardCell cell = board.getCell(3,0);
		board.calcAdjacencies(cell);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(3, 1)));
		Assert.assertTrue(testList.contains(board.getCell(2, 0)));
		Assert.assertEquals(2, testList.size());
	}
	@Test
	public void testAdjacency4()
	{
		BoardCell cell = board.getCell(1,1);
		board.calcAdjacencies(cell);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(2, 1)));
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));

		Assert.assertEquals(4, testList.size());
	}
	@Test
	public void testAdjacency5()
	{
		BoardCell cell = board.getCell(2,2);
		board.calcAdjacencies(cell);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(2, 1)));
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));

		Assert.assertEquals(4, testList.size());
	}
}

