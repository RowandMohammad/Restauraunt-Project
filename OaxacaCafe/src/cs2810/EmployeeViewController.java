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
	private TableColumn<Employee, Integer> colEmpID;
	@FXML
	private TableColumn<Employee, String> colEmpName;
	@FXML
	private TableColumn<Employee, Integer> colEmpUser;
	@FXML
	private TableColumn<Employee, Integer> colEmpPass;
	@FXML
	private TableColumn<Employee, String> colEmpRole;
	@FXML
	private TableColumn<Employee, String> colEmpEmail;
	@FXML
	private TableView employeeList;
	
	@FXML
	private void initialize() throws URISyntaxException, SQLException {
		colEmpID.setCellValueFactory(cellData -> cellData.getValue().getEmployeeID().asObject());
		colEmpName.setCellValueFactory(cellData -> cellData.getValue().getEmployeeName());
		colEmpUser.setCellValueFactory(cellData -> cellData.getValue().getEmployeeUser().asObject());
		colEmpPass.setCellValueFactory(cellData -> cellData.getValue().getEmployeePass().asObject());
		colEmpRole.setCellValueFactory(cellData -> cellData.getValue().getEmployeeRole());
		colEmpEmail.setCellValueFactory(cellData -> cellData.getValue().getEmployeeEmail());
		ObservableList<Employee> empList = EmployeeDAO.getAllRecords();
		populateTable(empList);
		
	}

	private void populateTable(ObservableList<Employee> empList) {
		employeeList.setItems(empList);
		
	}
	

}
