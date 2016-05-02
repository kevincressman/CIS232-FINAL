package edu.cis232.survivalgame;

public class Door extends Openable{
	protected int value;
	
	public Door(String name, int value){
		super(name, value);
		this.value = value;
	}
	public String Open(){
		String statement = "You open the "+getName();
		return statement;
		//whatever code were working on to go to next room
	}
	public void Inspect(){
		System.out.println("A Sturdy Wooden Door.");
	}
}