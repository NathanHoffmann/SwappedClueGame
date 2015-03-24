package clueGame;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;




import clueGame.Card.cardType;

public class detectiveDialog extends JDialog {

	ArrayList<Card> cards;
	JPanel people=new JPanel();	
	JPanel rooms=new JPanel();
	JPanel weapons=new JPanel();
	JPanel personGuess=new JPanel();
	JComboBox<String> personbox=new JComboBox<String>();
	JPanel roomGuess=new JPanel();
	JComboBox<String> roombox=new JComboBox<String>();
	JPanel weaponGuess=new JPanel();
	JComboBox<String> weaponbox=new JComboBox<String>();
	public detectiveDialog(ArrayList<Card> cards){
		setSize(500,600);
		setLayout(new GridLayout(3,2));
		people.setBorder(new TitledBorder(new EtchedBorder(),"People"));
		people.setLayout(new GridLayout(3,2));
		for(Card i: cards){
			if(i.getType()==cardType.PERSON){
				people.add(new JCheckBox(i.getName()));
				personbox.addItem(i.getName());
				}
			else if(i.getType()==cardType.WEAPON){
				weaponbox.addItem(i.getName());
				weapons.add(new JCheckBox(i.getName()));
				
			}
			else{ rooms.add(new JCheckBox(i.getName()));
				roombox.addItem(i.getName());
			}
		}
		add(people);
		personGuess.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
		personGuess.setLayout(new GridLayout(1,2));
		personGuess.add(personbox);
		add(personGuess);
		rooms.setBorder(new TitledBorder(new EtchedBorder(),"Rooms"));
		rooms.setLayout(new GridLayout(5,2));
		add(rooms);
		roomGuess.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		roomGuess.setLayout(new GridLayout(1,2));
		roomGuess.add(roombox);
		add(roomGuess);
		weapons.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		weapons.setLayout(new GridLayout(3,2));
		add(weapons);
		weaponGuess.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		weaponGuess.setLayout(new GridLayout(1,2));
		weaponbox.addItem("Rope");
		weaponGuess.add(weaponbox);
		add(weaponGuess);
		
	}
	
}
