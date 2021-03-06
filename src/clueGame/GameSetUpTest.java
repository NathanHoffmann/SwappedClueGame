package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.Card.cardType;



public class GameSetUpTest {
	ClueGame game;
	@Before
	public void setUp() throws Exception {
	    game=new ClueGame();
		game.loadConfigFiles();
	}
	
	@Test
	public void loadCardtest() throws FileNotFoundException , BadConfigFormatException {
		game=new ClueGame();
		game.loadConfigFiles();
		assertEquals(23, game.getCards().size());
		int weapon=0, person=0, rooms=0;
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
	
	
	
	@Test
	public void playerConfigTest() {
		// Won't be 8
		assertEquals(23,game.getCards().size());
		ArrayList<Player> players = game.getPlayers();		
		
		// Might need changing (as Color instead of string)
		// Random answers atm just to get syntax right
		assertEquals(java.awt.Color.red,players.get(0).getColor());
		assertEquals(java.awt.Color.WHITE,players.get(2).getColor());
		java.awt.Color purple = new java.awt.Color(255, 0, 255);
		assertEquals(purple,players.get(5).getColor());
		assertEquals("Ms. Scarlet",players.get(0).getName());
		assertEquals("Col. Mustard",players.get(1).getName());
		assertEquals("Prof. Plum",players.get(5).getName());
		assertEquals(15,players.get(4).getCol());
		assertEquals(6,players.get(5).getRow());
		assertEquals(0,players.get(2).getCol());
		assertEquals(15,players.get(2).getRow());	
	}

	@Test
	public void testDealCards(){
		//test all cards are dealt.
		//also test one card is not given to two different players.
		Set<Card> dealtCards= new HashSet<Card>();
		for(int i=0; i<game.getPlayers().size();i++){
			dealtCards.addAll(game.getPlayers().get(i).getCards());
		}

		Assert.assertEquals(20, dealtCards.size());
		Set<String> solutionCards=new HashSet<String>();

		solutionCards.add(game.getSolution().person);
		solutionCards.add(game.getSolution().weapon);
		solutionCards.add(game.getSolution().room);

		Assert.assertEquals(23, dealtCards.size()+solutionCards.size());

		//all players have roughly the same number of cards.
		for(int i=0; i<game.getPlayers().size();i++){
			for(int j=i; j<game.getPlayers().size();j++){
				assertTrue(Math.abs(game.getPlayers().get(i).getCards().size()-game.getPlayers().get(j).getCards().size())<2);				
			}
		}		
		
	}
}
