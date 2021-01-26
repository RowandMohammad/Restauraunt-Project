package cs2810;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMain {

  
  ArrayList<Menu_Item> mainItems = new ArrayList<Menu_Item>();
  ArrayList<Menu_Item> sideItems = new ArrayList<Menu_Item>();
  ArrayList<Menu_Item> drinkItems = new ArrayList<Menu_Item>();
  

  
  public void initialiseMainItems() throws IOException {
    File file = new File("Main.txt");
    Scanner sc = new Scanner(file);
    int count = 0;
    
    while (sc.hasNextLine()) {
      
      String name = sc.nextLine();
      int calories = Integer.valueOf(sc.nextLine());
      String[] ingredients = sc.nextLine().split(",");
      double price = Double.parseDouble(sc.nextLine());
      sc.nextLine();
      
      Menu_Item mainitem = new Menu_Item(name, calories, ingredients, price, "Main", true);
      mainItems.add(mainitem);
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
      double price = Double.parseDouble(sc.nextLine());
      sc.nextLine();
      
      Menu_Item sideitem = new Menu_Item(name, calories, ingredients, price, "Side", true);
      sideItems.add(sideitem);
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
      double price = Double.parseDouble(sc.nextLine());
      sc.nextLine();
      
      Menu_Item drinkitem = new Menu_Item(name, calories, ingredients, price, "Drink", true);
      drinkItems.add(drinkitem);
      count ++;
    }

    
  }

}
