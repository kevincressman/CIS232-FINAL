package edu.cis232.survivalgame;


public abstract class Openable extends Object{
	protected int value;
	
	public Openable(String name, int value){
		super(name);
		this.value = value;
	}
	public Openable(String name, Item i){
		super(name);
		this.value = value;
	}
	
	public String Open(){
		String statement = "You open the "+getName();
		return statement;
	}
}