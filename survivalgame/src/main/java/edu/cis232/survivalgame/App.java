package edu.cis232.survivalgame;
import java.util.ArrayList;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Openable[] map = { new Door("First Door", 0), new LockedDoor("Iron Door", 1), new Chest("Wooden Chest", new Item ("Second Key", 2)), new LockedDoor("Portcullis", 2) };
        //{ new Item("First Key", 1)};
		ArrayList<Item> inventory = new ArrayList<Item>();
		inventory.add(new Item("First Key", 1));
		for(Object object : map){
			//on click
			OpenifOpenable(object, inventory);
		}
    }
    
		public static void OpenifOpenable(Object object, ArrayList<Item> inventory){
			if(object instanceof Door){
				Openable opened = (Openable)object;
				opened.Open();
				
			}else if(object instanceof Chest){
				Openable opened = (Openable)object;
				opened.Open();
				Item i = ((Chest) opened).getItem();
				inventory.add(i);
				
			}else if(object instanceof LockedDoor){
				Openable opened = (Openable)object;
				opened.Open();
				//on click
				CheckForKey((LockedDoor)object, inventory);
				
			} else {
				System.out.println("The "+ object.getName() + " is not openable");
			}
		}
		public static void CheckForKey(LockedDoor door, ArrayList<Item> inventory){
			
			for(Item i : inventory){
				if(door.getValue()==i.getValue()){
					door.unlock();
					System.out.println("The "+ door.getName() + " Has Been Opened!");
				}
				
			
		}

    }
}
    
    
