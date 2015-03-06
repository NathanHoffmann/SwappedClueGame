package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import clueGame.RoomCell.DoorDirection;

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
	private HashSet<BoardCell> visited;
	private HashSet<BoardCell> targets;
	
	public Board(int rows, int cols){
		this.numRows = rows;
		this.numColumns = cols;
		layout = new clueGame.BoardCell[rows][cols];
		for ( int i = 0; i<rows; i++){
			for(int j =0; j<cols; j++){
				layout[i][j] =new clueGame.BoardCell(i, j);
			}
		}
	}
	public String getRoomName(int row , int col){
		return rooms.get(getCellAt(row, col).getInitial1().charAt(0));
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
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String [] split = line.split(", ");
			key = split[0].charAt(0);
			if (split.length > 2) {
				throw new BadConfigFormatException();
			}
			roomName = split[1];
			rooms.put(key,roomName);
		}
		FileReader reader1 = new FileReader(board);
		Scanner in1 = new Scanner(reader1);
		int j = 0;
		while(in1.hasNextLine()) {
			String hold = in1.nextLine();
			String[] split2 = hold.split(",");
			for(int i = 0; i< numColumns; i++) {
				layout[j][i].setInitial(split2[i]);
			}
			j++;
		}
		for (int k = 0; k < numRows; k++) {
			for (int n =0; n < numColumns; n++) {
				BoardCell hold = layout[k][n];
				if ( hold.isWalkway()) {
					int holdrow = layout[k][n].getRow();
					int holdcol = layout[k][n].getColumn();
					WalkwayCell hold1 = new WalkwayCell(holdrow, holdcol);
					layoutWalkway[k][n] = new WalkwayCell(holdrow, holdcol);
				}
				else if (hold.isRoom()) {
					int holdrow = layout[k][n].getRow();
					int holdcol = layout[k][n].getColumn();
					RoomCell hold1 = new RoomCell(holdrow, holdcol);
					String holdInit = layout[k][n].getInitial1();
					Character holdChar = holdInit.charAt(0);
					if(!rooms.containsKey(holdChar)) {
						throw new BadConfigFormatException();
					}
					hold1.setRoomInitial(holdChar);
					hold1.setDoorDirection('N');
					layoutRoom[k][n] = hold1;
					
				}
				else if(hold.isDoorway()) {
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
		calcAdjacencies();
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
	//changed clueGame.BoardCell to BoardCell
	public BoardCell getCellAt(int row, int col){
		return layout[row][col];
	}

	public void calcAdjacencies() {
		adjacencies = new HashMap<BoardCell, LinkedList<BoardCell>>();
		
		for( int i = 0; i<numRows; i++) {
			
			for (int j = 0; j<numColumns; j++) {
				LinkedList<BoardCell> hold = new LinkedList<BoardCell>();
				if( layout[i][j].isDoorway()){
					int x = layout[i][j].getRow();
					int y = layout[i][j].getColumn();
					RoomCell.DoorDirection dd = layoutRoom[i][j].getDoorDirection();
					if( dd == DoorDirection.RIGHT){
						hold.add(layout[x][y+1]);
					}
					else if( dd == DoorDirection.LEFT) {
						hold.add(layout[x][y-1]);
					}
					else if( dd == DoorDirection.DOWN) {
						hold.add(layout[x+1][y]);
					}
					else if( dd == DoorDirection.UP) {
						hold.add(layout[x-1][y]);
					}
					adjacencies.put(layout[i][j], hold);
				}
				else if (layout[i][j].isWalkway()) {
					
					int x = layout[i][j].getRow();
					int y = layout[i][j].getColumn();
					if( x != 0){
						BoardCell Left = layout[x-1][y];
						if(layout[x-1][y].isDoorway()&& layoutRoom[x-1][y].getDoorDirection() == RoomCell.DoorDirection.DOWN) {
							hold.add(Left);
						}
						else if ( layout[x-1][y].isWalkway()) {
							hold.add(Left);
						}
					}
					if( y != 0){
						BoardCell Up = layout[x][y-1];
						if(layout[x][y-1].isDoorway()&& layoutRoom[x][y-1].getDoorDirection() == RoomCell.DoorDirection.RIGHT) {
							hold.add(Up);
						}
						else if ( layout[x][y-1].isWalkway()) {
							hold.add(Up);
						}		
					}
					if( x != numRows - 1) {
						BoardCell Right = layout[x+1][y];
						if(layout[x+1][y].isDoorway()&&layoutRoom[x+1][y].getDoorDirection() == RoomCell.DoorDirection.UP) {
							hold.add(Right);
						}
						else if ( layout[x+1][y].isWalkway()) {
							hold.add(Right);
						}
					}
					if( y != numColumns - 1) {
						BoardCell Down = layout[x][y+1];
						if(layout[x][y+1].isDoorway()&&layoutRoom[x][y+1].getDoorDirection() == RoomCell.DoorDirection.LEFT) {
							hold.add(Down);
						}
						else if ( layout[x][y+1].isWalkway()) {
							hold.add(Down);
						}
					}
					adjacencies.put(layout[i][j], hold);
				}
			}
		}		
	}

	public LinkedList<clueGame.BoardCell> getAdjList(int i, int j) {
		LinkedList<BoardCell> holdAdj = adjacencies.get(layout[i][j]);
		if (holdAdj == null) {
			LinkedList<BoardCell>newAdj = new LinkedList<BoardCell>();
			return newAdj;
		}
		else
			return adjacencies.get(layout[i][j]);		
	}

	public void calcTargets(int i, int j, int k) {
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		visited.add(layout[i][j]);
		calcAllTargets(i, j, k);		
	}
	public void calcAllTargets(int i, int j, int k){
		LinkedList<BoardCell> hold = new LinkedList<BoardCell>(adjacencies.get(layout[i][j]));
		hold.removeAll(visited);
		int size = hold.size();
		for(int p = 0; p< size; p++){
			BoardCell cell = hold.get(p);
			int x = cell.getRow();
			int y = cell.getColumn();
			visited.add(layout[x][y]);
			if (layout[x][y].isDoorway()) {
				targets.add(layout[x][y]);
			}
			else if(k==1) {
					targets.add(layout[x][y]);
					visited.remove(layout[x][y]);
			}
			else {
				calcAllTargets(x, y, k-1);
				visited.remove(layout[x][y]);	
			}
		}
		visited.remove(layout[i][j]);
	}
	
	public Set<clueGame.BoardCell> getTargets() {
		return targets;
	}
	
}
