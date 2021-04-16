package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A GUI with several common logos
 * @author Tyler Marefke
 * @version 2020.03.30
 */
public class Logos extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//MasterCard Logo
		Circle redCircle = new Circle(85 , 75, 50);
		redCircle.setFill(Color.RED);
		
		Circle orangeCircle = new Circle(145 , 75, 50);
		orangeCircle.setFill(Color.ORANGE);
		
		Text masterCardText = new Text(45, 85, "MasterCard");
		
		Font masterCardFont = Font.font("Times", FontPosture.ITALIC, 28);
		masterCardText.setFont(masterCardFont);
		masterCardText.setFill(Color.WHITE);
		
		Group masterCard = new Group(redCircle, orangeCircle, masterCardText);
		masterCard.setTranslateY(200);
		
		//----------------------------------------------------------------------------------------------------
		//VisaCard Logo
		Rectangle whiteRectangle = new Rectangle(0,0,150,100);
		whiteRectangle.setFill(Color.WHITE);
		
		Rectangle blueRectangle = new Rectangle(0,0,150,30);
		blueRectangle.setFill(Color.BLUE);
		
		Rectangle orangeRectangle = new Rectangle(0,70,150,30);
		orangeRectangle.setFill(Color.ORANGE);
		
		Text visaText = new Text(33,65,"VISA");
		Font visaFont = Font.font("Times", FontWeight.BOLD, FontPosture.ITALIC, 40);
		visaText.setFont(visaFont);
		visaText.setFill(Color.BLUE);
		
		Group visaCard = new Group(whiteRectangle, blueRectangle, orangeRectangle, visaText);
		visaCard.setTranslateX(300);
		visaCard.setTranslateY(350);
		
		//----------------------------------------------------------------------------------------------------
		//Wal-mart Logo
		Text walmartText = new Text(0,54,"Walmart");
		Font walmartFont = new Font("Courier", 50);
		walmartText.setFont(walmartFont);
		walmartText.setFill(Color.CORNFLOWERBLUE);
		
		Text walmartLiveText = new Text(0,75,"Save money. Live better.");
		Font walmartLiveFont = new Font("Courier", 18);
		walmartLiveText.setFont(walmartLiveFont);
		walmartLiveText.setFill(Color.CORNFLOWERBLUE);
		
		Line line1 = new Line(250,5,250,20);
		line1.setStroke(Color.ORANGE);
		line1.setStrokeWidth(6);
		
		Line line2 = new Line(250,50,250,65);
		line2.setStroke(Color.ORANGE);
		line2.setStrokeWidth(6);
		
		Line line3 = new Line(250,20,250,35);
		line3.setStroke(Color.ORANGE);
		line3.setStrokeWidth(6);
		line3.setRotate(60);
		line3.setTranslateX(20);
		line3.setTranslateY(-3);
		
		Line line4 = new Line(250,50,250,65);
		line4.setStroke(Color.ORANGE);
		line4.setStrokeWidth(6);
		line4.setRotate(120);
		line4.setTranslateX(20);
		line4.setTranslateY(-10);
		
		Line line5 = new Line(250,20,250,35);
		line5.setStroke(Color.ORANGE);
		line5.setStrokeWidth(6);
		line5.setRotate(-60);
		line5.setTranslateX(-20);
		line5.setTranslateY(-3);
		
		Line line6 = new Line(250,50,250,65);
		line6.setStroke(Color.ORANGE);
		line6.setStrokeWidth(6);
		line6.setRotate(-120);
		line6.setTranslateX(-20);
		line6.setTranslateY(-10);
		
		Group flower = new Group(line1, line2, line3, line4, line5, line6);
		flower.setTranslateX(-25);
		
		Group walmart = new Group(walmartText, walmartLiveText, flower);
		walmart.setTranslateX(240);
		walmart.setTranslateY(100);
		
		//--------------------------------------------------------------------------------------------------------
		//Amazon Logo
		Text amazonText = new Text(0,54,"amazon");
		Font amazonFont = Font.font("Courier",FontWeight.BOLD,46);
		amazonText.setFont(amazonFont);
		
		Arc arc1 = new Arc(70,10,80,60,240,50);
		arc1.setStroke(Color.ORANGE);
		arc1.setStrokeWidth(4);
		arc1.setFill(null);
		
		Arc arc2 = new Arc(100,65,20,6,60,65);
		arc2.setFill(null);
		arc2.setStroke(Color.ORANGE);
		arc2.setStrokeWidth(4);
		
		Arc arc3 = new Arc(90,60,20,25,320,40);
		arc3.setFill(null);
		arc3.setStroke(Color.ORANGE);
		arc3.setStrokeWidth(4);
		
		Group arrow = new Group(arc1, arc2, arc3);
		arrow.setTranslateX(-10);
		
		Group amazon = new Group(amazonText, arrow);
		amazon.setTranslateX(20);
		amazon.setTranslateY(20);
		
		//----------------------------------------------------------------------------------------------------------
		//Final grouping and scene set up
		
		Group root = new Group(masterCard, visaCard, walmart, amazon);
		
		Scene scene = new Scene(root, 500, 500, Color.ANTIQUEWHITE);
		
		primaryStage.setTitle("Logos");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
