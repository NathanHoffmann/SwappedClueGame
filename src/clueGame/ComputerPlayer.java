package clueGame;


import java.util.Set;



public class ComputerPlayer extends Player {


	private char lastRoom=' ';
	private char secondLastRoom=' ';
	
	public void pickLocation(Set<BoardCell> targets){
		this.setRow(5);
		this.setCol(3);
		
	}
	public void createSuggestion(){
		
	}
	
	
}
