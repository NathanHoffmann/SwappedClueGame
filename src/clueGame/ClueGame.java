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

	public Solution getSolution() {
		return solution;
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
		}
		
		for(char key:rooms.keySet()) {
			if(key != 'X') {
				tempCard = new Card();
				tempCard.setType(cardType.ROOM);
				tempCard.setName(rooms.get(key));
			cards.add(tempCard);
			}
		}		
		readPlayer.close();
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
		return false;
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