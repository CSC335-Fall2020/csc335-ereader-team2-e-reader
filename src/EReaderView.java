/**
 * @author chelseybergmann
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
import javafx.stage.Stage;

public class EReaderView extends Application implements Observer {
	BorderPane pane;
	
	@Override
	public void start(Stage stage) throws Exception {
		pane = new BorderPane();
		
		sideOptions();
		
		// Test reading a book.
		Book book = new Book("Books/salembelle.txt");
		List<String[]> pages = book.getPages();
		Label firstPage = new Label();
		String pageText = "";

			for (String line : pages.get(0)) {
				pageText += line;
				pageText += "\n";
			}
		
		firstPage.setText(pageText);
		pane.setCenter(firstPage);
		
		//
		
		EReaderModel model = new EReaderModel();
		EReaderController controller = new EReaderController(model);
		
		
		
		// Set and display scene.
		Scene scene = new Scene(pane, 900, 600);    
		stage.setTitle("E-Reader GUI View");   
		stage.setScene(scene);       
		stage.show(); 
	}
	
	private void sideOptions() {
		HBox fonts = new HBox();	
		Label fontL = new Label("Font:  ");
		TextField font = new TextField();
		font.setMaxWidth(30);
		Label fontS = new Label("Font Size:  ");
		TextField fontSize = new TextField();
		fontSize.setMaxWidth(30);
		fonts.getChildren().addAll(fontL, font, fontS, fontSize);
		
		pane.setTop(fonts);
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}