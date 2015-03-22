package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;



public class RoomCell extends BoardCell {
	private int x;
	private int y;
	public RoomCell(int column, int row) {
		super(column, row);
		//Board board=new Board();
		x=column;
		y=row;
		
		//draw(g, board);
		}
	@Override
	public void draw(Graphics g){	
		System.out.println("this is a test");
		g.setColor(Color.BLUE);
		g.fillRect(cellSize*x, cellSize*y, cellSize, cellSize);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(cell);
		result = prime * result
				+ ((doorDirection == null) ? 0 : doorDirection.hashCode());
		result = prime * result
				+ ((roomInitial == null) ? 0 : roomInitial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomCell other = (RoomCell) obj;
		if (!Arrays.deepEquals(cell, other.cell))
			return false;
		if (doorDirection != other.doorDirection)
			return false;
		if (roomInitial == null) {
			if (other.roomInitial != null)
				return false;
		} else if (!roomInitial.equals(other.roomInitial))
			return false;
		return true;
	}

	private DoorDirection doorDirection;
	private BoardCell[][] cell;
	private Character roomInitial;
	
	public enum DoorDirection {LEFT, RIGHT, UP, DOWN, NONE};

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	public void setDoorDirection(Character c){
		if ( c == 'L'){
			doorDirection = DoorDirection.LEFT;
		}
		if (c=='R'){
			doorDirection = DoorDirection.RIGHT;
		}
		if (c=='U'){
			doorDirection = DoorDirection.UP;
		}
		if (c=='D'){
			doorDirection = DoorDirection.DOWN;
		}
		if (c=='N'){
			doorDirection = DoorDirection.NONE;
		}
	}
	public boolean isRoom(){
			return true;
	}
	public boolean isDoorway(){
		if (doorDirection != DoorDirection.NONE){
			return true;
		}
		else 
			return false;
	}

	public Character getInitial() {
		return roomInitial;
	}
	public void setRoomInitial(Character c){
		this.roomInitial = c;
	}
}
