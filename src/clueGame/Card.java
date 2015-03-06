package clueGame;

public class Card {

	private String name;
	private cardType type;
	public enum cardType{PERSON, WEAPON, ROOM}
	
	public Card() {
		
	}
	public Card(String name, cardType type) {
		this.name = name;
		this.type = type;
	}
	
	
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

	// Testing
	@Override
	public String toString() {
		return "Card [name=" + name + ", type=" + type + "]";
	}
}


