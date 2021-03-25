package cs2810;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemStockDAO {
	public static ObservableList<ItemStock> getAllRecords(String menu) throws URISyntaxException, SQLException {
		String employeeQuery = "SELECT * FROM " + menu;
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet rsSet = DatabaseInitialisation.executeSelect(dbConnection, employeeQuery);
		ObservableList<ItemStock> itemList = getItemObjects(rsSet);
		return itemList;

	}

	private static ObservableList<ItemStock> getItemObjects(ResultSet rsSet) throws SQLException {
		try {
			ObservableList<ItemStock> itemList = FXCollections.observableArrayList();
			while (rsSet.next()) {
				ItemStock it = new ItemStock();
				it.setItName(rsSet.getString("name"));
				it.setItIng(rsSet.getString("ingredients"));
				it.setItQStock(rsSet.getInt("stock"));
				it.setItUPrice(rsSet.getFloat("price"));
				it.setItSValue((rsSet.getFloat("price")*rsSet.getInt("stock")));
				itemList.add(it);

			}
			return itemList;

		} catch (SQLException e) {
			System.out.println("Error occured while fetching data from database" + e);
			e.printStackTrace();
			throw e;
		}
	}
}
