package clueGame;


import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import clueGame.Card.cardType;



public class ComputerPlayer extends Player {

	private char lastRoom=' ';
	private char secondLastRoom=' ';

	public void makeSuggestion(String room){
		
		setSugRoom(room);
		String suggestWeapon="";
		String suggestPerson="";

		ArrayList<Card>unSeenCards=new ArrayList<Card>(getUnSeen());
		
		Random rand=new Random();
		int index;
		
		while(suggestPerson.equals("") || suggestWeapon.equals("") ) {
			index=rand.nextInt(cards.size());
			if(unSeenCards.get(index).getType() == cardType.WEAPON) {
				suggestWeapon = unSeenCards.get(index).getName();
			} else if(unSeenCards.get(index).getType() == cardType.PERSON) {
				suggestPerson = unSeenCards.get(index).getName();
			}
		}

		setSugWeapon(suggestWeapon);
		setSugPerson(suggestPerson);		
	}
	
	
	public void pickLocation(Set<BoardCell> targets){
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
}
