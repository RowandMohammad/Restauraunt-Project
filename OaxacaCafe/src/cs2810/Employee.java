package cs2810;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
	
	private IntegerProperty idProperty;
	private StringProperty nameProperty;
	private IntegerProperty usernameProperty;
	private IntegerProperty passwordProperty;
	private StringProperty roleProperty;
	private StringProperty emailProperty;
	
	public Employee() {
		this.idProperty = new SimpleIntegerProperty();
		this.nameProperty = new SimpleStringProperty();
		this.usernameProperty = new SimpleIntegerProperty();
		this.passwordProperty = new SimpleIntegerProperty();
		this.roleProperty = new SimpleStringProperty();
		this.emailProperty = new SimpleStringProperty();
		
	}
	//This is for employee ID
	public Integer getEmpID() {
		return idProperty.get();	
	}
	public void setEmpID(int id) {
		this.idProperty.set(id);
	}
	public IntegerProperty getEmployeeID() {
		return idProperty;	
	}
	//This is for employee name
	public String getEmpName() {
		return nameProperty.get();	
	}
	public void setEmpName(String name) {
		this.nameProperty.set(name);
	}
	public StringProperty getEmployeeName() {
		return nameProperty;	
	}
	//This is for employee username
	public Integer getEmpUser() {
		return usernameProperty.get();	
	}
	public void setEmpUser(int username) {
		this.usernameProperty.set(username);
	}
	public IntegerProperty getEmployeeUser() {
		return usernameProperty;	
	}
	//This is for employee password
	public Integer getEmpPass() {
		return passwordProperty.get();	
	}
	public void setEmpPass(int password) {
		this.passwordProperty.set(password);
	}
	public IntegerProperty getEmployeePass() {
		return passwordProperty;	
	}
	//This is for employee role
	public String getEmpRole() {
		return roleProperty.get();	
	}
	public void setEmpRole(String role) {
		this.roleProperty.set(role);
	}
	public StringProperty getEmployeeRole() {
		return roleProperty;	
	}
	//This is for employee email
	public String getEmpEmail() {
		return emailProperty.get();	
	}
	public void setEmpEmail(String email) {
		this.emailProperty.set(email);
	}
	public StringProperty getEmployeeEmail() {
		return emailProperty;	
	}
	
	

}
