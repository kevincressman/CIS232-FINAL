package edu.cis232.survivalgame;


public abstract class Openable extends Object{
	protected int value;
	
	public Openable(String name, int value){
		super(name);
		this.value = value;
	}
	
	public void Open(){
		System.out.println("You open the "+getName());
	}
}