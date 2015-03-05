package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;





import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.prism.paint.Color;


public class GameSetUpTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void loadPeopletest() throws FileNotFoundException , BadConfigFormatException {
		ClueGame game=new ClueGame();
		game.loadConfigFiles();
	
	}
	@Test
	public void loadCardtest() throws FileNotFoundException , BadConfigFormatException {
		ClueGame game=new ClueGame();
		game.loadConfigFiles();
		Assert.assertEquals(21, game.getCards().size());
		
	}

}
