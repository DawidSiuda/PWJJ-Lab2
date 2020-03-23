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

	@Override
	public void start(Stage primaryStage) throws Exception {

			Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
	        primaryStage.setTitle("Lab2");

			primaryStage.setScene(new Scene(root, 730, 500));
	        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
