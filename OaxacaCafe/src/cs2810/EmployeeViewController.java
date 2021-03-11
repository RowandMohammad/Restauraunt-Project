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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmployeeViewController {
	@FXML
	private TableColumn<Employee, Integer> coldEmpID;
	@FXML
	private TableColumn<Employee, String> colEmpName;
	@FXML
	private TableColumn<Employee, Integer> coldEmpUser;
	@FXML
	private TableColumn<Employee, Integer> coldEmpPass;
	@FXML
	private TableColumn<Employee, String> coldEmpRole;
	@FXML
	private TableColumn<Employee, String> coldEmpEmail;
	@FXML
	private TableView employeeList;
	

}
