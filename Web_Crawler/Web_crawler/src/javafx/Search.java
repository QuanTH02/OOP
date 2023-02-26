package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Search extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("search.fxml")));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Search and Display");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
