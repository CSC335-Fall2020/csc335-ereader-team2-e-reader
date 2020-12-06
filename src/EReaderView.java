/**
 * @author chelseybergmann, chloed
 * File: EReaderView.java
 * Project: Final Project - E-Reader
 * Purpose: This class is the gui view that displays the reader.
 **/



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EReaderView extends Application implements Observer {
	BorderPane pane;
	EReaderController controller;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		this.pane = new BorderPane();
		
		// initialize the model and controller
		EReaderModel model = new EReaderModel();
		
		//Adds this class object as it's observer
		model.addObserver(this);
		this.controller = new EReaderController(model);
		
		
		// Adds book to the model
		this.controller.addBook ("Books/salembelle.txt");
		Page page = this.controller.getPage ();
		
		//Displays the Current Book Heading
		displayHeading ();

		// TO DO: EVENT HANDLER FOR CHANGING FONT / SIZE
		
		// Displays a page with a given font size
		displayPage(page, "Times New Roman", 12, pane);
		
		// display the user's options on the side 
		// entered info is stored in font and fontSize text fields( index 1 and 3)
		HBox fonts = sideOptions(page, pane);
		
		// Set and display scene.
		setScene(this.pane, stage); 
	}
	
	private void displayHeading () {
		// create heading 
		System.out.println ("This is title :"+this.controller.getTitle());
		String titleAndAuthor = this.controller.getTitle() + " by " + this.controller.getAuthor();
		Label heading = new Label();
		heading.setText(titleAndAuthor);
		heading.setAlignment(Pos.CENTER);
		pane.setTop(heading);
		pane.setAlignment(heading, Pos.CENTER);
		
	}
	
	private void displayPage(Page page, String fontType, int fontSize, BorderPane pane) {
		// get the list of pages
		Label currPage = new Label();
		String pageText = page.getContent(); //Gets content from page object

		// add an outer loop to create a new screen for each page
		
		
		currPage.setText(pageText);
		currPage.setFont(new Font(fontType, fontSize));
		pane.setCenter(currPage);
	}
	
	private HBox sideOptions(Page page, BorderPane pane) throws FileNotFoundException {
		
		VBox sidePanel = new VBox ();
		
		HBox fonts = new HBox();	
		
		Label fontL = new Label("Font:  ");
		TextField font = new TextField();
		
		font.setMaxWidth(30);
		Label fontS = new Label("Font Size:  ");
		
		TextField fontSize = new TextField();
		fontSize.setMaxWidth(30);

		
		
		HBox pageArrows = new HBox ();
		
		//Creates Right Arrow Button
        FileInputStream input = new FileInputStream("Images/rightArrowButton.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight( 20);
        imageView.setFitWidth( 20);
        
		Button nextPageButton = new Button("Next Page", imageView);

		nextPageButton.setOnAction( event -> {

			Page currentPage = this.controller.getPage ();
			int pageNumber = currentPage.getPageNumber();
			pageNumber++;//Go to the next Page;
			
			this.controller.goToPage(pageNumber);
		});
		
		
		//Creates Left Arrow Button
        FileInputStream input2 = new FileInputStream("Images/leftArrowButton.png");
        Image image2 = new Image(input2);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight( 20);
        imageView2.setFitWidth( 20);
        
		Button prevPageButton = new Button("Next Page", imageView2);
		
		
		prevPageButton.setOnAction( event -> {

			Page currentPage = this.controller.getPage ();
			int pageNumber = currentPage.getPageNumber();
			pageNumber--;//Go to previous page;
			
			this.controller.goToPage(pageNumber);
		});
		
		pageArrows.getChildren().addAll( prevPageButton, nextPageButton );
		pageArrows.setSpacing(10);
				
		fonts.getChildren().addAll(fontL, font, fontS, fontSize);
		fonts.setSpacing(10);
		// entered info is stored in font and fontSize text fields( index 1 and 3)
		
		font.setOnAction((event) -> { // Change font.
			String fontSizeStr = fontSize.getText();
			int fontSizeInt = Integer.valueOf(fontSizeStr);
			String fontType = font.getText();
			
			displayPage(page, fontType, fontSizeInt, pane);
			
		});
		
		fontSize.setOnAction((event) -> { // Change font size.
			// Use this code to get the user entered font size when filled in
			String fontSizeStr = fontSize.getText();
			int fontSizeInt = Integer.valueOf(fontSizeStr);
			String fontType = font.getText();
			
			displayPage(page, fontType, fontSizeInt, pane);
			
		});
		
		sidePanel.getChildren().addAll(fonts,pageArrows );
		sidePanel.setSpacing(10);
		
		pane.setLeft(sidePanel);
		return fonts;
	}
	
	private void setScene(BorderPane pane, Stage stage) {
		Scene scene = new Scene(pane, 900, 600);    
		stage.setTitle("E-Reader GUI View");   
		stage.setScene(scene);       
		stage.show(); 
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		
		EReaderModel model = (EReaderModel) o;
		
		Page page = (Page) (arg);
		
		System.out.println("Update called with page object" +page.getPageNumber ());
		
		//Re render a differen page
		displayPage(page, "Times New Roman", 12, pane);
		
	}
	
}
