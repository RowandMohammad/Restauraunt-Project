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
    String orderDate;

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
	  //Default constructor for cloning a Menu_Item
    }

  /**
   * Creates a new instance of the menu item so that two foods that are the exact same will still
   * be classified as separate objects - this is so they can have their own orderTime set to them.
   * 
   * @param item the menu item to be cloned to have same attributes
   * @return returns the new item object which is cloned
   */
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
	
	public void setOrderDate(String orderDate) {
	  this.orderDate = orderDate;
	}
	
	public String getOrderDate() {
	  return orderDate;
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
