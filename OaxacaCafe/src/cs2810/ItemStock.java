package cs2810;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ItemStock {
	
	private StringProperty nameProperty;
	private StringProperty ingProperty;
	private IntegerProperty qStockProperty;
	private FloatProperty unitPriceProperty;
	private FloatProperty stockValueProperty;
	
	public ItemStock() {
		this.nameProperty = new SimpleStringProperty();
		this.ingProperty = new SimpleStringProperty();
		this.qStockProperty = new SimpleIntegerProperty();
		this.unitPriceProperty = new SimpleFloatProperty();
		this.stockValueProperty = new SimpleFloatProperty();

		
	}

	//This is for MenuItem name
	public String getItName() {
		return nameProperty.get();	
	}
	public void setItName(String name) {
		this.nameProperty.set(name);
	}
	public StringProperty getItemName() {
		return nameProperty;	
	}
	//This is for MenuItem ingredients
	public String getItIng() {
		return ingProperty.get();	
	}
	public void setItIng(String ingredients) {
		this.ingProperty.set(ingredients);
	}
	public StringProperty getItemIng() {
		return ingProperty;	
	}
	//This is for MenuItem stock quantity
	public Integer getItQStock() {
		return qStockProperty.get();	
	}
	public void setItQStock(int stockQuantity) {
		this.qStockProperty.set(stockQuantity);
	}
	public IntegerProperty getItemQStock() {
		return qStockProperty;	
	}
	//This is for MenuItem unit price
	public float getItUPrice() {
		return unitPriceProperty.get();	
	}
	public void setItUPrice(float unitPrice) {
		this.unitPriceProperty.set(unitPrice);
	}
	public FloatProperty getItemUPrice() {
		return unitPriceProperty;	
	}
	//This is for MenuItem stock value
	public float getItSValue() {
		return stockValueProperty.get();	
	}
	public void setItSValue(float stockValue) {
		this.stockValueProperty.set(stockValue);
	}
	public FloatProperty getItemSValue() {
		return stockValueProperty;	
	}
	
	

}