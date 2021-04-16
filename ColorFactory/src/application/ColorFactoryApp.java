package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ColorFactoryApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ColorFactoryPane root = new ColorFactoryPane();
		Scene scene = new Scene(root, 425, 225);
		primaryStage.setTitle("Color Factory");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
