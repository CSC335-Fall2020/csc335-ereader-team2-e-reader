import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

/**
 * @author chelseybergmann, chloed
 * File: EReaderController.java
 * Project: Final Project - E-Reader
 * Purpose: This class controls the e-reader.
 */
public class EReaderController {
	EReaderModel model;
	
	public EReaderController(EReaderModel model) {
		this.model = model;
	}
	
	public List<Page> getPages() {
		return this.model.getPages();
	}
	
	public String getAuthor() {
		return this.model.getAuthor();
	}
	
	public String getTitle() {
		return this.model.getTitle();
	}
	
	public void addBook (String fileName) throws FileNotFoundException {
		
		//Adds book to the model
		this.model.addBook (fileName);
	}
	
	//Returns a set of titles of books in the model object
	public Set<String> getBookTitleList () {
		
		return this.model.getBookTitles();
	}
	
	//Return the list of book objects that this model holds
	public List<Book> getBookList (){
		
		return this.model.getBookList();
	}
}
