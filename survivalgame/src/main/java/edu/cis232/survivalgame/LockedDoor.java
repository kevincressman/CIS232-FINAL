package edu.cis232.survivalgame;

public class LockedDoor extends Object{
	protected int value;
	protected boolean locked = true;
	
	public LockedDoor(String name, int value){
		super(name);
		this.value = value;
	}
	public void Open(){
		if (locked== true){
			System.out.println("The "+getName()+" is Locked. You Must Unlock It.");
		}
		else{
			System.out.println("You open the "+getName());
			//whatever code were working on to go to next room
		}
	}
	public void Inspect(){
		System.out.println("A Sturdy Wooden Door.");
	}
}