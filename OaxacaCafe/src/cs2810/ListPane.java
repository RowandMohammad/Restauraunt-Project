package cs2810;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
public class ListPane extends HBox {

    TextField name = new TextField();
    TextField price = new TextField();
    Button updateBtn = new Button("Update");
    private String ing[];
    String[] dietaryRequirements;

    @SuppressWarnings("unused")
    private ListPane() {

    }

    public ListPane(String _name, String _price, String[] ing, String[] dietaryRequirements) {
        super();
        this.ing = ing;
        this.name = new TextField(_name);
        this.price = new TextField(_price);
        this.dietaryRequirements = dietaryRequirements;

        new LoginMessage();
        Map<String, Object> map = LoginMessage.getMessage();
        if (map.isEmpty()) {
            price.setEditable(false);
            name.setEditable(false);
        }
        VBox lablesCotaier = new VBox(this.name, this.price );
        VBox buttonContainer = new VBox(this.updateBtn);
        lablesCotaier.setAlignment(Pos.BASELINE_LEFT);
        buttonContainer.setAlignment(Pos.CENTER);
        this.getChildren().addAll(lablesCotaier, buttonContainer);
        HBox.setHgrow(lablesCotaier, Priority.ALWAYS);
        setDeleteBtnActionListener();
    }

    private void setDeleteBtnActionListener() {
        // TODO Auto-generated method stub
        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String names = name.getText();
                String prices = price.getText();
                String[] ings = ing;
                if (names == null || prices == null) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("warn");
                    alert.setHeaderText(null);
                    alert.setContentText("please input right information");
                }
                try {
                    new MenuMain();
                    ArrayList<Menu_Item> mainItems = MenuMain.initialiseMainItems();
                    new MenuMain();
                    ArrayList<Menu_Item> drinkItems = MenuMain.initiliseDrinkItems();
                    new MenuMain();
                    ArrayList<Menu_Item> sideItems = MenuMain.initialiseSideItems();
                    for (Menu_Item item : mainItems) {
                        if (ings.equals(item.getIngredients())) {
                            item.setPrice(Double.parseDouble(prices.split("£")[1]));
                            item.setName(names);
                        }
                    }
                    for (Menu_Item item : drinkItems) {
                        if (ings.equals(item.getIngredients())) {
                            item.setPrice(Double.parseDouble(prices.split("£")[1]));
                            item.setName(names);
                        }
                    }
                    for (Menu_Item item : sideItems) {
                        if (ings.equals(item.getIngredients())) {
                            item.setPrice(Double.parseDouble(prices.split("£")[1]));
                            item.setName(names);
                        }
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }

    public String getName() {
        return name.getText();
    }


    public void setName(String name) {
        this.name.setText(name);
        ;
    }


    public TextField getPrice() {
        return price;
    }


    public void setPrice(TextField price) {
        this.price = price;
    }


    public String[] getIng() {
        return ing;
    }

    public void setIng(String[] ing) {
        this.ing = ing;
    }

}
