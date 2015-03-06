package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Card.cardType;

public class GameActionTests {
	static ClueGame game;
	static Card scarletCard, greenCard, pistolCard, knifeCard, libraryCard, studyCard;
	
	@BeforeClass
	public static void setup() throws FileNotFoundException, BadConfigFormatException {
		game = new ClueGame();
		game.loadConfigFiles();
		
		scarletCard = new Card("Ms. Scarlet", Card.cardType.PERSON);
		greenCard = new Card("Rev. Green",Card.cardType.PERSON);
		pistolCard = new Card("Pistol", Card.cardType.WEAPON);
		knifeCard = new Card("Knife", Card.cardType.WEAPON);
		libraryCard = new Card("Library", Card.cardType.ROOM);
		studyCard = new Card("Study", Card.cardType.ROOM);
		
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
		assertTrue(a>5);
		assertTrue(b>5);
		assertTrue(c>5);
		assertTrue(d>5);		
	}
	
	@Test
	public void disproveTest() {
		ComputerPlayer player = new ComputerPlayer();
		player.cards.add(scarletCard);
		player.cards.add(greenCard);
		player.cards.add(pistolCard);
		player.cards.add(knifeCard);
		player.cards.add(libraryCard);
		player.cards.add(studyCard);
		// Test return of person
		assertTrue(scarletCard.equals(player.disproveSuggestion("Ms. Scarlet", "Poison", "Lounge")));
		// Test return of weapon
		assertTrue(knifeCard.equals(player.disproveSuggestion("Prof. Plum", "Knife", "Lounge")));
		// Test return of room
		assertTrue(libraryCard.equals(player.disproveSuggestion("Prof. Plum", "Poison", "Library")));
		// Test random of return
		int testPerson = 0;
		int testWeapon = 0;
		int testRoom = 0;
		
		for(int i = 0; i < 100; i++) {
			if(player.disproveSuggestion("Ms. Scarlet", "Knife", "Library") == scarletCard) testPerson++;
			if(player.disproveSuggestion("Ms. Scarlet", "Knife", "Library") == knifeCard) testWeapon++;
			if(player.disproveSuggestion("Ms. Scarlet", "Knife", "Library") == libraryCard) testRoom++;
		}
		assertTrue(testPerson > 10);
		assertTrue(testWeapon > 10);
		assertTrue(testRoom > 10);	
	}
	@Test
	public void multPlayerDisproveTest() {
		// Set up the specific cards
		Card scarletCard = new Card("Ms. Scarlet",cardType.PERSON);
		Card greenCard = new Card("Rev. Green",cardType.PERSON);
		Card whiteCard = new Card("Mrs. White",cardType.PERSON);
		Card mustardCard = new Card("Col. Mustard",cardType.PERSON);
		Card peacockCard = new Card("Mrs. Peacock",cardType.PERSON);
		Card plumCard = new Card("Prof. Plum",cardType.PERSON);
		Card PistolCard = new Card("Pistol",cardType.WEAPON);
		Card poisonCard = new Card("Poison",cardType.WEAPON);
		Card ropeCard = new Card("Rope",cardType.WEAPON);
		Card axeCard = new Card("Axe",cardType.WEAPON);
		Card knifeCard = new Card("Knife",cardType.WEAPON);
		Card anvilCard = new Card("Anvil",cardType.WEAPON);
		Card kitchenCard = new Card("Kitchen",cardType.ROOM);
		Card libraryCard = new Card("Library",cardType.ROOM);
		Card loungeCard = new Card("Lounge",cardType.ROOM);
		Card hallCard = new Card("Hall",cardType.ROOM);
		Card diningCard = new Card("Dining Room",cardType.ROOM);
		Card ballCard = new Card("Ballroom",cardType.ROOM);
		// Set up players to be in specific order
		//HumanPlayer p1 = new HumanPlayer();
		//ComputerPlayer p2 = new ComputerPlayer();
		//ComputerPlayer p3 = new ComputerPlayer();
		//ComputerPlayer p4 = new ComputerPlayer();
		//ComputerPlayer p5 = new ComputerPlayer();
		//ComputerPlayer p6 = new ComputerPlayer();
		//ArrayList<Player> players = new ArrayList<Player>();
		game.players.get(0).cards.clear();
		game.players.get(1).cards.clear();
		game.players.get(2).cards.clear();
		game.players.get(3).cards.clear();
		game.players.get(4).cards.clear();
		game.players.get(5).cards.clear();
		game.players.get(0).cards.add(scarletCard);
		game.players.get(0).cards.add(PistolCard);
		game.players.get(0).cards.add(kitchenCard);
		game.players.get(1).cards.add(greenCard);
		game.players.get(1).cards.add(poisonCard);
		game.players.get(1).cards.add(libraryCard);
		game.players.get(2).cards.add(whiteCard);
		game.players.get(2).cards.add(ropeCard);
		game.players.get(2).cards.add(loungeCard);
		game.players.get(3).cards.add(mustardCard);
		game.players.get(3).cards.add(axeCard);
		game.players.get(3).cards.add(hallCard);
		game.players.get(4).cards.add(peacockCard);
		game.players.get(4).cards.add(knifeCard);
		game.players.get(4).cards.add(diningCard);
		game.players.get(5).cards.add(plumCard);
		game.players.get(5).cards.add(anvilCard);
		game.players.get(5).cards.add(ballCard);
		// Actual tests
		// Test where no one has the cards
		game.handleSuggestion("Other Person", "TNT", "Billiard Room", game.players.get(0));
		assertEquals(null,game.getDisproved());
		// Test where the human has the card
		game.handleSuggestion("Ms. Scarlet", "TNT", "Billiard Room", game.players.get(1));
		assertEquals("Ms. Scarlet",game.getDisproved());
		// Test where the current person can disprove (computer)
		game.handleSuggestion("Rev. Green", "TNT", "Billiard Room", game.players.get(1));
		assertEquals(null,game.getDisproved());
		// Test where the current person can disprove (human)
		game.handleSuggestion("Ms. Scarlet", "TNT", "Billiard Room", game.players.get(0));
		assertEquals(null,game.getDisproved());
		// Test where two players can disprove
		game.handleSuggestion("Col. Mustard", "Anvil", "Billiard Room", game.players.get(1));
		assertEquals("Col. Mustard",game.getDisproved());
		// Test where the last person can disprove
		game.handleSuggestion("Other Person", "TNT", "Ballroom", game.players.get(3));
		assertEquals("Ballroom",game.getDisproved());
		
		
		
		
	}
	
}
