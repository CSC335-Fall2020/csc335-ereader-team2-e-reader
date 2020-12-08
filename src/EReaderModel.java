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
 * @author chelseybergmann, chloed, Korre Henry
 * File: EReaderModel.java
 * Project: Final Project - E-Reader
 * Purpose: This class does the behind the scene work for the e-reader such as getting
 * the font, current page, a bookmarked page, a list of all books, the title, and author.
 * It also has the abilities to change the current page, add a new book, and change
 * the current book.
 */

public class EReaderModel extends Observable {
	
	private Book currBook; //Specified the current book you are looking at
	private Page currPage;
	private int fontSize; // Font size
	private String fontType;//Font type
	
	private List <Book> bookList = new ArrayList<Book>();
	
	//Hashes Book title to a specified Book Object
	private HashMap<String, Book> bookMap = new HashMap<String, Book>();

	public EReaderModel() throws FileNotFoundException {
		// Test reading a book.
		this.currBook = null;
		this.currPage = null;
		this.fontType = "Times New Roman";
		this.fontSize = 12;
		
	}
	
	//
	
	/**
	 * Purpose: Returns the Font
	 * @return a string
	 */
	public String getFont() {
		
		return this.fontType;
	}
	
	/**
	 * Purpose: Returns the Font size
	 * @return an int
	 */
	public int getFontSize() {
		return this.fontSize;
	}
	
	/**
	 * Purpose: Sets the Font.
	 *@param font the new font as a string
	 */
	public void setFont(String font) {
		
		if (font!= null && !font.equals("")) {
			this.fontType = font; 
		}
	}
	
	/**
	 * Purpose: Sets the font size.
	 * @param size
	 */
	public void setFontSize(int size) {
		this.fontSize = size; 
	}
	
	/**
	 * Purpose: Gets the current page, a page object.
	 * @return page
	 */
	public Page getCurrentPage() {
		return this.currBook.getCurrentPage();
		
	}
	
	/**
	 * Purpose:  Gets the bookmarked page.
	 * @return a page
	 */
	public Page getBookmarkedPage () {
		
		return this.currBook.getbookMarkedPage();
	}
	
	/**
	 * Purpose: Bookmarks a page.
	 * @param the page number
	 */
	public void bookmarkPage(int number ) {
	
		this.currBook.bookMarkPage (number);
	}
	
	/**
	 * Purpose: Gets the current book.
	 * @return a book object
	 */
	public Book getCurrentBook() {
		return this.currBook;
	}
	
	/**
	 * Purpose: Gets the pages of the current book.
	 * @return a list of pages
	 */
	public List<Page> getPages() {
		List<Page> pages = this.currBook.getPages();
		return pages;
	}
	
	
	/**
	 * Purpose: gets the author.
	 * @return current Book Author being observed
	 */
	public String getAuthor() {
		

		return this.currBook.getAuthor();
	}
	
	/**
	 * Purpose: Gets the title.
	 * @return current book title being observed
	 */
	public String getTitle() {
		return this.currBook.getTitle();
	}
	
	/**
	 * Purpose: gets the book list.
	 * @return List of book objects
	 */
	public List<Book> getBookList(){
		
		return this.bookList;
	}
	
	/**
	 * Purpose: Gets the title of all books.
	 * @return Set of String objects representing the titles 
	 * of all the books contained in this model object.
	 */
	public Set<String> getBookTitles() {
		
		return this.bookMap.keySet();
		
	}
	
	/*
	 * Purpose: Given a book title, changes the currBook being observed to 
	 * the new book title given.
	 * @param title
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
	 * Purpose: Changes the page to a new one.
	 * @param pageNumber
	 * @return that the page number is in bounds
	 */
	public boolean changePage(int pageNumber) {
		
		Page pageObj = this.currBook.getPage (pageNumber);
		
		if (pageObj != null) {
			this.currPage = this.currBook.getCurrentPage();
			
			//Notify observers that current book being observed has changed.
			setChanged();
			//System.out.println(" Recieved request free page number : "+ pageNumber);
			//System.out.println( "Page number we got back was : "+this.currPage.getPageNumber());
			
			notifyObservers (this.currPage);
			
			return true;
		}
		
		//Indicates page number is out of bounds;
		return false;	
		
	}
	
	/**
	 *  Purpose: Adds a new Book Object to this model.
	 *  
	 * @param fileName
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
