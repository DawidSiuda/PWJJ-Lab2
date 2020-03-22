package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Main extends Application {

//	@FXML
//	Button addRecordButton;
//
//	Alert a;
//	EventHandler<ActionEvent> event;

	@Override
	public void start(Stage primaryStage) throws Exception {

			Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
	        primaryStage.setTitle("Lab2");

			primaryStage.setScene(new Scene(root, 730, 500));
	        primaryStage.show();

//			addRecordButton = new Button();
//			addRecordButton.setOnAction(event);
//
//			a = new Alert(AlertType.NONE);
//
//			event = new EventHandler<ActionEvent>() {
//		          public void handle(ActionEvent e)
//		          {
//		        	  a.setAlertType(AlertType.CONFIRMATION);
//		        	  a.show();
//		          }
//		      };



//			//BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();

	}

	//  EventHandler<ActionEvent>

	public static void main(String[] args) {
		launch(args);
	}
}
