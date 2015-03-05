package clueGame;

public class Card {

	private String name;
	private cardType type;
	public enum cardType{PERSON, WEAPON, ROOM}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public cardType getType() {
		return type;
	}
	public void setType(cardType type) {
		this.type = type;
	}
	
	public boolean equals(){
		return true;
	}
	

}


