/**
 * @author chelseybergmann, chloed, Korre Henry
 * File: EReaderController.java
 * Project: Final Project - E-Reader
 * Purpose: This class controls the e-reader such as bookmarking a page, setting the font,
 *  getting a book, page, author, or title. It also controls jumping to a page, getting
 *  the book list, adding a book, or changing the current book.
 */
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public class EReaderController {
	EReaderModel model;
	
	public EReaderController(EReaderModel model) {
		this.model = model;
	}
	
	/**
	 * Purpose: Bookmarks a page.
	 * @param number the page number
	 */
	public void bookmarkPage (int number) {
		
		this.model.bookmarkPage(number);
		
	}
	
	/**
	 * Purpose: Gets the selected or default font.
	 */
	public String getFont() {
		
		return this.model.getFont();
	}
	
	/**
	 * Purpose: Gets the font size.
	 */
	public int getFontSize() {
		return this.model.getFontSize();
	}
	
	/**
	 * Purpose: Sets the font to a new one.
	 * @param font the new font as a string
	 */
	public void setFont(String font) {
		this.model.setFont(font); 
	}
	
	/**
	 * Purpose: Gets the bookmarked page.
	 * @preturn the page
	 */
	public Page getBookMarkedPage() {
		return this.model.getBookmarkedPage ();
	}
	
	/**
	 * Purpose: Sets the font size to a new one.
	 * @param size the new size
	 */
	public void setFontSize(int size) {
		this.model.setFontSize( size); 
	}
	
	/**
	 * Purpose: Gets the current book.
	 * @return the book
	 */
	public Book getBook() {
		
		return this.model.getCurrentBook();
	}
	
	/**
	 * Purpose: Gets the current page.
	 * @return the page
	 */
	public Page getPage() {
		
		return this.model.getCurrentPage();
	}
	
	/**
	 * Purpose: Gets the current book's pages.
	 * @param an array list of page objects
	 */
	public List<Page> getPages() {
		return this.model.getPages();
	}
	
	/**
	 * Purpose: Gets the author.
	 * @return the author
	 */
	public String getAuthor() {
 
		return this.model.getAuthor();
	}
	
	/**
	 * Purpose: Gets the title.
	 * @return the title
	 */
	public String getTitle() {
		
		return this.model.getTitle();
	}
	
	/**
	 * Purpose: Changes model current page page state to the given page number
	 * @param pageNumber
	 * @return boolean
	 */
	public boolean goToPage(int pageNumber) {

		return this.model.changePage(pageNumber);
	}
	
	/**
	 * Purpose: Changes model state to go to a different book;
	 * @param title
	 */
	public void changeBook ( String title) {
		
		this.model.changeBook(title);
		
	}
	
	/**
	 * Purpose: Adds a new book to the list of books.
	 * @param fileName name of book
	 * @throws FileNotFoundException
	 */
	public void addBook (String fileName) throws FileNotFoundException {
		
		//Adds book to the model
		this.model.addBook (fileName);
	}
	
	/**
	 * Purpose: Returns a set of titles of books in the model object
	 * @return a set of titles
	 */
	public Set<String> getBookTitleList () {
		
		return this.model.getBookTitles();
	}
	
	/**
	 * Purpose: Return the list of book objects that this model holds
	 * @return a list of book objects
	 */
	public List<Book> getBookList (){
		
		return this.model.getBookList();
	}
}
