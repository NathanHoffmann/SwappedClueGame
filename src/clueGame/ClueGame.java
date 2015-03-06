package clueGame;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import clueGame.Card.cardType;

public class ClueGame {
	ArrayList<Player> players=new ArrayList<Player>();
	ArrayList<Card> cards=new ArrayList<Card>();
	private Map<Character, String> rooms;
	private int rows;
	private int cols;
	private Board gameBoard;
	private String board;
	private String legend;
	private String playerConfig;
	private Solution solution = new Solution();
	private Card disprovedCard;
	private String disproved;
	
	private String sugRoom;
	private String sugPerson;
	private String sugWeapon;
	public String getDisproved() {
		return disproved;
	}

	public void removeSeen(){
		
	}

	public void setDisproved(String disproved) {
		this.disproved = disproved;
	}

	public String getSugRoom() {
		return sugRoom;
	}


	public String getSugPerson() {
		return sugPerson;
	}


	public String getSugWeapon() {
		return sugWeapon;
	}


	public Solution getSolution() {
		return solution;
	}


	public void suggestion(Player currentPlayer){
		//String roomSuggestion=rooms.get(gameBoard.
		System.out.println(gameBoard.getRoomName(2, 2));
		
	}
	public void loadConfigFiles() throws FileNotFoundException, BadConfigFormatException{
		FileReader reader = new FileReader(board);
		Scanner in = new Scanner(reader);
		int colu = 0;
		int row = 1;
		String line = in.nextLine();
		String [] split = line.split(",");
		colu = split.length;
		while(in.hasNextLine()){
			row++;
			line = in.nextLine();
			String [] split1 = line.split(",");
			int hold = split1.length;
			if ( colu != hold){
				throw new BadConfigFormatException();
			}
		}
		in.close();
		this.cols = colu;
		this.rows = row;
		gameBoard = new Board (rows, cols);
		gameBoard.setBoard(board);
		gameBoard.setLegend(legend);
		gameBoard.loadBoardConfig();
		rooms = gameBoard.getRooms();
		try {
			loadPlayerConfig();
		} catch(Exception e) {
			e.getMessage();
		}
	}


	public void loadPlayerConfig() throws Exception  {
		FileReader PReader = new FileReader(playerConfig);
		Scanner readPlayer = new Scanner(PReader);
		String line;
		String[] split;
		int count = 0;
		Player tempPlayer;
		Card tempCard;
		while(readPlayer.hasNextLine()) {
			tempCard = new Card();
			line = readPlayer.nextLine();
			split = line.split(", ");
			switch(split[0]){
			case "P" :
				if(count == 0) tempPlayer = new HumanPlayer();
				else tempPlayer = new ComputerPlayer();
				tempPlayer.setName(split[1]);
				tempPlayer.setColor(convertColor(split[2]));
				tempPlayer.setCol(Integer.parseInt(split[3]));
				tempPlayer.setRow(Integer.parseInt(split[4]));
				players.add(tempPlayer);
				tempCard.setName(split[1]);
				tempCard.setType(cardType.PERSON);
				cards.add(tempCard);
				break;
			case "W" :
				tempCard.setName(split[1]);
				tempCard.setType(cardType.WEAPON);
				cards.add(tempCard);
				break;				
			default: throw new BadConfigFormatException();
			}
			count++;
		}
		// System.out.println(players.get(0));
		// System.out.println(players.get(1));
		
		
		for(char key:rooms.keySet()) {
			if(key != 'X' && key != 'W') {
				tempCard = new Card();
				tempCard.setType(cardType.ROOM);
				tempCard.setName(rooms.get(key));
			cards.add(tempCard);
			}
		}	
		for(int i=0; i<players.size();i++){
			if(players.get(i).getClass()==ComputerPlayer.class){
				players.get(i);
			}
		}
		ArrayList<String> strCards=new ArrayList<String>();
		ArrayList<String> tempCards;
		for(Card i: cards){
		strCards.add(i.getName());
		}
		for(Player i: players){
			tempCards = new ArrayList<String>(strCards);
			ArrayList<String> seen=new ArrayList<String>();
			for(int j=0; j<i.getCards().size(); j++){
				seen.add(i.getCards().get(j).getName());	
			}
			tempCards.removeAll(seen);
			i.setUnSeen(tempCards);
		}
		readPlayer.close();
	}
	
	public void  makeSuggestion(Player currentPlayer){
		
		
	}
	public Color convertColor(String strColor) {
		Color color;
		try {
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());
			color = (Color) field.get(null);
		} catch (Exception e) {
			color = null;
		}
		return color;
	}
	public void dealCards(){
		int i=0;

		Random rand = new Random();
		ArrayList<String> roomCard = new ArrayList<String>();
		ArrayList<String> weaponCard = new ArrayList<String>();
		ArrayList<String> personCard = new ArrayList<String>();
		
		for(Card c:cards) {
			switch(c.getType()) {
			case PERSON : personCard.add(c.getName());
						break;
			case WEAPON : weaponCard.add(c.getName());
						break;
			case ROOM : roomCard.add(c.getName());
						break;
			}

			
		}
		solution.person = personCard.get(rand.nextInt(personCard.size()));
		solution.room = roomCard.get(rand.nextInt(roomCard.size()));
		solution.weapon = weaponCard.get(rand.nextInt(weaponCard.size()));
		while(i<cards.size()){
			for(int j=0; j<players.size()&&i<cards.size();j++){
				if(!cards.get(i).getName().equals(solution.weapon)&&!cards.get(i).getName().equals(solution.person)&&!cards.get(i).getName().equals(solution.room)){

				players.get(j).getCards().add(cards.get(i));
				}
				else {
					j--;
				}
				i++;
			}
		}
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayer(ArrayList<Player> player) {
		this.players = player;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	public void loadRoomConfig(){
		
	}
	
	public boolean checkAccusation(Solution accusation) {
		boolean checkWeapon = solution.weapon.equals(accusation.weapon);
		boolean checkRoom = solution.room.equals(accusation.room);
		boolean checkPerson = solution.person.equals(accusation.person);
		
		if(checkWeapon && checkRoom && checkPerson) return true;
		return false;
	}
	
	public void handleSuggestion(String person, String weapon, String room, Player accusingPerson) {
		disprovedCard = null;
		
		for(Player i:players) {
			if(!i.equals(accusingPerson)) {
				disprovedCard = i.disproveSuggestion(person, weapon, room);
				if(disprovedCard != null) break;
			}
		}
		if(disprovedCard != null) {
			disproved = disprovedCard.getName();
		} else disproved = null;
	}
	
	public ClueGame() {
		this.board = "ClueLayout.csv";
		this.legend = "Legend.txt";
		this.playerConfig = "playerConfigFile.txt";
	}
	public ClueGame(String string, String string2) throws FileNotFoundException, BadConfigFormatException {
		this.board= string;
		this.legend = string2;
		// Probably needs to be an input like board and legend
		this.playerConfig = "playerConfigFile.txt";
		loadConfigFiles();
	}
	public Board getBoard(){
		return gameBoard;
	}
}