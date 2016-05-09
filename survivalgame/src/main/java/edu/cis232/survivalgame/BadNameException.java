package edu.cis232.survivalgame;

public class BadNameException extends Exception{

	public BadNameException(String name) {
			super(name+" Is an invalid name. Try Again.");
	}

}
