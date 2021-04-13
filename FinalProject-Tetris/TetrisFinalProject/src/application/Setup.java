package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Tiernan Meyer
 * @version 1.0.0
 * @date 4/24/2020
 */

public class Setup extends BorderPane {
	private AnchorPane startPane, titlePane, gamePane, scoresPane, initialsPane, losePane;
	private Color[] blockColors = { Color.BLACK, Color.LIGHTBLUE, Color.PURPLE, Color.DARKBLUE, Color.ORANGE, Color.RED,
			Color.GREEN, Color.YELLOW };
	private Button play, scores, startButton;
	private Text start, tetris, controlsText, lineText, topScoreText, scoreText, nextText, levelText, highScoresTitle,
			nameText, scoresText, scoresList, namesList, leave, topTen, enterInitials, loseText, pressEnter;
	private Font startFont, titleFont, buttonFont, gameFont, scoresFont, initialsFont, loseFont;
	private Scene titleScreen, gameScreen, scoreScreen, initialsScreen, loseScreen;
	private Rectangle lineRect, scoreRect, nextRect, levelRect;
	private StackPane linePane, scorePane, nextPane, levelPane;
	private BorderPane game, highScores, title, initialsBPane, lose;
	private GridPane gameBoard, nextBlock;
	private Stage window;
	private Blocks tetrisBackEnd;
	private Calculations calc;
	private int score, level, lines, topScore, fileLength;
	private String[] namesArray;
	private int[] scoresArray;
	private HighScore[] highScoresArray;
	private boolean isGreater = false;
	private TextField textField;
	private StringBuilder initials;
	private Media tetrisSong;
	private MediaPlayer mp;
	
	/**
	 * Sets up the start window of the game
	 */
	public Setup() {
		window = TetrisMain.getStage();
		setStartScreen();
	}

	/**
	 *  Method to set up the screen that tells the user to press ENTER to move on to
	 *the title screen
	 */
	private void setStartScreen() {
		try {
			startPane = new AnchorPane();
			start = new Text("Click the button\nbelow to start");
			startFont = new Font("Courier", 60);
			start.setFont(startFont);
			start.setFill(Color.WHITE);
			startButton = new Button("START");
			startButton.setFont(startFont);
			startButton.setOnAction(this::processAction);
			startButton.setStyle("-fx-text-fill: white; " + "-fx-background-color: black;" + "-fx-border-color: white;"
					+ "-fx-border-width: 10;");
			startPane.getChildren().addAll(start, startButton);
			AnchorPane.setTopAnchor(start, 250.0);
			AnchorPane.setLeftAnchor(start, 130.0);
			AnchorPane.setBottomAnchor(startButton, 150.0);
			AnchorPane.setLeftAnchor(startButton, 225.0);
			setCenter(startPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the title screen, allows the user to select to go to the game
	 * screen or score screen
	 */
	private void setTitleScreen() {
		try {
			titlePane = new AnchorPane();
			titlePane.setMaxSize(700, 900);
			titleFont = Font.font("Courier", 80);
			buttonFont = Font.font("Courier", 55);
			tetris = new Text("TETRIS!");
			play = new Button("PLAY");
			controlsText = new Text("ARROW KEYS - Move blocks\nW R - Rotate Blocks");
			play.setOnAction(this::processAction);
			scores = new Button("SCORES");
			scores.setOnAction(this::processAction);
			tetris.setFont(titleFont);
			tetris.setFill(Color.WHITE);
			controlsText.setFont(buttonFont);
			controlsText.setFill(Color.WHITE);
			play.setFont(buttonFont);
			play.setStyle("-fx-text-fill: white; "
					+ "-fx-background-color: black;"
					+ "-fx-border-color: white;"
					+ "-fx-border-width: 10;");
			
			scores.setFont(buttonFont);
			scores.setStyle("-fx-text-fill: white; "
					+ "-fx-background-color: black;"
					+ "-fx-border-color: white;"
					+ "-fx-border-width: 10;");
			
			titlePane.getChildren().addAll(tetris, play, scores, controlsText);
			AnchorPane.setTopAnchor(tetris, 150.0);
			AnchorPane.setLeftAnchor(tetris, 200.0);
			AnchorPane.setBottomAnchor(play, 150.0);
			AnchorPane.setLeftAnchor(play, 60.0);
			AnchorPane.setBottomAnchor(scores, 150.0);
			AnchorPane.setRightAnchor(scores, 60.0);
			AnchorPane.setTopAnchor(controlsText, 350.0);
			AnchorPane.setLeftAnchor(controlsText, 15.0);
			title = new BorderPane(titlePane);
			title.setStyle("-fx-background-color: black");
			titleScreen = new Scene(title, 700, 900);
			window.setScene(titleScreen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the game screen, where the user will play "Tetris"
	 */
	private void setGameScreen() {
		try {
			tetrisSong = new Media(new File("Tetris.mp3").toURI().toString());
			mp = new MediaPlayer(tetrisSong);
			mp.setStartTime(Duration.seconds(0));
			mp.setStopTime(Duration.seconds(77));
			mp.setVolume(0.4);
			mp.setCycleCount(MediaPlayer.INDEFINITE);
			mp.play();
			/**
			 * "Tetris.mp3" obtained from https://archive.org/details/TetrisThemeMusic on 5/6/2020
			 * Based off the song Korobeiniki by Nikolai Nekrasov
			 */
			
			getHighScores();
			tetrisBackEnd = new Blocks();
			gameBoard = new GridPane();
			gameBoard.setPrefSize(350, 700);
			gameBoard.setStyle("-fx-background-color: black;" + "-fx-border-color: white;" + "-fx-border-width: 10;");
			gamePane = new AnchorPane();
			gamePane.setMaxSize(700, 900);
			calc = new Calculations();
			score = calc.getScore();
			level = calc.getCurrentLevel();
			lines = calc.getAmtLines();

			gameFont = Font.font("Courier", 40);
			lineText = new Text("LINES-" + lines);
			lineText.setFont(gameFont);
			lineText.setFill(Color.WHITE);
			topScoreText = new Text("TOP SCORE\n" + topScore);
			topScoreText.setFont(gameFont);
			topScoreText.setFill(Color.WHITE);
			scoreText = new Text("SCORE\n" + score);
			scoreText.setFont(gameFont);
			scoreText.setFill(Color.WHITE);
			nextText = new Text("NEXT");
			nextText.setFont(gameFont);
			nextText.setFill(Color.WHITE);
			levelText = new Text("LEVEL-" + level);
			levelText.setFont(gameFont);
			levelText.setFill(Color.WHITE);

			lineRect = new Rectangle(360, 100);
			lineRect.setFill(Color.BLACK);
			lineRect.setStrokeWidth(10.0);
			lineRect.setStroke(Color.WHITE);
			scoreRect = new Rectangle(225, 250);
			scoreRect.setFill(Color.BLACK);
			scoreRect.setStrokeWidth(10.0);
			scoreRect.setStroke(Color.WHITE);
			nextRect = new Rectangle(225, 150);
			nextRect.setFill(Color.BLACK);
			nextRect.setStrokeWidth(10.0);
			nextRect.setStroke(Color.WHITE);
			levelRect = new Rectangle(225, 100);
			levelRect.setFill(Color.BLACK);
			levelRect.setStrokeWidth(10.0);
			levelRect.setStroke(Color.WHITE);

			nextBlock = new GridPane();
			nextBlock.setPrefSize(140, 35);
			linePane = new StackPane();
			linePane.getChildren().addAll(lineRect, lineText);
			scorePane = new StackPane();
			scorePane.getChildren().addAll(scoreRect, topScoreText, scoreText);
			StackPane.setAlignment(topScoreText, Pos.TOP_LEFT);
			StackPane.setMargin(topScoreText, new Insets(10, 10, 10, 10));
			StackPane.setAlignment(scoreText, Pos.BOTTOM_LEFT);
			StackPane.setMargin(scoreText, new Insets(10, 10, 10, 10));
			nextPane = new StackPane();
			nextPane.getChildren().addAll(nextRect, nextBlock, nextText);
			StackPane.setAlignment(nextText, Pos.TOP_CENTER);
			nextBlock.setAlignment(Pos.CENTER);

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 2; j++) {
					Rectangle block = new Rectangle(34, 34);
					block.setStrokeWidth(1);
					block.setStroke(Color.WHITE);
					block.setFill(Color.BLACK);
					nextBlock.add(block, i, j);
				}
			}

			levelPane = new StackPane();
			levelPane.getChildren().addAll(levelRect, levelText);

			changeBoardDisplay();
			changeNextBlock();
			doTime();

			gamePane.getChildren().addAll(linePane, scorePane, nextPane, levelPane, gameBoard);
			AnchorPane.setTopAnchor(linePane, 25.0);
			AnchorPane.setLeftAnchor(linePane, 25.0);
			AnchorPane.setTopAnchor(scorePane, 75.0);
			AnchorPane.setRightAnchor(scorePane, 25.0);
			AnchorPane.setTopAnchor(nextPane, 400.0);
			AnchorPane.setRightAnchor(nextPane, 25.0);
			AnchorPane.setBottomAnchor(levelPane, 175.0);
			AnchorPane.setRightAnchor(levelPane, 25.0);
			AnchorPane.setBottomAnchor(gameBoard, 25.0);
			AnchorPane.setLeftAnchor(gameBoard, 25.0);
			game = new BorderPane(gamePane);
			game.setCenter(gamePane);
			game.setStyle("-fx-background-color: black");
			gameScreen = new Scene(game, 700, 900);
			gameScreen.setOnKeyPressed(this::processKeyPress);
			window.setScene(gameScreen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Changes the display of the board to match what is going on in the back end
	 */
	private void changeBoardDisplay() {

		int[][] tempArray;

		tempArray = tetrisBackEnd.getTetrisGridBackground();

		for (int row = 0; row < tempArray.length; row++) {
			for (int col = 0; col < tempArray[row].length; col++) {
				Rectangle block = new Rectangle(34, 34);
				block.setFill(blockColors[Math.abs(tempArray[row][col])]);
				block.setStrokeWidth(1.0);
				block.setStroke(Color.DARKGREY);
				gameBoard.add(block, col, row);
			}
		}
	}

	/**
	 * Changes the next block display to match what is going on in the back end
	 */
	private void changeNextBlock() {
		int[][] tempArray;

		tempArray = tetrisBackEnd.getNextBlockGridBackground();

		for (int row = 0; row < tempArray.length; row++) {
			for (int col = 0; col < tempArray[row].length; col++) {
				Rectangle block = new Rectangle(34, 34);
				block.setFill(blockColors[Math.abs(tempArray[row][col])]);
				if (tempArray[row][col] != 0) {
					block.setStrokeWidth(1.0);
					block.setStroke(Color.DARKGREY);
				}else {
					block.setStroke(Color.BLACK);
				}
				nextBlock.add(block, col, row);
			}
		}
	}
	
	/**
	 * Method to set the initial screen for if the player got a new top 10 score
	 */
	private void setInitialsScreen() {
		initialsPane = new AnchorPane();
		initialsPane.setMaxSize(700.0, 900.0);
		topTen = new Text("New Top 10 Score!");
		topTen.setFont(startFont);
		topTen.setFill(Color.WHITE);
		enterInitials = new Text("Enter you initals");
		enterInitials.setFont(startFont);
		enterInitials.setFill(Color.WHITE);
		initialsFont = new Font("Courier", 100);
		textField = new TextField();
		textField.setPrefHeight(300);
		textField.setPrefWidth(400);
		textField.setFont(initialsFont);
		textField.setStyle("-fx-text-fill: white; " + "-fx-background-color: black;" + "-fx-border-color: white;"
					+ "-fx-border-width: 10;");
		textField.setOnAction(this::processAction);
		initialsPane.getChildren().addAll(topTen, enterInitials, textField);
		AnchorPane.setTopAnchor(topTen, 25.0);
		AnchorPane.setLeftAnchor(topTen, 100.0);
		AnchorPane.setTopAnchor(enterInitials, 90.0);
		AnchorPane.setLeftAnchor(enterInitials, 100.0);
		AnchorPane.setTopAnchor(textField, 350.0);
		AnchorPane.setLeftAnchor(textField, 150.0);
		initialsBPane = new BorderPane(initialsPane);
		initialsBPane.setStyle("-fx-background-color: black");
		initialsScreen = new Scene(initialsBPane, 700, 900);
		window.setScene(initialsScreen);
	}
	
	/**
	 * Method to add the initials and score to highScores.dat.txt for if the player got a new top 10 score 
	 */
	private void addTopScore() {
		try {
		   FileWriter fr = new FileWriter("highScores.dat.txt", true);
		   fr.write("\n" + initials + "\n" + score);
		   fr.close();
		}catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * Method to set the lose screen for if the player didn't get a top 10 score
	 */
	private void setLoseScreen() {
		losePane = new AnchorPane();
		losePane.setMaxSize(700, 900);
		loseFont = new Font("Courier", 100);
		loseText = new Text("GAME OVER");
		loseText.setFont(loseFont);
		loseText.setFill(Color.WHITE);
		pressEnter = new Text("Press ENTER to continue");
		pressEnter.setFont(gameFont);
		pressEnter.setFill(Color.WHITE);
		losePane.getChildren().addAll(loseText, pressEnter);
		AnchorPane.setTopAnchor(loseText, 250.0);
		AnchorPane.setLeftAnchor(loseText, 75.0);
		AnchorPane.setBottomAnchor(pressEnter, 100.0);
		AnchorPane.setLeftAnchor(pressEnter, 125.0);
		lose = new BorderPane(losePane);
		lose.setStyle("-fx-background-color: black");
		loseScreen = new Scene(lose, 700, 900);
		window.setScene(loseScreen);
		loseScreen.setOnKeyPressed(this::processKeyPress);
	}
	
	/**
	 *  Method to set the score screen, which reads in a .txt file to get the noted
	 * down high scores
	 */
	private void setScoreScreen() {
		try {
			getHighScores();
			
			String nameString = new String();
			for (int i = 0; i < 10; i++) {
				nameString += namesArray[i] + "\n";
			}
			String scoreString = new String();
			for (int i = 0; i < 10; i++) {
				scoreString += scoresArray[i] + "\n";
			}

			scoresFont = new Font("Courier", 40);
			scoresPane = new AnchorPane();
			scoresPane.setMaxSize(700, 900);
			highScoresTitle = new Text("High Scores");
			highScoresTitle.setFont(titleFont);
			highScoresTitle.setFill(Color.WHITE);
			nameText = new Text("Name");
			nameText.setFont(buttonFont);
			nameText.setFill(Color.WHITE);
			scoresText = new Text("Scores");
			scoresText.setFont(buttonFont);
			scoresText.setFill(Color.WHITE);
			namesList = new Text(nameString);
			namesList.setFont(scoresFont);
			namesList.setFill(Color.WHITE);
			scoresList = new Text(scoreString);
			scoresList.setFont(scoresFont);
			scoresList.setFill(Color.WHITE);
			leave = new Text("Press ESC to exit");
			leave.setFont(scoresFont);
			leave.setFill(Color.WHITE);
			scoresPane.getChildren().addAll(highScoresTitle, nameText, scoresText, namesList, scoresList, leave);
			AnchorPane.setTopAnchor(highScoresTitle, 25.0);
			AnchorPane.setLeftAnchor(highScoresTitle, 150.0);
			AnchorPane.setTopAnchor(nameText, 150.0);
			AnchorPane.setLeftAnchor(nameText, 100.0);
			AnchorPane.setTopAnchor(scoresText, 150.0);
			AnchorPane.setLeftAnchor(scoresText, 400.0);
			AnchorPane.setTopAnchor(namesList, 225.0);
			AnchorPane.setLeftAnchor(namesList, 100.0);
			AnchorPane.setTopAnchor(scoresList, 225.0);
			AnchorPane.setLeftAnchor(scoresList, 400.0);
			AnchorPane.setBottomAnchor(leave, 25.0);
			AnchorPane.setLeftAnchor(leave, 200.0);
			highScores = new BorderPane(scoresPane);
			highScores.setStyle("-fx-background-color: black");
			scoreScreen = new Scene(highScores, 700, 900);
			window.setScene(scoreScreen);
			scoreScreen.setOnKeyPressed(this::processKeyPress);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processAction(ActionEvent event) {
		if (event.getSource() == play) {
			setGameScreen();
		} else if (event.getSource() == scores) {
			setScoreScreen();
		} else if (event.getSource() == startButton) {
			setTitleScreen();
		}
		if (event.getSource() == textField) {
			initials = new StringBuilder(textField.getText());
			initials.setLength(3);
			addTopScore();
			setScoreScreen();
		}
	}

	/**
	 * Processes the input commands from the keyboard for the program
	 * @param event
	 */
	private void processKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.DOWN) {
			int tempNumKey = tetrisBackEnd.BlockMoveDown();
			
			if(tempNumKey == 1) {
				calculateScore();
			}else if(tempNumKey == 2) {
				mp.stop();
				endGame();
			}
			changeBoardDisplay();
			changeNextBlock();
		}

		if (event.getCode() == KeyCode.RIGHT) {
			tetrisBackEnd.BlockMoveRight();
			changeBoardDisplay();
		}

		if (event.getCode() == KeyCode.LEFT) {
			tetrisBackEnd.BlockMoveLeft();
			changeBoardDisplay();
		}

		if (event.getCode() == KeyCode.R) {
			tetrisBackEnd.RotateBlockKey(true);
			changeBoardDisplay();
		}

		if (event.getCode() == KeyCode.W) {
			tetrisBackEnd.RotateBlockKey(false);
			changeBoardDisplay();
		}
		if (event.getCode() == KeyCode.ESCAPE) {
			if (window.getScene() == scoreScreen) {
				setTitleScreen();
			}
		}
		if (event.getCode() == KeyCode.ENTER) { 
			if (window.getScene() == loseScreen) {
				setScoreScreen();
			}
		}
	}
	
	/**
	 * Method to check if the player's score was better than any of the top 10,
	 * and go to initials screen if it was greater or lose screen if not
	 */
	private void endGame() {
		getHighScores();
		int i = 0;
		while(isGreater == false && i < 10) {
			if (score > scoresArray[i]) {
				isGreater = true;
			}
			else isGreater = false;
			i++;
		}
		if (isGreater == true) {
			setInitialsScreen();
		}
		else if (isGreater == false) {
			setLoseScreen();
		}
	}
	
	/**
	 * Method to read in the high scores and set values to be used elsewhere in Setup
	 */
	public void getHighScores() {
		try {
		fileLength = (int) new File("highScores.dat.txt").length();
		fileLength = fileLength/2;
		namesArray = new String[fileLength];
		scoresArray = new int[fileLength];
		int nameCount = 0;
		int scoreCount = 0;
		Scanner s = new Scanner(new FileReader("highScores.dat.txt"));
		int readCount = 0;
		while (s.hasNext()) {
			if (readCount % 2 == 0) {
				namesArray[nameCount] = s.nextLine();
				nameCount++;
			} else {
				String temp = s.nextLine();
				scoresArray[scoreCount] = Integer.parseInt(temp);
				scoreCount++;
			}
			readCount++;
		}
		highScoresArray = new HighScore[fileLength];
		for (int i = 0; i < fileLength; i++) {
			highScoresArray[i] = new HighScore(namesArray[i], scoresArray[i]);
		}
		Arrays.sort(highScoresArray);
		for (int i = 0; i < 10; i++) {
			namesArray[i] = highScoresArray[i].getInitials();
			scoresArray[i] = highScoresArray[i].getScore();
		}
		topScore = scoresArray[0];
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Calculates the current score for the player and updates those fields accordingly
	 */
	public void calculateScore() {
		calc.AddToScore(tetrisBackEnd.getNumRowsDeleted() - calc.getAmtLines());
		score = calc.getScore();
		level = calc.getCurrentLevel();
		lines = calc.getAmtLines();
		
		scoreText.setText("SCORE\n" + score);
		levelText.setText("LEVEL - " + level);
		lineText.setText("LINES - " + lines);
	}
	
	/**
	 * Timer that makes the block go down at a consistent speed that gets faster as the game goes on
	 */
	public void doTime() {
		Timeline time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		
		if(time!=null) {
			time.stop();
		}
		KeyFrame frame = new KeyFrame(Duration.seconds(calc.getSpeedOfBlocks()), new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				int tempNumKey = tetrisBackEnd.BlockMoveDown();
				
				if(tempNumKey == 1) {
					calculateScore();
				}else if(tempNumKey == 2) {
					mp.stop();
					time.stop();
					endGame();
				}
				changeBoardDisplay();
				changeNextBlock();
			}
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}
}
