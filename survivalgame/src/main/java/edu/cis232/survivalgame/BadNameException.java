package edu.cis232.survivalgame;

public class BadNameException extends Exception{

	public BadNameException(String name) {
			super(String.format("Please Enter a Name"));
	}

}
