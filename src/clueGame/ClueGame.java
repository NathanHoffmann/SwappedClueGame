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
	private String playerConfig;
	
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
		loadPlayerConfig();
		
		
	}
	public void loadPlayerConfig() throws FileNotFoundException  {
		FileReader PReader = new FileReader(playerConfig);
		Scanner readPlayer = new Scanner(PReader);
		String line = readPlayer.nextLine();
		String[] split = line.split(",");
		//while(readPlayer.hasNextLine()) {
			
		//}
		
		
		
	}
	public ClueGame() {
		this.board = "ClueLayout.csv";
		this.legend = "ClueLegend.txt";
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