package application;

import java.io.File;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WhackAMoleOG extends Application {
	
	private Stage window;
	private Text whackAMoleText;
	private Font whackAMoleFont;
	private Image whackAMoleImage;
	private ImageView imageView;
	private Group mole;
	private Random randomGen;
	private FlowPane pane;
	private Scene scene1, scene2;
	
	private int score = 0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		whackAMoleFont = new Font("Comic Sans MS",40);
		whackAMoleText = new Text("Whack-a-mole!\nHit enter to play.\nClick on the mole to move it.\nPress escape to quit");
		whackAMoleText.setFont(whackAMoleFont);
		
		pane = new FlowPane(whackAMoleText);
		
		scene1 = new Scene(pane, 600,300, Color.PINK);
		scene1.setOnKeyPressed(this::processKeyPress);
		
		window.setTitle("Whack-a-mole");
		window.setScene(scene1);
		window.show();
		
	}

	public void processKeyPress(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			try {
				whackAMoleImage = new Image("mole.png");
				imageView = new ImageView(whackAMoleImage);
				
				imageView.setOnMouseClicked(this::processMouseClick);
				
				mole = new Group(imageView);
				
				moveMole(mole);
							
				scene2 = new Scene(mole, 1000, 1000, Color.DARKGREEN);
				scene2.setOnKeyPressed(this::processKeyPress);
				
				window.setScene(scene2);
				
			}catch(Exception e) {
				System.out.println("Image not found!");
			}
			
		}else if(event.getCode() == KeyCode.ESCAPE) {
			whackAMoleText.setText("You whacked " + score + " moles!");
			
			window.setScene(scene1);
		}
	}
	
	public void processMouseClick(MouseEvent event) {
		
		moveMole(mole);
		score++;
		
		try {
			File whackAMoleSound = new File("pop.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(whackAMoleSound);
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioInputStream);
			audioClip.start();
			
		}catch(Exception e) {
			System.out.println("Could not find sound file!");
		}
	}
	
	public void moveMole(Group mole) {
		randomGen = new Random();
		
		mole.setTranslateX(randomGen.nextInt(801));
		mole.setTranslateY(randomGen.nextInt(801));
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
