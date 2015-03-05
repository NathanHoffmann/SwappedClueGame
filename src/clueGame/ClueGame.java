package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClueGame {
	ArrayList<Player> player=new ArrayList<Player>();
	ArrayList<Card> cards=new ArrayList<Card>();
	private Map<Character, String> rooms;
	private int rows;
	private int cols;
	private Board gameBoard;
	private String board;
	private String legend;
	
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
		this.cols = colu;
		this.rows = row;
		//System.out.println("rows:"+rows+"cols: "+cols);
		gameBoard = new Board (rows, cols);
		gameBoard.setBoard(board);
		gameBoard.setLegend(legend);
		gameBoard.loadBoardConfig();
	}
	public ArrayList<Player> getPlayer() {
		return player;
	}
	public void setPlayer(ArrayList<Player> player) {
		this.player = player;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	public void loadRoomConfig(){
		
	}
	public ClueGame() {
		this.board = "ClueLayout.csv";
		this.legend = "ClueLegend.txt";
	}
	public ClueGame(String string, String string2) throws FileNotFoundException, BadConfigFormatException {
		this.board= string;
		this.legend = string2;
		loadConfigFiles();
	}
	public Board getBoard(){
		return gameBoard;
	}
}