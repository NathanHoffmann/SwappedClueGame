package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

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

}
