package edu.cis232.survivalgame;
//REQ#4//REQ#6
public class Chest extends Object implements Openable{
	
	protected int value;
	
	private Item item;
	public Chest(String name, Item i){
	super(name);	
		this.setItem(i);
	}
	@Override
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
	@Override
	public String inspect(){
		String statement = "A Wooden Chest. What could be inside?";
		return statement;
	}
}