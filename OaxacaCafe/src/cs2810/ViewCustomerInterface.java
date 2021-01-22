package cs2810;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;




public class ViewCustomerInterface extends Application{
  
  
  public static void main(String[] args) {
    launch(args);

  }
  
  MenuMain main = new MenuMain();
  

  
  @FXML
  private ListView<String> MainListView;

  @FXML
  private ListView<String> SidesListView;

  @FXML
  private ListView<String> DrinkListView;
  
  @FXML
  private Button StartButton;
  

  

  
  @FXML
  void StartButtonPressed(ActionEvent event) throws IOException {
    StartButton.setDisable(true);
    StartButton.setVisible(false);
    populateMenu();
    
  }

  
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
    Scene scene = new Scene(root, 400, 680);
    primaryStage.setScene(scene);
    primaryStage.show();
    
    URL url = this.getClass().getClassLoader().getResource("res/12025.mp3");
	  System.out.println(url.toExternalForm());
	  Media media = new Media(url.toExternalForm());
	  MediaPlayer mp = new MediaPlayer(media);
    Button b = (Button)root.lookup("#CallingButton");
    System.out.println(b.getText());
    
    b.setOnAction(new EventHandler<ActionEvent>()
    {
    	public void handle(ActionEvent event)
    	{
    		 mp.play();
    	}
    });
    
    
  }
  
  
  
  public void populateMenu() throws IOException{
    main.initialiseMainItems();
    main.initiliseDrinkItems();
    main.initialiseSideItems(); 
    
    //THIS BIT IS FOR ACTUALLY MAKING THE MENU ITEMS APPEAR ON SCREEN.
    
//    for (int i = 0; i < 8; i++) {
//      MainListView.getItems().add(main.mainItems[i].name);
//    }
    


    
    
  }
  

}
