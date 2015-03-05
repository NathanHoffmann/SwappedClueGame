package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;






import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.Card.cardType;

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
		for (int i=0; i < game.getCards().size();i++){
			if(game.getCards().get(i).getType()==cardType.WEAPON){
				
			}
		}
		
		
	}

}
