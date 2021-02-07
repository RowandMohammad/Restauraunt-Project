  
package cs2810;

public class Menu_Item {
  String name;
  int calories;
  String[] ingredients;
  double price;
  String type;
  boolean available;
  String[] dietaryRequirements;
  int cooktime;
  
  public Menu_Item(String name, int calories, String[] ingredients, double price, int cooktime, String type, boolean available, String[] dietaryRequirements) {
    this.name = name;
    this.type = type;
    this.price = price;
    this.cooktime =cooktime;
    this.calories = calories;
    this.available = true;
    this.ingredients = ingredients;
    this.dietaryRequirements = dietaryRequirements;
  }
  
  

}