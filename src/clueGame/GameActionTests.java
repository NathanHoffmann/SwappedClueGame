package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameActionTests {
	static ClueGame game;
	
	@BeforeClass
	public static void setup() throws FileNotFoundException, BadConfigFormatException {
		game = new ClueGame();
		game.loadConfigFiles();
	}
	
	
	@Test
	public void testAccusation() {
		game.dealCards();
		Solution solution = game.getSolution();
		Solution accusation = new Solution();
		accusation.person = solution.person;
		accusation.room = solution.room;
		accusation.weapon = solution.weapon;
		
		// Check when they are all right
		assertTrue(game.checkAccusation(accusation));
		// Check when just the person is wrong
		accusation.person = "Different person";
		assertFalse(game.checkAccusation(accusation));
		// Check when just the weapon is wrong
		accusation.person = solution.person;
		accusation.weapon = "Different Weapon";
		assertFalse(game.checkAccusation(accusation));
		// Check when just the room is wrong
		accusation.weapon = solution.weapon;
		accusation.room = "Different Room";
		assertFalse(game.checkAccusation(accusation));
		// Check when all three are wrong
		accusation.weapon = "Different Weapon";
		accusation.person = "Different Person";
		assertFalse(game.checkAccusation(accusation));
	}

	@Test
	public void testSelectingTargets() {
		// a set of targets that include a room
		game.getBoard().calcTargets(6, 1, 3);
		
		Set<BoardCell> targets=game.getBoard().getTargets();
		ComputerPlayer cplayer= (ComputerPlayer) game.getPlayers().get(3);
		
		cplayer.pickLocation(targets);
	
		assertEquals(4, cplayer.getRow());
		assertEquals(2, cplayer.getCol());
		//a test that considers the last visited room
		game.getBoard().calcTargets(4, 2, 2);
		targets=game.getBoard().getTargets();
		cplayer.pickLocation(targets);
		assertNotEquals(game.getBoard().getCellAt(4, 2), game.getBoard().getCellAt(cplayer.getRow(), cplayer.getCol()));
		//assertNotEquals(4, cplayer.getRow());
		//assertNotEquals(2, cplayer.getCol());
		//random selection
		game.getBoard().calcTargets(15, 6, 2);
		targets=game.getBoard().getTargets();
		int a=0, b=0, c=0, d=0;
		for(int i=0; i<100 ; i++){
			cplayer.pickLocation(targets);
			if(game.getBoard().getCellAt(cplayer.getRow(),cplayer.getCol())==game.getBoard().getCellAt(13, 6)){
				a++;
			}
			else if(game.getBoard().getCellAt(cplayer.getRow(),cplayer.getCol())==game.getBoard().getCellAt(14, 7)){
				b++;
			}
			else if(game.getBoard().getCellAt(cplayer.getRow(),cplayer.getCol())==game.getBoard().getCellAt(16, 5)){
				c++;
			}
			else if(game.getBoard().getCellAt(cplayer.getRow(),cplayer.getCol())==game.getBoard().getCellAt(15, 4)){
				d++;
			}
		}
		System.out.println(a+" "+b+" "+c+ " "+d);
		assertTrue(a>5);
		assertTrue(b>5);
		assertTrue(c>5);
		assertTrue(d>5);		
	}
}
