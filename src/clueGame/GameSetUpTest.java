package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;






import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.Card.cardType;

import com.sun.prism.paint.Color;


public class GameSetUpTest {

	ClueGame game;
	@Before
	public void setUp() throws Exception {
	    game=new ClueGame();
		//game.loadConfigFiles();
	}

	
	@Test
	public void loadCardtest() throws FileNotFoundException , BadConfigFormatException {
		//test if The deck contains the correct total number of cards
		//random number for the cards size	
		Assert.assertEquals(23, game.getCards().size());	
		//test if The deck contains the correct number of cards of each type
		int weapon=0,person=0, rooms=0;
		for (int i=0; i < game.getCards().size();i++){
			if(game.getCards().get(i).getType()==cardType.WEAPON)
				weapon++;
			else if(game.getCards().get(i).getType()==cardType.PERSON)
				person++;
			if(game.getCards().get(i).getType()==cardType.ROOM)
				rooms++;
		}
		Assert.assertEquals(8, weapon);
		Assert.assertEquals(6, person);
		Assert.assertEquals(9, rooms);
		// select one room, one weapon, and one person, and ensure the deck contains each of those
		Set<String>cardName=new HashSet<String>();
		for(int i=0 ; i<game.getCards().size();i++){
		cardName.add(game.getCards().get(i).getName());
		}
		Assert.assertTrue(cardName.contains("Ms. Scarlet"));
		Assert.assertTrue(cardName.contains("TNT"));
		Assert.assertTrue(cardName.contains("Library"));
	}

}
