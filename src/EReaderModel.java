import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;

import javafx.scene.control.Label;

/**
 * @author chelseybergmann, chloed
 * File: EReaderModel.java
 * Project: Project 5 - Connect 4
 * Purpose: This class does the behind the scene work for the e-reader.
 */

public class EReaderModel extends Observable {
	
	private Book book;

	public EReaderModel() throws FileNotFoundException {
		// Test reading a book.
		this.book = new Book("Books/salembelle.txt");
	}
	
	public List<String[]> createPages() {
		List<String[]> pages = book.getPages();
		return pages;
	}
	
	public String getAuthor() {
		return this.book.getAuthor();
	}
	
	public String getTitle() {
		return this.book.getTitle();
	}
}
