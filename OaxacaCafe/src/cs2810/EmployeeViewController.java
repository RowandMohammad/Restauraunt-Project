package cs2810;

import java.net.URISyntaxException;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	private TableView<Employee> employeeList;
	@FXML
	private Button BackToOrdering;
	@FXML
	private TextField searchEmpName;
	@FXML
	private Button searchButton;
	@FXML
	private Button searchAllEmployee;

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

	@FXML
	void BackToOrderingPressed(ActionEvent event) {
		((Stage) BackToOrdering.getScene().getWindow()).close();

	}

	private void populateTable(ObservableList<Employee> empList) {
		employeeList.setItems(empList);

	}

	@FXML
	private void searchEmployee(ActionEvent event) throws URISyntaxException, SQLException {
		ObservableList<Employee> list = EmployeeDAO.searchEmployee(searchEmpName.getText());
		if (list.size() > 0) {
			populateTable(list);
		}

	}

	@FXML
	void searchAllEmployees(ActionEvent event) throws URISyntaxException, SQLException {
		ObservableList<Employee> list = EmployeeDAO.getAllRecords();
		populateTable(list);

	}

}
