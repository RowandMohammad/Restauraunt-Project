package cs2810;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeDAO {
	
	public static ObservableList<Employee> getAllRecords() throws URISyntaxException, SQLException {
		String employeeQuery = "SELECT * FROM staffinfo";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet rsSet = DatabaseInitialisation.executeSelect(dbConnection, employeeQuery);
		ObservableList<Employee> empList = getEmployeeObjects(rsSet);
		return empList;

	}

	private static ObservableList<Employee> getEmployeeObjects(ResultSet rsSet) throws SQLException {
		try {
			ObservableList<Employee> empList = FXCollections.observableArrayList();
			while (rsSet.next()) {
				Employee emp = new Employee();
				emp.setEmpID(rsSet.getInt("employeeID"));
				emp.setEmpName(rsSet.getString("employeeName"));
				emp.setEmpUser(rsSet.getInt("username"));
				emp.setEmpPass(rsSet.getInt("password"));
				emp.setEmpRole(rsSet.getString("employeeRole"));
				emp.setEmpEmail(rsSet.getString("employeeEmail"));
				empList.add(emp);
				
			}
			return empList;

		} catch (SQLException e) {
			System.out.println("Error occured while fetching data from database" + e);
			e.printStackTrace();
			throw e;
		}
	}

}
