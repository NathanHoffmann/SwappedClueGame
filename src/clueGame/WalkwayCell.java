package clueGame;

import java.awt.Color;

public class WalkwayCell extends BoardCell {

	public WalkwayCell(int row, int column) {
		super(row, column);
		
	}
	
	@Override
	public boolean isWalkway() {
		return true;
	}
	@Override
	public boolean isDoorway() {
		return false;
	}
	


	@Override
	public void draw(java.awt.Graphics g) {
		
		g.setColor(Color.YELLOW);
		g.fillRect(cellSize*x, cellSize*y, cellSize, cellSize);
		g.setColor(Color.BLACK);
		g.drawRect(cellSize*x, cellSize*y, cellSize, cellSize);
		
	}
}
