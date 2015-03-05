package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.prism.paint.Color;



public class GameSetUpTest {
	ClueGame clueGame;

	@Before
	public void setUp() throws Exception {
		clueGame = new ClueGame();
		clueGame.loadConfigFiles();
	}

	@Test
	public void loadPeopletest() throws FileNotFoundException , BadConfigFormatException {
		ClueGame game=new ClueGame();
		game.loadConfigFiles();
		
	}
	
	@Test
	public void playerConfigTest() {
		// Won't be 8
		assertEquals(8,clueGame.getCards().size());
		ArrayList<Player> players = clueGame.getPlayer();		
		
		// Might need changing (as Color instead of string)
		// Random answers atm just to get syntax right
		assertEquals("red",players.get(0).getColor());
		assertEquals("blue",players.get(2).getColor());
		assertEquals("purple",players.get(5).getColor());
		assertEquals("asdga",players.get(0).getName());
		assertEquals("asdga",players.get(1).getName());
		assertEquals("asdga",players.get(5).getName());
		assertEquals(5,players.get(4).getCol());
		assertEquals(5,players.get(5).getRow());
		assertEquals(5,players.get(2).getCol());
		assertEquals(5,players.get(2).getRow());	
	}

}
