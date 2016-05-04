package edu.cis232.survivalgame;

public class Chest extends Openable{
	
	protected int value;
	
	private Item item;
	public Chest(String name, Item i){
	super(name, 0);	
		this.setItem(i);
	}
	public String Open(){
		String statement = "You open the "+getName()+ ".\nIt Contains the "+item.getName();
		return statement;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String inspect(){
		String statement = "A Wooden Chest. What could be inside?";
		return statement;
	}
}