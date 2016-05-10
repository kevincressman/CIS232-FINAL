package edu.cis232.survivalgame;
//REQ#4//REQ#6
public class Door extends Object implements Openable{
	protected int value;
	
	public Door(String name, int value){
		super(name);
		this.value = value;
	}
	//REQ#10
	@Override
	public String Open(){
		String statement = "You open the "+getName();
		return statement;
		//whatever code were working on to go to next room
	}
	@Override
	public String inspect(){
		String statement = "A Sturdy Wooden Door.";
		return statement;
	}
}