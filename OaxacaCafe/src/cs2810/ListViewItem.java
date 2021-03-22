package cs2810;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.Arrays;
import org.controlsfx.control.PopOver;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Utility class for managing all data for ListView items
 * data members
 * name: Label   ==> Product's Name
 * price: Label  ==> Product's Price
 * cal: String   ==> Product's Calories
 * ing: String[] ==> list of ingredients used in product
 */

/**
 * Utility class for managing all data for ListView items
 * data members 
 * 		name: Label   ==> Product's Name
 * 		price: Label  ==> Product's Price
 * 		cal: String   ==> Product's Calories
 * 		ing: String[] ==> list of ingredients used in product
 *
 */
public class ListViewItem extends HBox {

    Label name = new Label();
    Label price = new Label();
    Button calories = new Button("  Calories   ");
    Button ingredients = new Button("Ingredients");
    private String cal;
    private String ing[];
    String[] dietaryRequirements;
    ImageView imageView = new ImageView();

    /**
     *  No default construction allowed
     *  Must be initialized using parameterized constructor
     *  so that all necessary data is provided
     */
    @SuppressWarnings("unused")
    private ListViewItem() {

    }

    /**
     * setup initial view and callback for list item
     * parameterized constructor  
     * @param _name: Product's Name
     * @param _price: Product's unint price
     * @param cal: Product's total calories
     * @param ing: Product's ingredient list
     */
    public ListViewItem(String _name, String _price, String cal, String[] ing, String[] dietaryRequirements) {
        super();
        this.cal = cal;
        this.ing = ing;
        this.name = new Label(_name);
        this.price = new Label(_price);
        this.dietaryRequirements = dietaryRequirements;
        
        /**
         * Read food pictures
         * Design the size of pictures
         */
        imageView.setFitHeight(50);
    	imageView.setFitWidth(50);
    	if(_name.equals("Burrito")) {
    	Image image = new Image("foodpictures/Burrito.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Beer")) {
    	Image image = new Image("foodpictures/Beer.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Caldo de queso")) {
    	Image image = new Image("foodpictures/Caldo de queso.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Chorizo")) {
    	Image image = new Image("foodpictures/Chorizo.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Churros with Chocolate Sauce")) {
    	Image image = new Image("foodpictures/Churros with Chocolate Sauce.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Fries")) {
    	Image image = new Image("foodpictures/Fries.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Guacamole")) {
    	Image image = new Image("foodpictures/Guacamole.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Queso flameado")) {
    	Image image = new Image("foodpictures/Queso flameado.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Huevos motulenos")) {
    	Image image = new Image("foodpictures/Huevos motulenos.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Milanesas")) {
    	Image image = new Image("foodpictures/Milanesas.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Vegetarian Tacos")) {
    	Image image = new Image("foodpictures/Vegetarian Tacos.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Rice")) {
    	Image image = new Image("foodpictures/Rice.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Salsa")) {
    	Image image = new Image("foodpictures/Salsa.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Three Chicken Wings")) {
    	Image image = new Image("foodpictures/Three Chicken Wings.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Sprite")) {
    	Image image = new Image("foodpictures/Sprite.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Salad")) {
    	Image image = new Image("foodpictures/Salad.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Beans")) {
    	Image image = new Image("foodpictures/Beans.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Coke")) {
    	Image image = new Image("foodpictures/Coke.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Vanilla Milkshake")) {
    	Image image = new Image("foodpictures/Vanilla Milkshake.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Chocolate Milkshake")) {
    	Image image = new Image("foodpictures/Chocolate Milkshake.png");
    	imageView.setImage(image);  
    	}else if(_name.equals("Strawberry Milkshake")) {
    	Image image = new Image("foodpictures/Strawberry Milkshake.png");
    	imageView.setImage(image);  
    }
        VBox lablesCotaier = new VBox(this.name, this.price, this.imageView);
        VBox buttonContainer = new VBox(this.ingredients, this.calories);
        VBox.setMargin(calories, new Insets(2, 0, 0, 0));
        VBox.setMargin(imageView, new Insets(10, 0, 0, 0));
        lablesCotaier.setAlignment(Pos.BASELINE_LEFT);
        buttonContainer.setAlignment(Pos.CENTER);
        this.getChildren().addAll(lablesCotaier, buttonContainer);
        HBox.setHgrow(lablesCotaier, Priority.ALWAYS);
        setCaloriesActionListener();
        setIngredientActionListener();
    }


    /**
     * callback function for showing product calories pop-up
     */
    private void setCaloriesActionListener() {
        calories.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Label label = new Label();
                        label.setText(ListViewItem.this.cal);
                        PopOver popOver = new PopOver();
                        popOver.setContentNode(label);
                        popOver.show(calories);
                    }
                });
    }

    /**
     * callback function for showing product Ingredients pop-up
     */
    private void setIngredientActionListener() {
        ingredients.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String _ingredients = "";
                        for (String _ing : ListViewItem.this.ing) {
                            _ingredients += _ing + "\n";
                        }
                        Label l = new Label(_ingredients);
                        Insets insets = new Insets(5, 5, 5, 5);
                        VBox.setMargin(l, insets);
                        VBox box = new VBox(l);
                        box.setAlignment(Pos.CENTER);
                        PopOver popOver = new PopOver(box);
                        popOver.show(ingredients);
                    }
                });
    }

    /**
     * Utility function for converting listviewItem into string  
     * @return string object of current class
     */
    @Override
    public String toString() {
        return "ListViewItem{" +
                "name=" + name +
                ", price=" + price +
                ", calories=" + calories +
                ", ingredients=" + ingredients +
                ", cal='" + cal + '\'' +
                ", ing=" + Arrays.toString(ing) +
                '}';
    }

    /**
     * Check if passes object is equal to current object or not
     * @param o object to be checked
     * @return true if object is instance of ListviewItem and is equal to current items otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListViewItem)) return false;

        ListViewItem that = (ListViewItem) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null) return false;
        if (getCalories() != null ? !getCalories().equals(that.getCalories()) : that.getCalories() != null)
            return false;
        if (getIngredients() != null ? !getIngredients().equals(that.getIngredients()) : that.getIngredients() != null)
            return false;
        if (getCal() != null ? !getCal().equals(that.getCal()) : that.getCal() != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getIng(), that.getIng());
    }

    /**
     * @return String object of product's name 
     */
    public String getName() {
        return name.getText();
    }

    /**
     *
     * @param name of product
     */
    public void setName(String name) {
        this.name.setText(name);
        ;
    }

    /**
     *
     * @return
     */
    public Label getPrice() {
        return price;
    }


    /**
     *
     * @param price
     */
    public void setPrice(Label price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public Button getCalories() {
        return calories;
    }

    /**
     *
     * @param calories
     */
    public void setCalories(Button calories) {
        this.calories = calories;
    }

    /**
     *
     * @return
     */
    public Button getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients
     */
    public void setIngredients(Button ingredients) {
        this.ingredients = ingredients;
    }

    /**
     *
     * @return
     */
    public String getCal() {
        return cal;
    }

    /**
     *
     * @param cal
     */
    public void setCal(String cal) {
        this.cal = cal;
    }

    /**
     *
     * @return
     */
    public String[] getIng() {
        return ing;
    }

    /**
     *
     * @param ing
     */
    public void setIng(String[] ing) {
        this.ing = ing;
    }

}
