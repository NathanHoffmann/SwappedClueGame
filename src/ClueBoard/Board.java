package ClueBoard;

import java.util.Map;

import ClueLayout.BoardCell;

public class Board {
	private BoardCell[][] layout;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	
	public void loadBoardConfig(){
		
	}
	
	public BoardCell[][] getLayout() {
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
		return null;
	}
	public ClueBoard.BoardCell getCellAt(int row, int col){
		return null;
	}
}
