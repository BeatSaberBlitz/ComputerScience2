package application;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import javafx.util.Duration;

public class WhackAMole extends Application {

	private HighScores[] topScores = new HighScores[10];
	private Timeline time;
	
	private Stage window;
	private Label timerLabel, scoreLabel;
	private TextField input;
	private Text whackAMoleText;
	private Text highScoreText;
	private Font whackAMoleFont;
	private Font highScoreFont;
	private Image whackAMoleImage, bombImage;
	private ImageView imageView, imageViewBomb;
	private Group mole, bomb;
	private Random randomGen;
	private FlowPane pane, topPane;
	private Scene scene1, scene2, scene3, scene4;
	
	private int score = 0;
	private final int STARTTIME = 30;
	private int seconds = STARTTIME;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		setupHighScores();
		Arrays.sort(topScores);
		
		whackAMoleFont = new Font("Comic Sans MS",40);
		whackAMoleText = new Text("Whack-a-mole!\nHit enter to play.\nPush tab to see high scores!\nClick on the mole to move it.\nWatch out for bombs!");
		whackAMoleText.setFont(whackAMoleFont);
		
		pane = new FlowPane(whackAMoleText);
		
		scene1 = new Scene(pane, 600,300, Color.PINK);
		scene1.setOnKeyPressed(this::processKeyPress);
		
		window.setTitle("Whack-a-mole");
		window.setScene(scene1);
		window.show();
		
	}

	public void processKeyPress(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER && event.getSource() == scene1) {
			try {
				whackAMoleImage = new Image("mole.png");
				bombImage = new Image("moddedBomb.png");
				imageView = new ImageView(whackAMoleImage);
				imageViewBomb = new ImageView(bombImage);
				
				imageView.setOnMouseClicked(this::processMouseClick);
				imageViewBomb.setOnMouseClicked(this::processMouseClick);
				
				mole = new Group(imageView);
				bomb = new Group(imageViewBomb);
				
				moveMole(mole, bomb);
				
				seconds = STARTTIME;
				
				timerLabel = new Label();
				timerLabel.setText(String.valueOf(seconds) + " Seconds Left");
				timerLabel.setFont(Font.font("Comic Sans MS", 30));
				timerLabel.setTextFill(Color.BLACK);
				
				scoreLabel = new Label();
				scoreLabel.setText("     Score: " + String.valueOf(score) + "\t\t");
				scoreLabel.setFont(Font.font("Comic Sans MS", 30));
				scoreLabel.setTextFill(Color.BLACK);
				
				topPane = new FlowPane(scoreLabel, timerLabel);
				topPane.setTranslateX(400);
				
				doTime();
				
				Group fullRoot = new Group(mole, bomb, topPane);
				
				scene2 = new Scene(fullRoot, 1000, 1000, Color.DARKGREEN);
				
				window.setScene(scene2);
				
			}catch(Exception e) {
				System.out.println("Image not found!");
			}
			
		}else if(event.getCode() == KeyCode.TAB && event.getSource() == scene1) {
			String highScoreBuffer = "Press Backspace to go back\nto the main menu\n";
			
			for (int index = 0; index < topScores.length; index++) {
				highScoreBuffer += (index + 1) + ". " + topScores[index].getIntials() + " " + topScores[index].getScore() + "\n";
			}
			
			highScoreText = new Text(highScoreBuffer);
			
			highScoreFont = new Font("Comic Sans MS", 18);
			highScoreText.setFont(highScoreFont);
			
			pane = new FlowPane(highScoreText);
			pane.setStyle("-fx-background-color: red");
			
			scene4 = new Scene(pane, 250, 300);
			scene4.setOnKeyPressed(this::processKeyPress);
			
			window.setScene(scene4);
			
		}else if(event.getCode() == KeyCode.BACK_SPACE && event.getSource() == scene4) {
			whackAMoleText.setText("Whack-a-mole!\nHit enter to play.\nPush tab to see high scores!\nClick on the mole to move it.\nWatch out for bombs!");
			
			pane = new FlowPane(whackAMoleText);
			pane.setStyle("-fx-background-color: pink");
			
			scene1 = new Scene(pane, 600, 300);
			scene1.setOnKeyPressed(this::processKeyPress);
			
			window.setScene(scene1);
		}
	}
	
	public void processEndTime() {
		whackAMoleText.setText("You whacked " + score + " moles!\nPlease enter you initials to save\nyour score!");
		input = new TextField();
		input.setOnAction(this::processLabel);
		
		pane = new FlowPane(whackAMoleText, input);
		pane.setStyle("-fx-background-color: aqua");
		pane.setPadding(new Insets(10,20,20,10));
		
		scene3 = new Scene(pane, 600,300);
		scene3.setOnKeyPressed(this::processKeyPress);
		window.setScene(scene3);
	}
	
	public void processMouseClick(MouseEvent event) {
		
		if(event.getSource() == imageViewBomb) {
			processEndTime();
			time.stop();
			score--;
		}
		
		moveMole(mole, bomb);
		score++;
		scoreLabel.setText("     Score: " + String.valueOf(score) + "\t\t");
		
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
	
	public void processLabel(ActionEvent event) {
		
		int placeHolder = -1;
		
		HighScores [] topScoresPlaceHolder = new HighScores[10];
		
		for (int index = 0; index < topScores.length; index++) {
			topScoresPlaceHolder[index] = topScores[index];
			if(topScores[index].getScore() < score)
				placeHolder = index;
		}
		
		if(placeHolder != -1) {
			for (int index = 0; index < topScores.length; index++) {
				if(index < placeHolder) {
					topScores[index] = topScoresPlaceHolder[index];
				}else if(index > placeHolder) {
					topScores[index] = topScoresPlaceHolder[index - 1];
				}
				else {
					topScores[index] = new HighScores(input.getText(), score);
				}
			}
		}
		Arrays.sort(topScores);
		
		try {
			FileWriter fw = new FileWriter("highScores.dat.txt", false);
			
			for (int index = 0; index < topScores.length; index++) {
				fw.write(topScores[index].getIntials() + "\n");
				fw.write(String.valueOf(topScores[index].getScore()) + "\n");
			}
			fw.close();
			
		}catch(Exception e) {
			System.out.println("Could not write in this file!");
		}
		
		whackAMoleText.setText("Whack-a-mole!\nHit enter to play.\nPush tab to see high scores!\nClick on the mole to move it.\nWatch out for bombs!");
		
		pane = new FlowPane(whackAMoleText);
		pane.setStyle("-fx-background-color: pink");
		
		scene1 = new Scene(pane, 600, 300);
		scene1.setOnKeyPressed(this::processKeyPress);
		
		score = 0;
		
		
		window.setScene(scene1);
	}
	
	public void setupHighScores() {
		try {
			Scanner infile = new Scanner(new File("highScores.dat.txt"));
			for (int index = 0; index < topScores.length; index++) {
				topScores[index] = new HighScores(infile.nextLine(), infile.nextInt());
				infile.nextLine();
			}
			infile.close();
			
		}catch(Exception e) {
			System.out.println("File not found!");
		}
	}
	
	public void doTime() {
		time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		
		if(time!=null) {
			time.stop();
		}
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				seconds--;
				
				timerLabel.setText(String.valueOf(seconds) + " Seconds Left");
				if(seconds<=0) {
					time.stop();
					processEndTime();
				}
			}
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}
	
	public void moveMole(Group mole, Group bomb) {
		randomGen = new Random();
		
		int translateMoleX = 0;
		int translateMoleY = 0;
		int translateBombX = 0;
		int translateBombY = 0;
		
		do {
			translateMoleX = randomGen.nextInt(701) + 100;
			translateMoleY = randomGen.nextInt(701) + 100;
			translateBombX = randomGen.nextInt(701) + 100;
			translateBombY = randomGen.nextInt(701) + 100;
		}while((translateBombX > translateMoleX - 259 && translateBombX < translateMoleX + 270) || (translateBombY > translateMoleY - 210 && translateBombY < translateMoleY + 182));
		
		mole.setTranslateX(translateMoleX);
		mole.setTranslateY(translateMoleY);
		bomb.setTranslateX(translateBombX);
		bomb.setTranslateY(translateBombY);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
