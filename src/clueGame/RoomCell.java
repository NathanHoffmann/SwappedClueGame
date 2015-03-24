package clueGame;
/*
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
*/
import java.awt.*;
import java.util.Arrays;



public class RoomCell extends BoardCell {
	private int x;
	private int y;
	public RoomCell(int row, int column) {
		super(row, column);
		//Board board=new Board();
		x=column;
		y=row;
		
		
		//draw(g, board);
		}
	@Override
	public void draw(Graphics g){	


		if(this.isDoorway())
			
		g.setColor(Color.GREEN);
		else 
		//System.out.println(doorDirection);
		g.setColor(Color.LIGHT_GRAY);

		g.fillRect(cellSize*x, cellSize*y, cellSize, cellSize);
		g.setColor(Color.BLUE);
		int thickness = cellSize/7;
		switch(doorDirection){
		case UP: g.fillRect(cellSize*x, cellSize*y, cellSize, thickness); break;
		case DOWN: g.fillRect(cellSize*x, cellSize*y+cellSize, cellSize, -thickness); break;
		case RIGHT: g.fillRect(cellSize*x+cellSize, cellSize*y, -thickness, cellSize); break;
		case LEFT: g.fillRect(cellSize*x, cellSize*y, thickness, cellSize); break;
			
		default: 	break;
		}
		g.setColor(Color.LIGHT_GRAY);
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
	
	@Override
	public boolean isDoorway(){

		if (doorDirection != null && doorDirection != DoorDirection.NONE){
			return true;

		}
		else 
			return false;
	}
	public boolean isWalkway() {
		return false;
	}

	public Character getInitial() {
		return roomInitial;
	}
	public void setRoomInitial(Character c){
		this.roomInitial = c;
	}
}
