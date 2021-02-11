
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

	public void Clone(Menu_Item item) {
		// cloneList calls Clone() to clone each individual item
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
