package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TetrisMain extends Application {
	
private static Stage window;

public static Stage getStage() {
	return window;
}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			window = primaryStage;
			Setup root = new Setup();
			root.setStyle("-fx-background-color: black");
			Scene scene = new Scene(root, 700, 900);
			window.setScene(scene);
			window.setTitle("Tetris!");
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
