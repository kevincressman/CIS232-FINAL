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
			String statement = "The "+getName()+" is Locked. Try using the scroll wheel\n"
					+ "to look in inventory for a way to open it";
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
	public String inspect(){
		String statement;
		if (locked== true){
		statement = "A More Secure Door. Locked, of course.";
		}
		else{
		statement = "A More Secure Door. Recently unlocked.";	
		}return statement;
	}
}