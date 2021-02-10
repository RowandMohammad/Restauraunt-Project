package cs2810;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class CancelOrder extends ListCell<String> {
	HBox hbox = new HBox();
	Label label = new Label("");
	Pane pane = new Pane();
	Button button = new Button("Cancel Order");

	public CancelOrder() {
		super();

		hbox.getChildren().addAll(label, pane, button);
		HBox.setHgrow(pane, Priority.ALWAYS);
		button.setOnAction(event -> cancelConfirmation());

	}

	void cancelConfirmation() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Cancel");
		alert.setContentText("Are you sure you want to cancel this order?");
		ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(yesButton, cancelButton);
		alert.showAndWait().ifPresent(type -> {
			if (type == yesButton) {
				getListView().getItems().remove(getItem());
				
			} else {
			}
		});
	}

	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		setText(null);
		setGraphic(null);

		if (item != null && !empty) {
			label.setText(item);
			setGraphic(hbox);
		}
	}

}
