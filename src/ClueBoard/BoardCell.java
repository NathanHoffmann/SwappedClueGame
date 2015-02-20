package ClueBoard;

public abstract class BoardCell {
	private int x;
	private int y;
	
	public boolean isWalkway(){
		return true;
	}
	public boolean isRoom(){
		return true;
	}
	public boolean isDoorway(){
		return false;
	}
}
