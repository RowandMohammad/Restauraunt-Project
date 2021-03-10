package cs2810;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class EmployeeViewController {


	@FXML
	private static ListView<String> employeeList ;

	static void populateEmployeeView() {
		try {
			String query = "select * from staffinfo";
			ResultSet employeeSelect = DatabaseInitialisation.executeSelect(DatabaseInitialisation.getConnection(), query);

			while (employeeSelect.next()) {
				String current = employeeSelect.getString("staffinfo");
				ObservableList<String> list = FXCollections.observableArrayList(current);
				employeeList.getItems().addAll(list);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
