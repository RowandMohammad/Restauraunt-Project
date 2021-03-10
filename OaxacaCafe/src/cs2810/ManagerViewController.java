package cs2810;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ManagerViewController {
    private ViewCustomerInterface parent;

    @FXML
    private Button BackToOrdering;

    @FXML
    private Label UserLabel;

    @FXML
    private Button viewEmployee;

    @FXML
    void BackToOrderingPressed(ActionEvent event) {
    	((Stage) BackToOrdering.getScene().getWindow()).close();

    }
     @FXML
    void changethemenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ChangeMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets()
            .add(getClass().getClassLoader().getResource("styling/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        new LoginMessage();
		LoginMessage.getMessage().put("Login", "successful");
    }

    @FXML
    void handleViewEmployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EmployeeView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }

}
