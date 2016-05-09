package edu.cis232.survivalgame;

public class BadNameException extends Exception{

	public BadNameException(String name) {
			super("Please Enter a Name");
	}

}
