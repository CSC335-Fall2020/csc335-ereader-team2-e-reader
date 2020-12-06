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
 * @author chelseybergmann, chloed
 * File: EReaderModel.java
 * Project: Project 5 - Connect 4
 * Purpose: This class does the behind the scene work for the e-reader.
 */



public class EReaderModel extends Observable {
	
	private Book currBook; //Specified the current book you are looking at
	private Page currPage;
	
	private List <Book> bookList = new ArrayList<Book>();
	
	//Hashes Book title to a specified Book Object
	private HashMap<String, Book> bookMap = new HashMap<String, Book>();

	public EReaderModel() throws FileNotFoundException {
		// Test reading a book.
		this.currBook = null;
		this.currPage = null;
		
	}
	
	public Page getCurrentPage() {
		return this.currPage;
		
	}
	
	public Book getCurrentBook() {
		return this.currBook;
	}
	
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
	 * @return current book title being observed
	 */
	public String getTitle() {
		return this.currBook.getTitle();
	}
	
	/**
	 * 
	 * @return List of book objects
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
	
	/*
	 * 
	 * Given a book title, changes the currBook being observed to 
	 * the new book title given.
	 */
	public boolean changeBook( String title) {
		
		if (this.bookMap.containsKey(title)) {
			
			this.currBook = this.bookMap.get (title);
			
			//Sets current page to first page of the new book
			this.currPage = this.currBook.getPage(1);
			
			//Notify observers that current book being observed has changed.
			setChanged();
			notifyObservers (this.currPage);
			
			
			//Indicates the book was changed
			return true;
		}
		
		//Indicates the book was not changed.
		return false;
	}
	
	public boolean changePage(int pageNumber) {
		
		Page pageObj = this.currBook.getPage (pageNumber);
		
		if (pageObj != null) {
			this.currPage = this.currBook.getCurrentPage();
			
			//Notify observers that current book being observed has changed.
			setChanged();
			System.out.println(" Recieved request free page number : "+ pageNumber);
			System.out.println( "Page number we got back was : "+this.currPage.getPageNumber());
			
			notifyObservers (this.currPage);
			
			return true;
		}
		
		//Indicates page number is out of pounds;
		return false;

		
		
		
	}
	
	/**
	 *  Adds a new Book Object to this model.
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
