package cs2810;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MenuMain {
  Menu_Item[] mainItems = new Menu_Item[10];
  Menu_Item[] sideItems = new Menu_Item[10];
  Menu_Item[] drinkItems = new Menu_Item[10];
  

  
  public void initialiseMainItems() throws IOException {
    File file = new File("Main.txt");
    Scanner sc = new Scanner(file);
    int count = 0;
    
    while (sc.hasNextLine()) {
      
      String name = sc.nextLine();
      int calories = Integer.valueOf(sc.nextLine());
      String[] ingredients = sc.nextLine().split(",");
      String[] dietaryRequirements = sc.nextLine().split(",");
      double price = Double.parseDouble(sc.nextLine());
      sc.nextLine();
      
      Menu_Item mainitem = new Menu_Item(name, calories, ingredients, price, "Main", true, dietaryRequirements);
      mainItems[count] = mainitem;
      count ++;
    }
    
    
  }
  
  public void initialiseSideItems() throws IOException {
    File file = new File("Sides.txt");
    Scanner sc = new Scanner(file);
    int count = 0;
    
    while (sc.hasNextLine()) {
      
      String name = sc.nextLine();
      int calories = Integer.valueOf(sc.nextLine());
      String[] ingredients = sc.nextLine().split(",");
      String[] dietaryRequirements = sc.nextLine().split(",");
      double price = Double.parseDouble(sc.nextLine());
      sc.nextLine();
      
      Menu_Item mainitem = new Menu_Item(name, calories, ingredients, price, "Side", true, dietaryRequirements);
      sideItems[count] = mainitem;
      count ++;
    }


    
  }
  
  public void initiliseDrinkItems() throws IOException {
    File file = new File("Drinks.txt");
    Scanner sc = new Scanner(file);
    int count = 0;
    
    while (sc.hasNextLine()) {
      
      String name = sc.nextLine();
      int calories = Integer.valueOf(sc.nextLine());
      String[] ingredients = sc.nextLine().split(",");
      String[] dietaryRequirements = sc.nextLine().split(",");
      double price = Double.parseDouble(sc.nextLine());
      sc.nextLine();
      
      Menu_Item mainitem = new Menu_Item(name, calories, ingredients, price, "Drink", true, dietaryRequirements);
      drinkItems[count] = mainitem;
      count ++;
    }

    
  }

}
