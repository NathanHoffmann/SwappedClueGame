package clueGame;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;

public class BoardAdjTargetTests {
	private static Board board;
	@BeforeClass
	public static void setUp() throws FileNotFoundException, BadConfigFormatException {
		ClueGame game = new ClueGame();
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		LinkedList<BoardCell> testList = board.getAdjList(0, 0);
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(4, 0);
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(15, 17);
		Assert.assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(18, 11);
		Assert.assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(20, 9);
		Assert.assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(8, 13);
		Assert.assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		LinkedList<BoardCell> testList = board.getAdjList(4, 13);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(4, 14)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(21, 15);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(21, 14)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(4, 2);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(5, 2)));
		//TEST DOORWAY UP
		testList = board.getAdjList(17, 9);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(16, 9)));
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		LinkedList<BoardCell> testList = board.getAdjList(20, 13);
		Assert.assertTrue(testList.contains(board.getCellAt(20, 12)));
		Assert.assertTrue(testList.contains(board.getCellAt(20, 14)));
		Assert.assertTrue(testList.contains(board.getCellAt(21, 13)));
		Assert.assertEquals(3, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(6, 16);
		Assert.assertTrue(testList.contains(board.getCellAt(5, 16)));
		Assert.assertTrue(testList.contains(board.getCellAt(6, 15)));
		Assert.assertTrue(testList.contains(board.getCellAt(6, 17)));
		Assert.assertEquals(3, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(22, 14);
		Assert.assertTrue(testList.contains(board.getCellAt(22, 15)));
		Assert.assertTrue(testList.contains(board.getCellAt(22, 13)));
		Assert.assertTrue(testList.contains(board.getCellAt(21, 14)));
		Assert.assertTrue(testList.contains(board.getCellAt(23, 14)));
		Assert.assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(13, 2);
		Assert.assertTrue(testList.contains(board.getCellAt(15, 2)));
		Assert.assertTrue(testList.contains(board.getCellAt(12, 2)));
		Assert.assertTrue(testList.contains(board.getCellAt(13, 1)));
		Assert.assertTrue(testList.contains(board.getCellAt(13, 3)));
		Assert.assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are SKY BLUE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board, just one walkway piece
		LinkedList<BoardCell> testList = board.getAdjList(0, 5);
		System.out.println(testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(0, 6)));
		Assert.assertTrue(testList.contains(board.getCellAt(1,5)));
		Assert.assertEquals(2, testList.size());
		
		// Test on left edge of board, two walkway pieces
		testList = board.getAdjList(12, 0);
		Assert.assertTrue(testList.contains(board.getCellAt(13, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(12, 1)));
		Assert.assertEquals(2, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(6, 19);
		Assert.assertTrue(testList.contains(board.getCellAt(6, 20)));
		Assert.assertTrue(testList.contains(board.getCellAt(6, 18)));
		Assert.assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(15,13);
		Assert.assertTrue(testList.contains(board.getCellAt(15, 14)));
		Assert.assertTrue(testList.contains(board.getCellAt(15, 12)));
		Assert.assertTrue(testList.contains(board.getCellAt(14, 13)));
		Assert.assertTrue(testList.contains(board.getCellAt(16, 13)));
		Assert.assertEquals(4, testList.size());
		
		// Test on bottom edge of board, next to 1 room piece
		testList = board.getAdjList(23, 13);
		Assert.assertTrue(testList.contains(board.getCellAt(23, 14)));
		Assert.assertTrue(testList.contains(board.getCellAt(22, 13)));
		Assert.assertEquals(2, testList.size());
		
		// Test on right edge of board, next to 1 room piece
		testList = board.getAdjList(15, 20);
		Assert.assertTrue(testList.contains(board.getCellAt(15, 19)));
		Assert.assertTrue(testList.contains(board.getCellAt(14, 20)));
		Assert.assertEquals(2, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(5, 15);
		Assert.assertTrue(testList.contains(board.getCellAt(4, 15)));
		Assert.assertTrue(testList.contains(board.getCellAt(6, 15)));
		Assert.assertTrue(testList.contains(board.getCellAt(5, 14)));
		Assert.assertEquals(3, testList.size());
	}
	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are PINK on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(23, 6, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(23, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(22, 6)));	
		
		board.calcTargets(7, 0, 1);
		targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(7, 1)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 0)));				
	}
	
	// Tests of just walkways, 2 steps
	// These are PINK on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(15, 5, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(13, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(14, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(16, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(14, 4)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 5)));	
		Assert.assertTrue(targets.contains(board.getCellAt(15, 3)));
		Assert.assertTrue(targets.contains(board.getCellAt(15, 7)));

		board.calcTargets(0, 13, 2);
		targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(2, 13)));	
		Assert.assertTrue(targets.contains(board.getCellAt(1, 14)));			
	}
	
	// Tests of just walkways, 4 steps
	// These are PINK on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(15, 6, 4);
		Set<BoardCell>targets= board.getTargets();
		Assert.assertEquals(10, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(19, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(15, 10)));	
		Assert.assertTrue(targets.contains(board.getCellAt(11, 6)));	
		Assert.assertTrue(targets.contains(board.getCellAt(15, 2)));
		Assert.assertTrue(targets.contains(board.getCellAt(14, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(16, 9)));	
		Assert.assertTrue(targets.contains(board.getCellAt(18, 5)));	
		Assert.assertTrue(targets.contains(board.getCellAt(14, 3)));
		Assert.assertTrue(targets.contains(board.getCellAt(13, 4)));
		Assert.assertTrue(targets.contains(board.getCellAt(12, 5)));
		
		board.calcTargets(19, 20, 4);
		targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(19, 16)));	
	}	
	
	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(0, 6, 6);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(6, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 5)));	
	}	
	
	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(7, 15, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(5, targets.size());
		// directly down & up (can't go right 2 steps)
		Assert.assertTrue(targets.contains(board.getCellAt(9, 15)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 15)));
		// one up/down, one left/right
		Assert.assertTrue(targets.contains(board.getCellAt(6, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 14)));
		Assert.assertTrue(targets.contains(board.getCellAt(8, 14)));
	}
	
	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(15, 10, 3);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(12, targets.size());
		// directly down
		Assert.assertTrue(targets.contains(board.getCellAt(17, 10)));
		// directly right and left
		Assert.assertTrue(targets.contains(board.getCellAt(15, 13)));
		Assert.assertTrue(targets.contains(board.getCellAt(15, 7)));
		// right then down
		Assert.assertTrue(targets.contains(board.getCellAt(16, 12)));
		// down then left/right
		Assert.assertTrue(targets.contains(board.getCellAt(16, 8)));
		// right then up
		Assert.assertTrue(targets.contains(board.getCellAt(14, 12)));
		//left then up
		Assert.assertTrue(targets.contains(board.getCellAt(14, 7)));
		// into the rooms
		Assert.assertTrue(targets.contains(board.getCellAt(17, 9)));				
	}

	// Test getting out of a room
	// These are PINK on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(10, 5, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(11, 6)));
		// Take two steps
		board.calcTargets(8, 10, 2);
		targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(9, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(9, 11)));
	}

}