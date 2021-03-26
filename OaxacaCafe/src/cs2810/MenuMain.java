package cs2810;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used for initialising all of the Menu Items as objects from a text file
 * 
 * @author Erikas Vieraitis
 *
 */
public class MenuMain {

	private static ArrayList<Menu_Item> mainItems = new ArrayList<Menu_Item>();
	private static ArrayList<Menu_Item> sideItems = new ArrayList<Menu_Item>();
	private static ArrayList<Menu_Item> drinkItems = new ArrayList<Menu_Item>();

	

	/**
	 * @author Erikas Vieraitis
	 * 
	 * Method used to read from the main menu text file and insert the information as menu objects
	 * 
	 * @return mainItems returns an array list containing the main menu item objects
	 * @throws IOException
	 */
	public static ArrayList<Menu_Item> initialiseMainItems() throws IOException {
		if (null != mainItems && mainItems.size() > 0) {

		} else {
    File file = new File("Main.txt");
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine()) {

      String name = sc.nextLine();
      int calories = Integer.valueOf(sc.nextLine());
      String[] ingredients = sc.nextLine().split(",");
      String[] dietaryRequirements = sc.nextLine().split(",");
      double price = Double.parseDouble(sc.nextLine());
      int cooktime = Integer.valueOf(sc.nextLine());
      sc.nextLine();

      Menu_Item mainitem =
          new Menu_Item(name, calories, ingredients, price, cooktime, "Main", true, dietaryRequirements);
      mainItems.add(mainitem);
    }
    sc.close();
  }
    	return mainItems;
	}
	
	
	
	/**
     * @author Erikas Vieraitis
     * 
     * Method used to read from the side menu text file and insert the information as menu objects
     * 
     * @return sideItems returns an array list containing the side menu item objects
     * @throws IOException
     */
	public static ArrayList<Menu_Item> initialiseSideItems() throws IOException {
		if (null != sideItems && sideItems.size() > 0) {

		} else {
    File file = new File("Sides.txt");
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine()) {

      String name = sc.nextLine();
      int calories = Integer.valueOf(sc.nextLine());
      String[] ingredients = sc.nextLine().split(",");
      String[] dietaryRequirements = sc.nextLine().split(",");
      double price = Double.parseDouble(sc.nextLine());
      int cooktime = Integer.valueOf(sc.nextLine());
      sc.nextLine();

      Menu_Item sideitem =
          new Menu_Item(name, calories, ingredients, price,cooktime, "Side", true, dietaryRequirements);
      sideItems.add(sideitem);
    }
    sc.close();
  }
    return sideItems;
}
	
	
	
	
	/**
     * @author Erikas Vieraitis
     * 
     * Method used to read from the drinks menu text file and insert the information as menu objects
     * 
     * @return drinkItems returns an array list containing the drink menu item objects
     * @throws IOException
     */
	public static ArrayList<Menu_Item> initiliseDrinkItems() throws IOException {
		if (null != drinkItems && drinkItems.size() > 0) {

		} else {
    File file = new File("Drinks.txt");
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine()) {

      String name = sc.nextLine();
      int calories = Integer.valueOf(sc.nextLine());
      String[] ingredients = sc.nextLine().split(",");
      String[] dietaryRequirements = sc.nextLine().split(",");
      double price = Double.parseDouble(sc.nextLine());
      int cooktime = Integer.valueOf(sc.nextLine());
      sc.nextLine();

      Menu_Item drinkitem =
          new Menu_Item(name, calories, ingredients, price,cooktime, "Drink", true, dietaryRequirements);
      drinkItems.add(drinkitem);
    }
    sc.close();
  }
    return drinkItems;
	}




}
