package edu.cis232.survivalgame;

public class Chest extends Object{
	protected int value;
	
	public Chest(String name, int value){
		super(name);
		this.value = value;
	}
	public void Open(){
		System.out.println("You open the "+getName());
		//get items to inventory
	}
}