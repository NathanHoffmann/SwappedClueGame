package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class Player {

	private String name;
	private Color color;
	private int row;
	private int col;
	private String sugRoom;
	private String sugWeapon;
	private String sugPerson;
	private ArrayList<Card> unSeen;
	ArrayList<Card> cards=new ArrayList<Card>();
	
	public String getSugRoom() {
		return sugRoom;
	}
	public void setSugRoom(String sugRoom) {
		this.sugRoom = sugRoom;
	}
	public String getSugWeapon() {
		return sugWeapon;
	}
	public void setSugWeapon(String sugWeapon) {
		this.sugWeapon = sugWeapon;
	}
	public String getSugPerson() {
		return sugPerson;
	}
	public void setSugPerson(String sugPerson) {
		this.sugPerson = sugPerson;
	}
	
	public ArrayList<Card> getUnSeen(){
		return unSeen;
	}
	public void updateUnSeen(String seen){
		unSeen.remove(seen);
		
	}
	public void setUnSeen(ArrayList<Card> unseen){
		unSeen=new ArrayList<Card>(unseen);
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public Card disproveSuggestion(String person, String weapon, String room) {
		ArrayList<Card> possibleCards = new ArrayList<Card>();
		Random rand = new Random();
		for(Card i:cards) {
			if(i.getName() == person || i.getName() == weapon || i.getName() == room) {
				possibleCards.add(i);
			}
		}
		if(possibleCards.size() == 0)
			return null;
		else {
		int index = rand.nextInt(possibleCards.size());
		return possibleCards.get(index);
		}
	}


}
