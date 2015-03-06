package clueGame;


import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import clueGame.Card.cardType;



public class ComputerPlayer extends Player {


	public ArrayList<String> getSuggestion() {
		return suggestion;
	}


	public void setSuggestion(ArrayList<String> suggestion) {
		this.suggestion = suggestion;
	}
	private char lastRoom=' ';
	private char secondLastRoom=' ';
	private ArrayList<String>suggestion=new ArrayList<String>();

	public void makeSuggestion(){
		//ClueGame game=new ClueGame();
		
		String suggestWeapon="";
		String suggestPerson="";
		//to make sure to suggest room outside
		//String suggestRoom;
//suggestRoom=game.getBoard().getRooms().get(game.getBoard().getCellAt(this.getRow(), this.getCol()).getInitial1().charAt(0));

		ArrayList<String>cards=new ArrayList<String>(getUnSeen());
		
		for(int i=0; i<cards.size();i++){
			Random rand=new Random();
		
			int index=rand.nextInt(cards.size());
		
				suggestPerson=cards.get(i);
			
		
				suggestWeapon=cards.get(i);
			if(suggestPerson.length()!=0&&suggestWeapon.length()!=0){
				break;
			}
		}
		//suggestion.add(suggestRoom);
		suggestion.add(suggestPerson);
		suggestion.add(suggestWeapon);
		
	}
	
	
	public void pickLocation(Set<BoardCell> targets){
		//this.setRow(5);
		//this.setCol(3);
		ArrayList<BoardCell> doorLocation=new ArrayList<BoardCell>();
		
		for( BoardCell i: targets){
			if(i.isDoorway()){
				//needed char need work on initial
				if(i.getInitial1().charAt(0)!=lastRoom&&i.getInitial1().charAt(0)!=secondLastRoom){
					this.setRow(i.getRow());
					this.setCol(i.getColumn());
					secondLastRoom=lastRoom;
					lastRoom=i.getInitial1().charAt(0);
					break;
				}
				else{
					doorLocation.add(i);
				}
			}
			targets.removeAll(doorLocation);
			Random rand=new Random();
			int index = rand.nextInt(targets.size());
			BoardCell[] aTargets = targets.toArray(new BoardCell[0]);
			this.setRow(aTargets[index].getRow());
			this.setCol(aTargets[index].getColumn());
			
		}
		
	}
	public void createSuggestion(){
		
	}
	
	
}
