  
package cs2810;

public class Menu_Item {
  String name;
  int calories;
  String[] ingredients;
  double price;
  String type;
  boolean available;
  String[] dietaryRequirements;
  
  public Menu_Item(String name, int calories, String[] ingredients, double price, String type, boolean available, String[] dietaryRequirements) {
    this.name = name;
    this.type = type;
    this.price = price;
    this.calories = calories;
    this.available = true;
    this.ingredients = ingredients;
    this.dietaryRequirements = dietaryRequirements;
  }
  
  

}