package edu.cis232.survivalgame;

public class LockedDoor extends Openable{
	protected int value;
	protected boolean locked = true;
	
	public LockedDoor(String name, int value){
		super(name, value);
		this.value = value;
		
	}
	public int getValue() {
			return value;
		}

	public void setValue(int value) {
			this.value = value;
		}
	public String Open(){
		if (locked== true){
			String statement = "The "+getName()+" is Locked. You Must Unlock It.";
			return statement;
		}
		else{
			String statement = "You open the "+getName();
			return statement;
			//whatever code were working on to go to next room
			}
		
		}
	public String unlock(){
			locked = false;
			String statement = "You Unlocked the "+getName();
			return statement;
	}
	public void Inspect(){
		System.out.println("A Sturdy Wooden Door.");
	}
}