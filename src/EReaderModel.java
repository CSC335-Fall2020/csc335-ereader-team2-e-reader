import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * @author Korre D. Henry, chloe, chelsey
 * COURSE: CSC 335; Fall 2020
 * Assignment:  Team 2 - E-Reader Project
 * 
 * Purpose: This EReaderModel Class is the Model that is used in this 
 * 			program's MVC structure. This EReaderModel Class is 
 * 			used to hold Book objects where each Book object 
 * 			pertains to a specific E-Book that has been added to
 * 			this model object instance.
 * 
 * Description: This EReaderModel Class will be able to do the following:
 * 				
 * 				Set the font that this model will adhere to when 
 * 				data is being displayed.
 * 
 * 				Set the font type that this model will adhere to.
 * 				
 * 				Retrieve the Current Book Object that has been selected
 * 				for this model to read/fetch data from.
 * 
 * 				
 * 				Store & Add E-Books as Book object to be retrieve and display
 * 				data from.
 * 
 */

public class EReaderModel extends Observable {
	
	private Book currBook; //Specified the current book you are looking at
	private Page currPage;
	private int fontSize; // Font size
	private String fontType;//Font type
	
	private List <Book> bookList = new ArrayList<Book>();
	
	//Hashes Book title to a specified Book Object
	private HashMap<String, Book> bookMap = new HashMap<String, Book>();

	/**
	 * @purpose: Constructs a EReaderModel object instance,
	 * with all null attributes relating to the current book
	 * being viewed as "currBook", the current page being viewed as
	 * "currPage". Sets the default font size attribute to 12,
	 * sets the default font style to "Times New Roman" font.
	 * 
	 * @throws FileNotFoundException
	 */
	public EReaderModel() throws FileNotFoundException {
		// Test reading a book.
		this.currBook = null;
		this.currPage = null;
		this.fontType = "Times New Roman";
		this.fontSize = 12;
		
	}
	
	/** 
	 * 
	 * @return the string value of the font 
	 * type that this model is currently broadcasting.
	 */
	public String getFont() {
		
		return this.fontType;
	}
	/**
	 *
	 * @return the integer value of the font size 
	 */
	public int getFontSize() {
		return this.fontSize;
	}
	
	/**
	 * @purpose Sets a new font type for this
	 * model object to broadcast as the passed in 
	 * string value, font* .
	 * 
	 * @param font, some string value of a font type.
	 */
	public void setFont(String font) {
		
		if (font!= null && !font.equals("")) {
			this.fontType = font; 
		}
	}
	
	/**
	 * @purpose: Sets the font size to the given number 
	 * size* that is passed in. 
	 * 
	 * @param size, some integer value pertaining to the requested
	 * size of content being read as Page objects in this model.
	 */
	public void setFontSize(int size) {
		this.fontSize = size; 
	}
	
	/**
	 * 
	 * @return the Page Object that is currently being 
	 * viewed by this model object.
	 */
	public Page getCurrentPage() {
		return this.currBook.getCurrentPage();
		
	}
	
	/**
	 * 
	 * @param number given a number as an integer value,
	 * 
	 * @return that Page object that contains the beginning of the 
	 * given number* chapter.
	 */
	public Page getChapter( int number) {
		return this.currBook.getChapter( number);
		
	}
	
	/**
	 * 
	 * @return the integer value of all the chapters in the
	 * Book Object reference that's currently being viewed.
	 */
	public int getNumberOfChapters() {
		return this.currBook.getNumberOfChapters();
	}
	
	
	/**
	 * 
	 * @return the Page object the current Book Object being 
	 * viewed's book marked page if the currently viewed Book Object
	 * has a book marked page set.
	 */
	public Page getBookmarkedPage () {
		
		return this.currBook.getbookMarkedPage();
	}
	
	/**
	 * @purpose: Bookmarks the page associated with the given
	 * page number; number* as the current book being viewed's
	 * book marked page if the given number is a valid page number
	 * in the Book Object.
	 * 
	 * @param number, integer value of some number that will be
	 * the book marked page.
	 */
	public void bookmarkPage(int number ) {
	
		this.currBook.bookMarkPage (number);
	}
	
	/**
	 * 
	 * @return the Book Object that this model is currently holding
	 * or broadcasting as the E-Book being viewed.
	 */
	public Book getCurrentBook() {
		return this.currBook;
	}
	
	/**
	 * 
	 * @return List of Page objects that are contained in the Book
	 * object that this model is currently broadcasting as the current Book
	 * being viewed.
	 */
	public List<Page> getPages() {
		List<Page> pages = this.currBook.getPages();
		return pages;
	}
	
	
	/**
	 * 
	 * @return current Book Author being observed
	 */
	public String getAuthor() {
		

		return this.currBook.getAuthor();
	}
	
	/**
	 * 
	 * @return String value of current book title being observed
	 */
	public String getTitle() {
		return this.currBook.getTitle();
	}
	
	/**
	 * 
	 * @return List Object of books, List of book objects
	 */
	public List<Book> getBookList(){
		
		return this.bookList;
	}
	
	/**
	 * 
	 * @return Set of String objects representing the titles 
	 * of all the books contained in this model object.
	 */
	public Set<String> getBookTitles() {
		
		return this.bookMap.keySet();
		
	}
	
	/**
	 * 
	 * @purpose: Given a book title, changes the currBook being observed to 
	 * the new book title given.
	 * 
	 * @return True if the the title given was a valid book titile
	 * in this model and the current book being viewed is changed. False
	 * otherwise.
	 */
	public boolean changeBook( String title) {
		
		if (this.bookMap.containsKey(title)) {
			
			this.currBook = this.bookMap.get (title);
			
			//Sets current page to first page of the new book
			this.currPage = this.currBook.getCurrentPage();
			
			//Notify observers that current book being observed has changed.
			setChanged();
			notifyObservers (this.currPage);
			
			
			//Indicates the book was changed
			return true;
		}
		
		//Indicates the book was not changed.
		return false;
	}
	
	/**
	 * 
	 * @purpose: Changes to the page number that is given
	 * as pageNunber* .
	 * 
	 * @param pageNumber, integer value of some page number 
	 * in the Book object that is currently being broadcasted in 
	 * this model instance.
	 * 
	 * @return True if the page number was succesfually 
	 * turned to and is in bounds, false if not.
	 */
	public boolean changePage(int pageNumber) {
		
		Page pageObj = this.currBook.getPage (pageNumber);
		
		if (pageObj != null) {
			this.currPage = this.currBook.getCurrentPage();
			
			//Notify observers that current book being observed has changed.
			setChanged();

			
			notifyObservers (this.currPage);
			
			return true;
		}
		
		//Indicates page number is out of pounds;
		return false;

		
		
		
	}
	
	/**
	 * @purpose: Returns a Book Object associated with the given title name.
	 * 
	 * @param: title, a string value of the the title of some Book Object
	 * related to some E-Book.
	 * 
	 * @returns a Book Object associated with the given title name.
	 */
	public Book getBook( String title ) {
		
		if (this.bookMap.containsKey(title)) {
			
			return this.bookMap.get(title);
		}
		return null;
	}
	
	/**
	 * @purpose: Adds a new Book Object to this model.
	 *  
	 * @param fileName, string value of the file name that holds
	 * some E-Book.
	 * 
	 * @throws FileNotFoundException
	 */
	public void addBook( String fileName) throws FileNotFoundException {
		
		//Makes a book object
		Book newBook = new Book (fileName);
		
		//Gets the book title after its been made
		String bookTitle = newBook.getTitle();
		
		if (this.currBook == null) {
			
			//Set Book reference
			this.currBook = newBook;
			
			//Sets current page equal to first page of the book.
			this.currPage = this.currBook.getCurrentPage();
		}
		
		//Hashes Book title to the book Object
		this.bookMap.put(bookTitle, newBook);
		
		//Adds Book object to list of Books this model holds
		this.bookList.add( newBook);
		
	}
	

}
