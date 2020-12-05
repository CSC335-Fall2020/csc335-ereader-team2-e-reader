/**
 * @author chelseybergmann, chloed
 * File: EReaderView.java
 * Project: Final Project - E-Reader
 * Purpose: This class is the gui view that displays the reader.
 **/



import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EReaderView extends Application implements Observer {
	BorderPane pane;
	
	@Override
	public void start(Stage stage) throws Exception {
		pane = new BorderPane();
		
		// initialize the model and controller
		EReaderModel model = new EReaderModel();
		EReaderController controller = new EReaderController(model);
		
		
		// Test reading a book.
		List<String[]> pages = controller.getPages();
		
		// create heading 
		String titleAndAuthor = controller.getTitle() + " by " + controller.getAuthor();
		Label heading = new Label();
		heading.setText(titleAndAuthor);
		heading.setAlignment(Pos.CENTER);
		pane.setTop(heading);
		pane.setAlignment(heading, Pos.CENTER);
		
		// TO DO: EVENT HANDLER FOR CHANGING FONT / SIZE
		
		// set up the pages.  Default.
		setUpPages(pages, "Times New Roman", 12, pane);
		
		// display the user's options on the side 
		// entered info is stored in font and fontSize text fields( index 1 and 3)
		HBox fonts = sideOptions(pages, pane);
		
		// Set and display scene.
		setScene(pane, stage); 
	}
	
	private void setUpPages(List<String[]> pages, String fontType, int fontSize, BorderPane pane) {
		// get the list of pages
		Label firstPage = new Label();
		String pageText = "";

		// add an outer loop to create a new screen for each page
		for (String line : pages.get(0)) {
				pageText += line;
				pageText += "\n";
		}
		
		firstPage.setText(pageText);
		firstPage.setFont(new Font(fontType, fontSize));
		pane.setCenter(firstPage);
	}
	
	private HBox sideOptions(List<String[]> pages, BorderPane pane) {
		HBox fonts = new HBox();	
		Label fontL = new Label("Font:  ");
		TextField font = new TextField();
		font.setMaxWidth(30);
		Label fontS = new Label("Font Size:  ");
		TextField fontSize = new TextField();
		fontSize.setMaxWidth(30);
		fonts.getChildren().addAll(fontL, font, fontS, fontSize);
		// entered info is stored in font and fontSize text fields( index 1 and 3)
		
		font.setOnAction((event) -> { // Change font.
			String fontSizeStr = fontSize.getText();
			int fontSizeInt = Integer.valueOf(fontSizeStr);
			String fontType = font.getText();
			
			setUpPages(pages, fontType, fontSizeInt, pane);
			
		});
		
		fontSize.setOnAction((event) -> { // Change font size.
			// Use this code to get the user entered font size when filled in
			String fontSizeStr = fontSize.getText();
			int fontSizeInt = Integer.valueOf(fontSizeStr);
			String fontType = font.getText();
			
			setUpPages(pages, fontType, fontSizeInt, pane);
			
		});
		
		pane.setLeft(fonts);
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
		
	}
	
}
