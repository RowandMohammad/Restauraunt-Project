package cs2810;

import java.net.URISyntaxException;
import java.sql.SQLException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class XCellDelete extends ListCell<String> {
  HBox hbox = new HBox();
  Label label = new Label("");
  Pane pane = new Pane();
  Button button = new Button("X");

  public XCellDelete(ViewCustomerInterface VCI) {
    super();

    hbox.getChildren().addAll(label, pane, button);
    HBox.setHgrow(pane, Priority.ALWAYS);
    button.setOnAction(event -> {
		try {
			VCI.delete(label.getText());
		} catch (URISyntaxException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});

  }

  @Override
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
