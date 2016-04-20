package edu.cis232.survivalgame;

public class Door extends Object{
	protected int value;
	
	public Door(String name, int value){
		super(name);
		this.value = value;
	}
	public void Open(){
		System.out.println("You open the "+getName());
		//whatever code were working on to go to next room
	}
	public void Inspect(){
		System.out.println("A Sturdy Wooden Door.");
	}
}