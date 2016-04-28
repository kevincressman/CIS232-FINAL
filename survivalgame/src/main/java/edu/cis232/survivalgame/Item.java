package edu.cis232.survivalgame;

public class Item extends Object{

	protected int value;
	
	public Item(String name, int value){
		super(name);
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
