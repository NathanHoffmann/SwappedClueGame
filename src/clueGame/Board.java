package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//import ClueLayout.BoardCell;

public class Board {
	private clueGame.BoardCell[][] layout;
	private RoomCell[][] layoutRoom;
	private WalkwayCell[][] layoutWalkway;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	private String legend;
	private String board;
	private Map< BoardCell, LinkedList<BoardCell>> adjacencies;
	
	public Board(int rows, int cols){
		this.numRows = rows;
		this.numColumns = cols;
		layout = new clueGame.BoardCell[rows][cols];
		for ( int i = 0; i<rows; i++){
			for(int j =0; j<cols; j++){
				layout[i][j] =new clueGame.BoardCell(i, j);
			}
		} ;
	}
	
	public void setLegend(String legend) {
		this.legend = legend;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException{
		layoutRoom = new RoomCell[numRows][numColumns];
		layoutWalkway = new WalkwayCell[numRows][numColumns];
		rooms = new HashMap<Character, String>();
		Character key;
		String roomName;
		FileReader reader = new FileReader(legend);
		Scanner in = new Scanner(reader);
		while(in.hasNextLine()){
			String line = in.nextLine();
			String [] split = line.split(", ");
			key = split[0].charAt(0);
			if (split.length > 2){
				throw new BadConfigFormatException();
			}
			roomName = split[1];
			rooms.put(key,roomName);
		}
		FileReader reader1 = new FileReader(board);
		Scanner in1 = new Scanner(reader1);
		int j = 0;
		while(in1.hasNextLine()){
			String hold = in1.nextLine();
			String[] split2 = hold.split(",");
			for(int i = 0; i< numColumns; i++){
				layout[j][i].setInitial(split2[i]);
			}
			j++;
		}
		for (int k = 0; k < numRows; k++){
			for (int n =0; n < numColumns; n++){
				BoardCell hold = layout[k][n];
				if ( hold.isWalkway()){
					int holdrow = layout[k][n].getRow();
					int holdcol = layout[k][n].getColumn();
					WalkwayCell hold1 = new WalkwayCell(holdrow, holdcol);
					layoutWalkway[k][n] = new WalkwayCell(holdrow, holdcol);
				}
				else if (hold.isRoom()){
					int holdrow = layout[k][n].getRow();
					int holdcol = layout[k][n].getColumn();
					RoomCell hold1 = new RoomCell(holdrow, holdcol);
					String holdInit = layout[k][n].getInitial1();
					Character holdChar = holdInit.charAt(0);
					if(!rooms.containsKey(holdChar)){
						throw new BadConfigFormatException();
					}
					hold1.setRoomInitial(holdChar);
					hold1.setDoorDirection('N');
					layoutRoom[k][n] = hold1;
					
				}
				else if(hold.isDoorway()){
					int holdrow = layout[k][n].getRow();
					int holdcol = layout[k][n].getColumn();
					RoomCell hold1 = new RoomCell(holdrow, holdcol);
					String holdInit = layout[k][n].getInitial1();
					Character holdChar = holdInit.charAt(0);
					Character holdDoor = holdInit.charAt(1);
					hold1.setRoomInitial(holdChar);
					hold1.setDoorDirection(holdDoor);
					layoutRoom[k][n] = hold1;
				}
					
			}
		}
		
	}
	
	@Override
	public String toString() {
		return "Board [layout=" + Arrays.toString(layout) + "]";
	}

	public clueGame.BoardCell[][] getLayout() {
		return layout;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	public RoomCell getRoomCellAt(int row, int col){
		return layoutRoom[row][col];
	}
	public clueGame.BoardCell getCellAt(int row, int col){
		return layout[row][col];
	}

	public void calcAdjacencies() {
		adjacencies = new HashMap<BoardCell, LinkedList<BoardCell>>();
		for( int i = 0; i<numRows; i++){
			for (int j = 0; j<numColumns; j++){
				LinkedList<BoardCell> hold = new LinkedList<BoardCell>();
				//BoardCell cell = layout[i][j];
				if( layout[i][j].isDoorway()){
					int x = layout[i][j].getRow();
					int y = layout[i][j].getColumn();
					if( x != 0){
						BoardCell Left = layout[x-1][y];
						if(layout[x-1][y].isDoorway()){
							hold.add(Left);
						}
						else if (layout[x-1][y].isWalkway()){
							hold.add(Left);
						}
					}
					if( y != 0){
						BoardCell Up = layout[x][y-1];
						if(layout[x][y-1].isDoorway()){
							hold.add(Up);
						}
						else if ( layout[x][y-1].isWalkway()){
							hold.add(Up);
						}					}
					if( x != numRows - 1){
						BoardCell Right = layout[x+1][y];
						if(layout[x+1][y].isDoorway()){
							hold.add(Right);
						}
						else if ( layout[x+1][y].isWalkway()){
							hold.add(Right);
						}					}
					if( y != numColumns - 1){
						BoardCell Down = layout[x][y+1];
						if(layout[x][y+1].isDoorway()){
							hold.add(Down);
						}
						else if ( layout[x][y+1].isWalkway()){
							hold.add(Down);
						}					}
					adjacencies.put(layout[i][j], hold);
				}
				else if (layout[i][j].isWalkway() ){
					int x = layout[i][j].getRow();
					int y = layout[i][j].getColumn();
					if( x != 0){
						BoardCell Left = layout[x-1][y];
						if(layout[x-1][y].isDoorway()){
							hold.add(Left);
						}
						else if ( layout[x-1][y].isWalkway()){
							hold.add(Left);
						}
					}
					if( y != 0){
						BoardCell Up = layout[x][y-1];
						if(layout[x][-y].isDoorway()){
							hold.add(Up);
						}
						else if ( layout[x][y-1].isWalkway()){
							hold.add(Up);
						}					}
					if( x != numRows - 1){
						BoardCell Right = layout[x+1][y];
						if(layout[x+1][y].isDoorway()){
							hold.add(Right);
						}
						else if ( layout[x+1][y].isWalkway()){
							hold.add(Right);
						}					}
					if( y != numColumns - 1){
						BoardCell Down = layout[x][y+1];
						if(layout[x][y+1].isDoorway()){
							hold.add(Down);
						}
						else if ( layout[x][y+1].isWalkway()){
							hold.add(Down);
						}					}
					adjacencies.put(layout[i][j], hold);
				}
					
			}
			
		}
		
	}

	public LinkedList<clueGame.BoardCell> getAdjList(int i, int j) {
		return adjacencies.get(layout[i][j]);
	}

	public void calcTargets(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	public Set<clueGame.BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
