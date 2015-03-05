package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;






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
		game.loadConfigFiles();
	}

	
	@Test
	public void loadCardtest() throws FileNotFoundException , BadConfigFormatException {
		//test if The deck contains the correct total number of cards
		//random number for the cards size
		Assert.assertEquals(23, game.getCards().size());
		
		//test if The deck contains the correct number of cards of each type
		int weapon=0;
		int person=0;
		int rooms=0;
		for (int i=0; i < game.getCards().size();i++){
			if(game.getCards().get(i).getType()==cardType.WEAPON){
				weapon++;
			}
			else if(game.getCards().get(i).getType()==cardType.PERSON){
				person++;
			}
			if(game.getCards().get(i).getType()==cardType.ROOM){
				rooms++;
			}
		}
		Assert.assertEquals(8, weapon);
		Assert.assertEquals(6, person);
		Assert.assertEquals(9, rooms);
		// select one room, one weapon, and one person, and ensure the deck contains each of those
		Card card=new Card();
		card.setName("Miss Scarlet");
		card.setType(cardType.PERSON);
		Assert.assertTrue(game.getCards().contains(card));
		card.setName("TNT");
		card.setType(cardType.WEAPON);
		Assert.assertTrue(game.getCards().contains(card));
		card.setName("Library");
		card.setType(cardType.ROOM);
		Assert.assertTrue(game.getCards().contains(card));
		
	}

}
