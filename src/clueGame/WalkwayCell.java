package clueGame;

import java.awt.Color;

import com.sun.prism.Graphics;

public class WalkwayCell extends BoardCell {

	public WalkwayCell(int row, int column) {
		super(row, column);
		// TODO Auto-generated constructor stub
	}

	


	@Override
	public void draw(java.awt.Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(cellSize*x, cellSize*y, cellSize, cellSize);
		g.setColor(Color.BLACK);
		g.drawRect(cellSize*x, cellSize*y, cellSize, cellSize);
		
	}
}
