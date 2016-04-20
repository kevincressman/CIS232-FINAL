package edu.cis232.survivalgame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Object[] map = { new Object("Door 1") };
        Object[] inventory = { new Object("Key 1")};
		
		for(Object object : map){
			OpenifDoor(object);
		}
    }
    
		public static void OpenifDoor(Object object){
			if(object instanceof Door ||object instanceof Chest){
				Openable opened = (Openable)object;
				opened.Open();
			} else {
				System.out.println(object + " is not openable");
			}
		}

    }
    
    
