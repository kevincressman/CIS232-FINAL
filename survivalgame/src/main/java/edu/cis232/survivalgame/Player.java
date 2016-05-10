package edu.cis232.survivalgame;

public class Player{
String name;
	public Player(String n) throws BadNameException {
		if(n.isEmpty()){
			throw new BadNameException(n);
		}
		else{
			name=n;
			}
	}

}
