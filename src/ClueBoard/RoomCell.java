package ClueBoard;

public class RoomCell extends BoardCell {
	private DoorDirection doorDirection;
	private Character roomInitial;
	public enum DoorDirection{
		LEFT, RIGHT, UP, DOWN, NONE;
	}
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	public Object getInitial() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
