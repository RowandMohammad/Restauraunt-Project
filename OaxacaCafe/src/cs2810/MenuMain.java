package cs2810;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMain {

	private static ArrayList<Menu_Item> mainItems = new ArrayList<Menu_Item>();
	private static ArrayList<Menu_Item> sideItems = new ArrayList<Menu_Item>();
	private static ArrayList<Menu_Item> drinkItems = new ArrayList<Menu_Item>();


	public static ArrayList<Menu_Item> initialiseMainItems() throws IOException {
		if (null != mainItems && mainItems.size() > 0) {

		} else {
    File file = new File("Main.txt");
    Scanner sc = new Scanner(file);
    int count = 0;

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
      count++;
    }
    sc.close();
  }
    	return mainItems;
	}

	public static ArrayList<Menu_Item> initialiseSideItems() throws IOException {
		if (null != sideItems && sideItems.size() > 0) {

		} else {
    File file = new File("Sides.txt");
    Scanner sc = new Scanner(file);
    int count = 0;

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
      count++;
    }
    sc.close();
  }
    return sideItems;
}
	public static ArrayList<Menu_Item> initiliseDrinkItems() throws IOException {
		if (null != drinkItems && drinkItems.size() > 0) {

		} else {
    File file = new File("Drinks.txt");
    Scanner sc = new Scanner(file);
    int count = 0;

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
      count++;
    }
    sc.close();
  }
    return drinkItems;
	}




}
