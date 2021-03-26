package cs2810;

import java.net.URISyntaxException;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ItemStockViewController {
	@FXML
	private TableColumn<ItemStock, String> colItName;
	@FXML
	private TableColumn<ItemStock, String> colItIng;
	@FXML
	private TableColumn<ItemStock, Integer> colItQStock;
	@FXML
	private TableColumn<ItemStock, Float> colItUPrice;
	@FXML
	private TableColumn<ItemStock, Float> colItSValue;
	@FXML
	private TableView<ItemStock> stockMainsView;
	@FXML
	private TableView<ItemStock> stockSidesView;
	@FXML
	private TableView<ItemStock> stockDrinksView;
	@FXML
	private Button BackToMenu;
	@FXML
	private TabPane tabPane;

	@FXML
	private Tab mainsTab;

	@FXML
	private Tab sidesTab;

	@FXML
	private Tab drinksTab;
	ObservableList<ItemStock> itemMainsList;
	ObservableList<ItemStock> itemSidesList;
	ObservableList<ItemStock> itemDrinksList;
	public ObservableList<ItemStock> getItemMainsList() {
		return itemMainsList;
	}

	public void setItemMainsList(ObservableList<ItemStock> itemMainsList) {
		this.itemMainsList = itemMainsList;
	}

	public ObservableList<ItemStock> getItemSidesList() {
		return itemSidesList;
	}

	public void setItemSidesList(ObservableList<ItemStock> itemSidesList) {
		this.itemSidesList = itemSidesList;
	}

	public ObservableList<ItemStock> getItemDrinksList() {
		return itemDrinksList;
	}

	public void setItemDrinksList(ObservableList<ItemStock> itemDrinksList) {
		this.itemDrinksList = itemDrinksList;
	}
	

	@FXML
	private void initialize() throws URISyntaxException, SQLException {
		colItName.setCellValueFactory(cellData -> cellData.getValue().getItemName());
		colItIng.setCellValueFactory(cellData -> cellData.getValue().getItemIng());
		colItQStock.setCellValueFactory(cellData -> cellData.getValue().getItemQStock().asObject());
		colItUPrice.setCellValueFactory(cellData -> cellData.getValue().getItemUPrice().asObject());
		colItSValue.setCellValueFactory(cellData -> cellData.getValue().getItemSValue().asObject());
		setItemMainsList(ItemStockDAO.getAllRecords("mainmenu"));
		setItemSidesList(ItemStockDAO.getAllRecords("sidesmenu"));
		setItemDrinksList(ItemStockDAO.getAllRecords("drinksmenu"));
		populateTable(itemMainsList);
		changeTable();
		System.out.println(tabPane.getSelectionModel().getSelectedItem().getText());

	}

	@FXML
	void BackToMenuPressed(ActionEvent event) {
		((Stage) BackToMenu.getScene().getWindow()).close();

	}

	private void populateTable(ObservableList<ItemStock> itemList) {

		stockMainsView.setItems(itemList);
	}
	
	void changeTable() {
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if (newValue.equals(mainsTab)) {
					populateTable(getItemMainsList());
				}else if (newValue.equals(sidesTab)) {
					populateTable(getItemSidesList());
				} else if (newValue.equals(drinksTab)) {
					populateTable(getItemDrinksList());
				}
				// TODO Auto-generated method stub
				
			}
	});
	}


}
