/**
 * @author chelseybergmann
 * @author chloed
 * File: EReaderView.java
 * Project: Final Project - E-Reader
 * Purpose: This class is the gui view that displays the reader.
 **/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class EReaderView extends Application implements Observer {
	BorderPane pane;
	EReaderController controller;
	VBox headers = new VBox();

	
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
		this.controller.addBook ("Books/starship.txt");
		Page page = this.controller.getPage ();

		// Displays a page with a given font size
		displayPage(page);
		
		//Displays Menu Panel 
		displayMenuPanel();
		
		//Sets Heading
		this.pane.setTop (this.headers);

		
		// Set and display scene.
		setScene(this.pane, stage); 
	}
	
	private void displayHeading ( BorderPane titleHeader ) {
		// create heading 
		System.out.println ("This is title :"+this.controller.getTitle());
		String titleAndAuthor = this.controller.getTitle() + " by " + this.controller.getAuthor();
		Label heading = new Label ();
		heading.setText(titleAndAuthor);
		heading.setAlignment(Pos.CENTER);
		
		titleHeader.setTop(heading);
		titleHeader.setAlignment(heading, Pos.CENTER);
		
	}
	
	
	/**
	 * Purpose: Displays the current page and its options.
	 * @param page the page from book to display.
	 * @throws FileNotFoundException
	 */
	protected void displayPage(Page page) throws FileNotFoundException {
		// get the list of pages
		Label currPage = new Label();
		String pageText = page.getContent(); //Gets content from page object

		//Displays Page options
		pageOptions(page);
		
		
		currPage.setText(pageText);
		currPage.setFont(new Font(this.controller.getFont(), this.controller.getFontSize()));
		this.pane.setCenter(currPage);
	}
	
	private void pageOptions(Page page) throws FileNotFoundException {
		
		VBox sidePanel = new VBox ();
		Label pageProgress = new Label (
				String.valueOf ("Page "+this.controller.getPage().getPageNumber())+
				" of "+ String.valueOf(this.controller.getPages().size())
				+" Pages "
				
				);
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
        
		Button prevPageButton = new Button("Prev Page", imageView2);
		
		
		prevPageButton.setOnAction( event -> {

			Page currentPage = this.controller.getPage ();
			int pageNumber = currentPage.getPageNumber();
			pageNumber--;//Go to previous page;
			
			this.controller.goToPage(pageNumber);
		});
		
		pageArrows.getChildren().addAll( prevPageButton, nextPageButton );
		pageArrows.setSpacing(10);

		
		sidePanel.getChildren().addAll(pageProgress, pageArrows );
		sidePanel.setSpacing(10);
		
		this.pane.setLeft(sidePanel);
		return ;
	}
	
	private void setScene(BorderPane pane, Stage stage) {
		Scene scene = new Scene(pane, 900, 600);    
		stage.setTitle("E-Reader GUI View");   
		stage.setScene(scene);       
		stage.show(); 
	}
	
	
	public void displayMenuPanel() {
		
		//Creates File Menu Toggle
		Menu fileMenuBox = new Menu("File");
		MenuItem newBookButton = new MenuItem ("New Book");
		newBookButton.setOnAction (e ->{
			BookListViewSettings settings = new BookListViewSettings();
			settings.showAndWait();
			
		});
		
		MenuItem changePageButton = new MenuItem ("Change Page");
		changePageButton.setOnAction (e ->{
			ChangePageSettings settings = new ChangePageSettings();
			settings.showAndWait();
			
		});
		
		
		
		MenuItem bookMarkButton = new MenuItem ("Bookmark Page");
		bookMarkButton.setOnAction (e ->{
			this.controller.bookmarkPage (this.controller.getPage().getPageNumber());
	
			
		});
		
		fileMenuBox.getItems().addAll(newBookButton, changePageButton, bookMarkButton);
		
		//For File Menu
		MenuBar fileMenuBar = new MenuBar (fileMenuBox);
		
		//Creates View Settings Menu Toggle
		Menu viewMenuBox = new Menu("View");
		MenuItem viewSettingsButton = new MenuItem ("View Settings");
		viewSettingsButton.setOnAction (e ->{
			ReadingViewSettings settings = new ReadingViewSettings();
			settings.showAndWait();
			
		});
		
		viewMenuBox.getItems().add(viewSettingsButton );
		
		//For View Settings
		MenuBar viewMenuBar = new MenuBar (viewMenuBox);
		
		//Adds menu toggle to the Top of the Application
		this.headers.getChildren().add(new HBox (fileMenuBar, viewMenuBar ));
		
		BorderPane titleHeader = new BorderPane ();
		displayHeading ( titleHeader);
		this.headers.getChildren().add(titleHeader);
		

		
	}
	
	
	
	
	@Override
	public void update(Observable o, Object arg) {


		EReaderModel model = (EReaderModel) o;
		
		Page page = (Page) (arg);
		
		//Accesses the Title Header
		BorderPane borderPane = (BorderPane) this.headers.getChildren().get(1);
		Label label = (Label) borderPane.getChildren().get(0);
		label.setText(model.getTitle());
		
		
		try {
			//Re Renders a save stated page
			displayPage(page);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private class BookListViewSettings extends Stage {
		

		
		//Search By Type of Title Or Author name;
		private ChoiceBox<String> searchByList = new ChoiceBox<String>();
		
		//FX Collections of Book List
		ObservableList<BookView> booksList = FXCollections.observableArrayList(
				new BookView ("Catherine Hathaway", "Beauty"));
		
		//Filtered Book List
		FilteredList<BookView> flBooks = new FilteredList<BookView>(this.booksList, p -> true);
		
		
		private TextField searchBar = new TextField("");
		
		private Label currentBook = new Label ("Current Book : "+controller.getBook().getTitle());
		private Label currentBookProg = new Label ("Progress : "+ controller.getPage().getPageNumber()
				+" of "+controller.getBook().getPages().size()+" Pages ");
		
		private Label selectedBook = new Label ("Selected Book : ");
		private Label selectedBookProg = new Label ("Progress :");
		private String chosenBook;
		
		//Table view of books
		private TableView<BookView> bookViewTable = new TableView<BookView> ();

		


		
		@SuppressWarnings("unchecked")
		public BookListViewSettings() {
			
			HBox properties = new HBox();
			HBox buttons = new HBox();
			VBox rightList = new VBox();
			VBox list = new VBox();
			initModality(Modality.APPLICATION_MODAL);

			//Adds All Books in the Model to the bookViewTable
			createBookViewList ();
			
			Label bookList = new Label("Search by: ");

			//On Search Click stores the value in the model
			this.bookViewTable.setOnMousePressed( e -> {
				
				clickItem(e);
			});
			//Search by Author or Title name
			this.searchByList.getItems().add("Title");
			this.searchByList.getItems().add("Author");
			this.searchByList.setValue("Title");
			
	        this.searchBar.setPromptText("Search here!");
	        this.searchBar.setOnKeyReleased(keyEvent ->
	        {
	            switch (this.searchByList.getValue())//Switch on search bar value
	            {
	                case "Title":
	                	this.flBooks.setPredicate(book -> 
	                	book.getBookTitle().toLowerCase().contains(
	                			this.searchBar.getText().toLowerCase().trim()));
	                    break;
	                case "Author":
	                	this.flBooks.setPredicate(book ->
	    	                book.getBookAuthor().toLowerCase().contains(
	    	                			this.searchBar.getText().toLowerCase().trim()));
	                	break;
	            }
	        });
	        
	        TableColumn<BookView, String> bookNameCol = new TableColumn<BookView, String>("Book Title");
	        bookNameCol.setMinWidth(100);
	        bookNameCol.setCellValueFactory(
	                new PropertyValueFactory<BookView, String>("bookTitle"));

	        TableColumn<BookView, String> authorNameCol = new TableColumn<BookView, String>("Author Name");
	        authorNameCol.setMinWidth(100);
	        authorNameCol.setCellValueFactory(
	                new PropertyValueFactory<BookView, String>("bookAuthor"));

	        

			
	        this.bookViewTable.setItems(this.flBooks);//Set the table's items using the filtered list
	        this.bookViewTable.getColumns().addAll(bookNameCol, authorNameCol);
	        
			
			
			properties.getChildren().addAll(bookList, this.searchByList, this.searchBar);
			properties.setPadding(new Insets(10, 0, 30, 0));
			
			rightList.getChildren().addAll(this.currentBook, this.currentBookProg, properties,   this.selectedBook, this.selectedBookProg );
			rightList.setPadding(new Insets(10, 10, 100, 20));
			
			Button ok = new Button("OK");
			Button cancel = new Button("Cancel");
			
			ok.setOnAction((e) -> {
				
				//Changes the book name
				controller.changeBook(this.chosenBook);

				int pageNumber = controller.getBookMarkedPage ().getPageNumber();
				controller.goToPage(pageNumber);
				
				//Passes in current page
				try {
					displayPage(controller.getPage());
				
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				hide();

				
			});
			
			cancel.setOnAction( (event) -> {
				//Leaves wasCanceled boolean to off.
				hide();
				
			});
			buttons.getChildren().addAll(ok, cancel);
			
			list.getChildren().addAll(rightList);
			
			BorderPane b = new BorderPane();
			b.setCenter(list);
			b.setBottom(buttons);
			
			VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 10, 10, 10));
	        vbox.getChildren().addAll( this.bookViewTable);
	        b.setLeft(vbox);

	        
			Scene s = new Scene(b, 700, 400);
			
			
			setScene(s);
			setTitle("Reading View Settings");
			
		}
		
		@FXML
		public void clickItem(MouseEvent event)
		{
			//Changes Book to new Reference
			this.chosenBook = this.bookViewTable.getSelectionModel().getSelectedItem().getBookTitle();
	
			this.selectedBook.setText ("Selected Book : "+
			this.bookViewTable.getSelectionModel().getSelectedItem().getBookTitle());
			
			Book newBook = controller.getBook
					(this.bookViewTable.getSelectionModel().getSelectedItem().getBookTitle());
			if (newBook != null) {
				this.selectedBookProg.setText("Progress : "+ 
			newBook.getbookMarkedPage().getPageNumber() +" of "+newBook.getPages().size() +"Pages ");
			}

		    
			System.out.println(this.bookViewTable.getSelectionModel().getSelectedItem().getBookTitle());
		    System.out.println(this.bookViewTable.getSelectionModel().getSelectedItem().getBookAuthor());
		    //System.out.println(tableID.getSelectionModel().getSelectedItem().getCountry());
		    
		}

		
		public void createBookViewList () {
			
			for (Book book: controller.getBookList()) {
				
				String title = book.getTitle();
				String author = book.getAuthor();
				
				//Make new book View object
				BookView bookView = new BookView (title, author);
				
				//Adds Book List
				this.booksList.add(bookView);
			}
		}
		
		public class BookView {

	        private final SimpleStringProperty bookTitle;
	        private final SimpleStringProperty bookAuthor;


	        BookView(String title, String author)
	        {
	            this.bookTitle = new SimpleStringProperty(title);
	            this.bookAuthor = new SimpleStringProperty(author);

	        }

	        public String getBookTitle()
	        {
	            return this.bookTitle.get();
	        }

	        public void setBookTitle(String title)
	        {
	        	this.bookTitle.set(title);
	        }

	        public String getBookAuthor()
	        {
	            return this.bookAuthor.get();
	        }

	        public void setBookAuthor(String author)
	        {
	        	this.bookAuthor.set(author);
	        }


	    }

		
	}
	
	
	
	
	private class ReadingViewSettings extends Stage {
		

		
		//private RadioButton human;
		//private RadioButton computer;
		
		//List of Font Types
		private ChoiceBox<String> dropDownList = new ChoiceBox<String>();
		private TextField fontSize;


		
		public ReadingViewSettings() {
			
			HBox properties = new HBox();
			HBox buttons = new HBox();
			VBox list = new VBox();
			initModality(Modality.APPLICATION_MODAL);

			
			Label fontLabel = new Label("Font ");
			Label fontSizeLabel = new Label("Font Size ");
			
			//List of fonts to choose from
			this.dropDownList.getItems().add("Times New Roman");
			this.dropDownList.getItems().add("Arial");
			this.dropDownList.getItems().add("Other");
			
			//Sets current font to the one that is currently set
			this.dropDownList.setValue(controller.getFont());
			
			
			this.fontSize = new TextField(String.valueOf(controller.getFontSize()));
			
			properties.getChildren().addAll(fontLabel, this.dropDownList, fontSizeLabel,
					this.fontSize);
			properties.setPadding(new Insets(10, 0, 10, 0));
			
			Button ok = new Button("OK");
			Button cancel = new Button("Cancel");
			
			ok.setOnAction((e) -> {
				
				String fontSizeStr = this.fontSize.getText();
				int fontSizeInt = Integer.valueOf(fontSizeStr);
				String fontType = this.dropDownList.getValue();
				
				//Sets New Font Size and Font Type in the model
				controller.setFont(fontType);
				controller.setFontSize(fontSizeInt);
				
				//Passes in current page
				try {
					displayPage(controller.getPage());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				hide();

				
			});
			
			cancel.setOnAction( (event) -> {
				//Leaves wasCanceled boolean to off.
				hide();
				
			});
			buttons.getChildren().addAll(ok, cancel);
			
			list.getChildren().addAll(properties);
			
			BorderPane b = new BorderPane();
			b.setCenter(list);
			b.setBottom(buttons);
			
			Scene s = new Scene(b, 400, 150);
			
			setScene(s);
			setTitle("Reading View Settings");
			
		}
		
		
		public int getFontSize() {
			
			return Integer.parseInt (this.fontSize.getText());
		}
		
		public String getFont() {
			
			return this.dropDownList.getValue();
		}
		

		
	}
	
	
	/**
	 * 
	 * @author korrehenry
	 * 
	 * Description: This ChangePageSettings class is a private class
	 * use to display a Change Page Settings Modal in this
	 * program.
	 *
	 */
	private class ChangePageSettings extends Stage {
		

		
		//private RadioButton human;
		//private RadioButton computer;
		//List of Font Types
		private ChoiceBox<String> dropDownList = new ChoiceBox<String>();
		private TextField chapter;
		private TextField pageNumber = new TextField("");


		
		public ChangePageSettings() {
			
			HBox properties = new HBox();
			HBox buttons = new HBox();
			VBox list = new VBox();
			initModality(Modality.APPLICATION_MODAL);

			System.out.println("I was called to help!");
			
			Label fontLabel = new Label("Chapter ");
			Label fontSizeLabel = new Label("Go to Page : ");
			
			//
			for (int i = 1; i < controller.getNumberOfChapters(); i++) {
				this.dropDownList.getItems().add(String.valueOf(i));
			}

			
			//Sets current font to the one that is currently set
			//this.dropDownList.setValue(controller.getChapter);
			
			
			this.pageNumber.setPromptText("Type Page Number Here");
			//this.fontSize = new TextField(String.valueOf(controller.getFontSize()));
			
			properties.getChildren().addAll(fontLabel, this.dropDownList, fontSizeLabel,
					this.pageNumber);
			properties.setPadding(new Insets(10, 10, 10, 10));
			properties.setSpacing(15);
			
			Button ok = new Button("OK");
			Button cancel = new Button("Cancel");
			
			ok.setOnAction((e) -> {
				
				String pageNumber = this.pageNumber.getText();
				
				int pageInt = controller.getPage().getPageNumber();
				if (!pageNumber.equals("")) {
					pageInt = Integer.valueOf(pageNumber);
				}
				
				
				
				//String chapaterNumber = this.dropDownList.getValue();
				
				//Sets New Font Size and Font Type in the model
				controller.goToPage (pageInt);
				//controller.setFontSize(fontSizeInt);
				
				//Passes in current page
				try {
					displayPage(controller.getPage());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				hide();

				
			});
			
			cancel.setOnAction( (event) -> {
				//Leaves wasCanceled boolean to off.
				hide();
				
			});
			buttons.getChildren().addAll(ok, cancel);
			
			list.getChildren().addAll(properties);
			
			BorderPane b = new BorderPane();
			b.setCenter(list);
			b.setBottom(buttons);
			
			Scene s = new Scene(b, 600, 150);
			
			setScene(s);
			setTitle("Change Page Settings");
			
		}
		

		
	}
	
}
