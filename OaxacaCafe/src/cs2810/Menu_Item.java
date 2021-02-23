
package cs2810;

import java.util.ArrayList;



public class Menu_Item {
  String name;
  int calories;
  String[] ingredients;
  double price;
  String type;
  boolean available;
  String[] dietaryRequirements;
  int cooktime;
  String purchaseDate;
  ArrayList<String> order_times = new ArrayList<String>();

	public Menu_Item(String name, int calories, String[] ingredients, double price, int cooktime, String type,
			boolean available, String[] dietaryRequirements) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.cooktime = cooktime;
		this.calories = calories;
		this.available = true;
		this.ingredients = ingredients;
		this.dietaryRequirements = dietaryRequirements;
	}

	public Menu_Item() {
    // TODO Auto-generated constructor stub
  }

  public Menu_Item Clone(Menu_Item item) {
	  Menu_Item clone = new Menu_Item();
	  clone.name = item.name;
	  clone.type = item.type;
	  clone.price = item.price;
	  clone.cooktime = item.cooktime;
	  clone.calories = item.calories;
	  this.available = true;
	  clone.ingredients = item.ingredients;
	  clone.dietaryRequirements = item.dietaryRequirements;
      return clone;
	}
	
	public void setPurchaseDate(String purchaseDate) {
	  this.purchaseDate = purchaseDate;
	  order_times.add(purchaseDate);
	}
	
	public String getPurchaseDate() {
	  return purchaseDate;
	}
	
	public String getPurchaseDate2(int index) {
	  System.out.println(order_times);
	  System.out.println("REAL CALL:"+ order_times.get(index));
      return order_times.get(index);
    }

	public void cloneList() {
		// method to clone arraylist of menu_item without pointing to same objects

	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String[] getIngredients() {
		return ingredients;
	}

	public void setIngredients(String[] ingredients) {
		this.ingredients = ingredients;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String[] getDietaryRequirements() {
		return dietaryRequirements;
	}

	public void setDietaryRequirements(String[] dietaryRequirements) {
		this.dietaryRequirements = dietaryRequirements;
	}
	  

}
