package edu.cis232.survivalgame;
//REQ#5
public class Object {
	private String name;

	public Object(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}