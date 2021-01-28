package cs2810;


import org.controlsfx.control.PopOver;

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
    Button calories    = new Button("  Calories   ");
    Button ingredients = new Button("Ingredients");
    private String cal;
    private String[] ing;
    String[] dietaryRequirements;

    /**
     *  No default construction allowed
     *  Must be initialized using parameterized constructor
     *  so that all necessary data is provided
     */
    @SuppressWarnings("unused")
	private ListViewItem(){

    }
    /**
     * setup initial view and callback for list item
     * parameterized constructor  
     * @param _name: Product's Name
     * @param _price: Product's unint price
     * @param cal: Product's total calories
     * @param ing: Product's ingredient list
     */
    public ListViewItem(String _name, String _price, String cal, String [] ing, String[] dietaryRequirements) {
        super();
        this.cal = cal;
        this.ing = ing;
        this.name = new Label(_name);
        this.price = new Label(_price);
        this.dietaryRequirements = dietaryRequirements;
        VBox lablesCotaier = new VBox(this.name, this.price);
        VBox buttonContainer = new VBox(this.ingredients, this.calories);
        VBox.setMargin(calories,new Insets(2,0,0,0));
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
    private void setCaloriesActionListener(){
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
    private void setIngredientActionListener(){
        ingredients.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String _ingredients = "";
                        for(String _ing : ListViewItem.this.ing) {
                            _ingredients += _ing+"\n" ;
                        }
                        Label l = new Label(_ingredients);
                        Insets insets = new Insets(5,5,5,5);
                        VBox.setMargin(l,insets);
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
        this.name.setText(name); ;
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
=======
package cs2810;


import org.controlsfx.control.PopOver;

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
    Button calories    = new Button("  Calories   ");
    Button ingredients = new Button("Ingredients");
    private String cal;
    private String[] ing;
    String[] dietaryRequirements;

    /**
     *  No default construction allowed
     *  Must be initialized using parameterized constructor
     *  so that all necessary data is provided
     */
    @SuppressWarnings("unused")
	private ListViewItem(){

    }
    /**
     * setup initial view and callback for list item
     * parameterized constructor  
     * @param _name: Product's Name
     * @param _price: Product's unint price
     * @param cal: Product's total calories
     * @param ing: Product's ingredient list
     */
    public ListViewItem(String _name, String _price, String cal, String [] ing, String[] dietaryRequirements) {
        super();
        this.cal = cal;
        this.ing = ing;
        this.name = new Label(_name);
        this.price = new Label(_price);
        this.dietaryRequirements = dietaryRequirements;
        VBox lablesCotaier = new VBox(this.name, this.price);
        VBox buttonContainer = new VBox(this.ingredients, this.calories);
        VBox.setMargin(calories,new Insets(2,0,0,0));
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
    private void setCaloriesActionListener(){
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
    private void setIngredientActionListener(){
        ingredients.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String _ingredients = "";
                        for(String _ing : ListViewItem.this.ing) {
                            _ingredients += _ing+"\n" ;
                        }
                        Label l = new Label(_ingredients);
                        Insets insets = new Insets(5,5,5,5);
                        VBox.setMargin(l,insets);
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
        this.name.setText(name); ;
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