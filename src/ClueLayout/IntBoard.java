package ClueLayout;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	BoardCell[][] grid;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private Map<BoardCell, LinkedList<BoardCell>> adjacencies;
	private int ROWS = 4;
	private int COLUMNS = 4;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((adjacencies == null) ? 0 : adjacencies.hashCode());
		result = prime * result + ((targets == null) ? 0 : targets.hashCode());
		result = prime * result + ((visited == null) ? 0 : visited.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntBoard other = (IntBoard) obj;
		if (adjacencies == null) {
			if (other.adjacencies != null)
				return false;
		} else if (!adjacencies.equals(other.adjacencies))
			return false;
		if (targets == null) {
			if (other.targets != null)
				return false;
		} else if (!targets.equals(other.targets))
			return false;
		if (visited == null) {
			if (other.visited != null)
				return false;
		} else if (!visited.equals(other.visited))
			return false;
		return true;
	}
	public IntBoard() {
		grid = new BoardCell[ROWS][COLUMNS];
		for ( int i = 0; i<ROWS; i++){
			for(int j =0; j<COLUMNS; j++){
				grid[i][j] =new BoardCell(i, j);
			}
		}
		adjacencies = new HashMap<BoardCell, LinkedList<BoardCell> >();
	}

	public void calcAdjacencies(BoardCell cell){
		
		LinkedList<BoardCell> hold = new LinkedList<BoardCell>();
		int x = cell.getColumn();
		int y = cell.getRow();
		if( x != 0){
			BoardCell Left = grid[x-1][y];
			hold.add(Left);
		}
		if( y != 0){
			BoardCell Up = grid[x][y-1];
			hold.add(Up);
		}
		if( x != 3){
			BoardCell Right = grid[x+1][y];
			hold.add(Right);
		}
		if( y != 3){
			BoardCell Down = grid[x][y+1];
			hold.add(Down);
		}
		adjacencies.put(cell, hold);
		
	}
	public void calcTargets(BoardCell cell, int k){
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		calcAllTargets(cell, k);

	}
	public void calcAllTargets(BoardCell cell, int k){
		LinkedList<BoardCell> hold = getAdjList(cell);
		visited.add(cell);

		if(k==1){
			int size = hold.size();
			for (int m = 0; m < size; m++){
				targets.add(hold.get(m));
			}
			
		}
		else{
			for(int i = 0; i< k ; i++){
				int size = hold.size();
				for(int j = 0; j< size; j++){
					BoardCell cell2= hold.get(j);
					calcAllTargets(cell2, k-1);
				}
			}
		}
		
	}
	public Set<BoardCell> getTargets(){
		targets.removeAll(visited);
		System.out.println(targets.toString());

		return targets;
	}
	public LinkedList<BoardCell> getAdjList(BoardCell cell){
		calcAdjacencies(cell);
		LinkedList<BoardCell> board = adjacencies.get(cell);
		return board;
	}
	public BoardCell getCell(int x, int y){
		BoardCell cell = grid[x][y];
		return cell;
		
	}
}
