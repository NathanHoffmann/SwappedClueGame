package clueGame;

import java.awt.Color;
import java.awt.Graphics;



public class BoardCell {
	private int x;
	private int y;
	private String initial1;
	private BoardCell[][] layout;
	protected final int cellSize=30;

	
	public void setInitial(String initial) {
		initial1= initial;
	}
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect(cellSize*x, cellSize*y, cellSize, cellSize);
		g.setColor(Color.BLACK);
		g.drawRect(cellSize*x, cellSize*y, cellSize, cellSize);
		
	}
	public boolean isWalkway(){
		if (initial1.charAt(0) == 'W'){
			return true;
		}
		else 
			return false;
	}
	public boolean isRoom(){
		if(initial1.length()== 1 && initial1.charAt(0) != 'W'){
			return true;
		}
		else
			return false;
	}
	public boolean isDoorway(){
		if (initial1.length() == 2 && initial1.charAt(1)!= 'N'){
			return true;
		}
		else 
			return false;
	}
	public BoardCell(int row, int column) {
		super();
		this.x = column;
		this.y = row;
		initial1 = "";
	}

	public int getRow() {
		return y;
	}
	public String getInitial1(){
		return initial1;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		BoardCell other = (BoardCell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	public void setRow(int row) {
		this.y = row;
	}
	public int getColumn() {
		return x;
	}
	public void setColumn(int column) {
		this.x = column;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + y + ", column=" + x + "]";
	}

}
