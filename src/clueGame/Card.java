package clueGame;

public class Card {

	private String name;
	private String type;
	public enum careType{PERSON, WEAPON, ROOM}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean equals(){
		return true;
	}
	

}


