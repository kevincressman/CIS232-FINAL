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
			//on click
			OpenifDoor(object);
		}
    }
    
		public static void OpenifDoor(Object object){
			if(object instanceof Door ||object instanceof Chest){
				Openable opened = (Openable)object;
				opened.Open();
			}else if(object instanceof LockedDoor){
				Openable opened = (Openable)object;
				opened.Open();
			} else {
				System.out.println(object.getName() + " is not openable");
			}
		}
		public static void CheckForKey(LockedDoor door, Item[] inventory){
			int inventorySpace = 0;
			for(Item object : inventory){
				if(door.getValue()==inventory[inventorySpace].getValue()){
					door.unlock();
					System.out.println(object.getName() + " Has Been Opened!");
				}
				
			
		}

    }
}
    
    
