package cs2810;

import java.net.URISyntaxException;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

	@FXML
	private void initialize() throws URISyntaxException, SQLException {
		colItName.setCellValueFactory(cellData -> cellData.getValue().getItemName());
		colItIng.setCellValueFactory(cellData -> cellData.getValue().getItemIng());
		colItQStock.setCellValueFactory(cellData -> cellData.getValue().getItemQStock().asObject());
		colItUPrice.setCellValueFactory(cellData -> cellData.getValue().getItemUPrice().asObject());
		colItSValue.setCellValueFactory(cellData -> cellData.getValue().getItemSValue().asObject());

		ObservableList<ItemStock> itemMainsList = ItemStockDAO.getAllRecords("mainmenu");
		ObservableList<ItemStock> itemSidesList = ItemStockDAO.getAllRecords("sidesmenu");
		ObservableList<ItemStock> itemDrinksList = ItemStockDAO.getAllRecords("drinksmenu");
		populateTable(itemMainsList, "main");
		System.out.println(tabPane.getSelectionModel().getSelectedItem().getText());


	}

	@FXML
	void BackToMenuPressed(ActionEvent event) {
		((Stage) BackToMenu.getScene().getWindow()).close();

	}

	private void populateTable(ObservableList<ItemStock> itemList, String table) {
		if (table.equals("main")) {
			stockMainsView.setItems(itemList);
		} else if (table.equals("sides")) {
			stockSidesView.setItems(itemList);
		} else if (table.equals("drinks")) {
			stockDrinksView.setItems(itemList);
		}

	}

}
