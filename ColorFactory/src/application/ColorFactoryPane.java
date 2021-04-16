package application;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ColorFactoryPane extends BorderPane {
	private Button redButton, orangeButton, yellowButton;
	private RadioButton greenButton, blueButton, cyanButton;
	private ToggleGroup rbGroup;
	private TextField input;
	private Label instructions;
	private Text output;
	private FlowPane centerPane, topPane, bottomPane;
	private CheckBox boldCheckBox, italicCheckBox, fontCheckBox;
	private Font font;
	private VBox rightPane, leftPane;
	
	public ColorFactoryPane(){
		setupCenterPane();
		setupTopPane();
		setupBottomPane();
		setupRightPane();	
		setupLeftPane();
		
		this.setCenter(centerPane);
		this.setTop(topPane);
		this.setBottom(bottomPane);
		this.setRight(rightPane);
		this.setLeft(leftPane);
	}
	
	private void setupCenterPane(){
		font = new Font("Helvetica", 15);
		output = new Text("Buttons change panel color,\nradio buttons change text color,\ncheckboxes formates text input");
		output.setFont(font);
		output.setFill(Color.PURPLE);
		
		centerPane = new FlowPane(output);
		centerPane.setAlignment(Pos.CENTER);
	}
	
	private void setupTopPane(){
		instructions = new Label("Type something in and hit enter");
		input = new TextField();
		input.setOnAction(this::processAction);
		
		topPane = new FlowPane(instructions, input);
		topPane.setAlignment(Pos.CENTER);
		topPane.setHgap(10);
	}
	
	private void setupBottomPane(){
		boldCheckBox = new CheckBox("Bold");
		boldCheckBox.setOnAction(this::processAction);
		
		italicCheckBox = new CheckBox("Italic");
		italicCheckBox.setOnAction(this::processAction);
		
		fontCheckBox = new CheckBox("Change font size");
		fontCheckBox.setOnAction(this::processAction);
		
		bottomPane = new FlowPane(boldCheckBox, italicCheckBox, fontCheckBox);
		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.setHgap(10);
	}
	
	private void setupRightPane(){
		greenButton = new RadioButton("Green");
		greenButton.setStyle("-fx-text-fill: green");
		greenButton.setOnAction(this::processAction);
		
		blueButton = new RadioButton("Blue");
		blueButton.setStyle("-fx-text-fill: blue");
		blueButton.setOnAction(this::processAction);
		
		cyanButton = new RadioButton("Cyan");
		cyanButton.setStyle("-fx-text-fill: cyan");
		cyanButton.setOnAction(this::processAction);
		
		rbGroup = new ToggleGroup();
		greenButton.setToggleGroup(rbGroup);
		blueButton.setToggleGroup(rbGroup);
		cyanButton.setToggleGroup(rbGroup);
		
		rightPane = new VBox(greenButton, blueButton, cyanButton);
		rightPane.setSpacing(20);
		rightPane.setPadding(new Insets(20,20,10,10));
	}
	
	private void setupLeftPane(){
		redButton = new Button("Red");
		redButton.setStyle("-fx-background-color: red");
		redButton.setOnAction(this::processAction);
		
		orangeButton = new Button("Orange");
		orangeButton.setStyle("-fx-background-color: orange");
		orangeButton.setOnAction(this::processAction);
		
		yellowButton = new Button("Yellow");
		yellowButton.setStyle("-fx-background-color: yellow");
		yellowButton.setOnAction(this::processAction);
		
		leftPane = new VBox(redButton, orangeButton, yellowButton);
		leftPane.setSpacing(20);
		leftPane.setPadding(new Insets(20,20,10,10));
	}
	
	private void processAction(ActionEvent event){
		FontWeight myFontWeight = FontWeight.NORMAL;
		FontPosture myFontProsture = FontPosture.REGULAR;
		String myFontFamily = "Helvetica";
		int myFontSize = 25;
		
		//CheckBox Handlers
		if(boldCheckBox.isSelected())
			myFontWeight = FontWeight.BOLD;
		if(italicCheckBox.isSelected())
			myFontProsture = FontPosture.ITALIC;
		if(fontCheckBox.isSelected())
			myFontSize = 30;

		
		output.setFont(Font.font(myFontFamily, myFontWeight, myFontProsture, myFontSize));
		
		//RadioButton Handlers
		if(greenButton.isSelected()) {
			output.setFill(Color.GREEN);
			
		}else if(blueButton.isSelected()) {
			output.setFill(Color.BLUE);
			
		}else if(cyanButton.isSelected()) {
			output.setFill(Color.CYAN);
			
		}else {
			output.setFill(Color.PURPLE);
			
		}
		
		output.setText(input.getText());
		
		//Button Handlers
		if(event.getSource() == redButton) {
			centerPane.setStyle("-fx-background-color: red");
		}
		
		if(event.getSource() == orangeButton) {
			centerPane.setStyle("-fx-background-color: orange");
		}
		
		if(event.getSource() == yellowButton) {
			centerPane.setStyle("-fx-background-color: yellow");
		}
	}
}
