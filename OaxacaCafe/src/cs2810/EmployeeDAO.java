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
				emp.setEmpID(rsSet.getInt("employeeid"));
				emp.setEmpName(rsSet.getString("employeename"));
				emp.setEmpUser(rsSet.getInt("username"));
				emp.setEmpPass(rsSet.getInt("password"));
				emp.setEmpRole(rsSet.getString("employeerole"));
				emp.setEmpEmail(rsSet.getString("employeeemail"));
				empList.add(emp);
				
			}
			return empList;

		} catch (SQLException e) {
			System.out.println("Error occured while fetching data from database" + e);
			e.printStackTrace();
			throw e;
		}
	}

	public static ObservableList<Employee> searchEmployee(String empName) throws URISyntaxException, SQLException {
		String employeeSql = "select * from staffinfo where employeename like '%" + empName + "%' ";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet rsEmployee = DatabaseInitialisation.executeSelect(dbConnection, employeeSql);
		ObservableList<Employee> list = getEmployeeObjects(rsEmployee);
		System.out.println(empName);
		return list;
		
	}

}
