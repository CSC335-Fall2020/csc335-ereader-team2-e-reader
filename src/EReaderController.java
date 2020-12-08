import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

/**
 * @author Korre D. Henry, chloe, chelsey
 * COURSE: CSC 335; Fall 2020
 * Assignment:  Team 2 - E-Reader Project
 * 
 * Purpose: This EReaderController Class is the Controller that is used in this 
 * 			program's MVC structure. This EReaderController Class is 
 * 			used to hold a single EReaderMode instance.
 * 
 * Description: This EReaderController Class will be able to do the following:
 * 				
 * 				Set the font that this model will adhere to when 
 * 				data is being displayed via (manipulating the model 
 * 				instance that it holds).
 * 
 * 				Set the font type that this model will adhere to via (manipulating 
 * 				the model instance that it holds).
 * 				
 * 				Retrieve the Current Book Object that has been selected
 * 				for this model to read/fetch data from via (manipulating the model 
 * 				instance that it holds).
 * 
 * 				
 * 				Store & Add E-Books as Book object to be retrieve and display
 * 				data from via (manipulating the model instance that it holds).
 * 
 * 				Generally can control (interact with), retrieve and manipulate
 * 				data stored in the EReaderModel object instance passed in at 
 * 				construction.
 * 
 */
public class EReaderController {
	EReaderModel model;
	
	
	/**
	 * @purpose: Constructs a EReaderController object instance,
	 * and adds a reference to the EReaderModel 
	 * model object that is passed in. 
	 * 
	 * @param model some EReaderModel object instance
	 * that this EReaderController will interact with
	 * and manipulate.
	 */
	public EReaderController(EReaderModel model) {
		this.model = model;
	}
	
	/**
	 * @purpose: Bookmarks the page associated with the given
	 * page number; number* as the current book being viewed's
	 * book marked page if the given number is a valid page number
	 * in the Book Object.
	 * 
	 * @param number
	 */
	public void bookmarkPage (int number) {
		
		this.model.bookmarkPage(number);
		
	}
	
	/** 
	 * 
	 * @return the string value of the font 
	 * type that this model is currently broadcasting.
	 */
	public String getFont() {
		
		return this.model.getFont();
	}
	
	/**
	 *
	 * @return the integer value of the font size 
	 */
	public int getFontSize() {
		return this.model.getFontSize();
	}
	
	/**
	 * @purpose Sets a new font type for this
	 * model object to broadcast as the passed in 
	 * string value, font* .
	 * 
	 * @param font, some string value of a font type.
	 */
	public void setFont(String font) {
		this.model.setFont(font); 
	}
	
	/**
	 * 
	 * @return the Page object the current Book Object being 
	 * viewed's book marked page if the currently viewed Book Object
	 * has a book marked page set.
	 */
	public Page getBookMarkedPage() {
		return this.model.getBookmarkedPage ();
	}

	/**
	 * @purpose: Sets the font size to the given number 
	 * size* that is passed in. 
	 * 
	 * @param size, some integer value pertaining to the requested
	 * size of content being read as Page objects in this model.
	 */
	public void setFontSize(int size) {
		this.model.setFontSize( size); 
	}
	
	/**
	 * 
	 * @return the Page Object that is currently being 
	 * viewed by this model object.
	 */
	public Book getBook() {
		
		return this.model.getCurrentBook();
	}
	
	/**
	 * 
	 * @param number given a number as an integer value,
	 * 
	 * @return that Page object that contains the beginning of the 
	 * given number* chapter.
	 */
	public Page getChapter ( int number) {
		return this.model.getChapter(number);
	}
	
	
	/**
	 * 
	 * @return the integer value of all the chapters in the
	 * Book Object reference that's currently being viewed.
	 */
	public int getNumberOfChapters() {
		return this.model.getNumberOfChapters();
	}
	
	/**
	 * @purpose: Returns a Book Object associated with the given title name.
	 * 
	 * @param: title, a string value of the the title of some Book Object
	 * related to some E-Book.
	 * 
	 * @returns a Book Object associated with the given title name.
	 */
	public Book getBook(String title) {
		
		return this.model.getBook (title);
	}
	
	
	/**
	 * @purpose: Bookmarks the page associated with the given
	 * page number; number* as the current book being viewed's
	 * book marked page if the given number is a valid page number
	 * in the Book Object.
	 * 
	 * @param number
	 */
	public Page getPage() {
		
		return this.model.getCurrentPage();
	}
	
	/**
	 * 
	 * @return List of Page objects that are contained in the Book
	 * object that this model is currently broadcasting as the current Book
	 * being viewed.
	 */
	public List<Page> getPages() {
		return this.model.getPages();
	}
	
	/**
	 * 
	 * @return current Book Author being observed
	 */
	public String getAuthor() {
 
		return this.model.getAuthor();
	}
	
	/**
	 * 
	 * @return String value of current book title being observed
	 */
	public String getTitle() {
		
		return this.model.getTitle();
	}
	
	/**
	 * 
	 * @purpose: Changes model current page page state to the given page number
	 * 
	 * @param pageNumber, some integer value of a page number in the 
	 *	Book Object that is currently being broadcasted.
	 * 
	 * @return True if page number was in bounds and has been
	 * successfully transitioned to.
	 */
	public boolean goToPage(int pageNumber) {

		return this.model.changePage(pageNumber);
	}
	
	/**
	 * @purpose Changes model state to go to a different book;
	 * 
	 * @param title, the string title of some Book object
	 */
	public void changeBook ( String title) {
		
		this.model.changeBook(title);
		
	}
	
	/**
	 * @purpose: Adds a new Book Object to this controller object's
	 * model.
	 *  
	 * @param fileName, string value of the file name that holds
	 * some E-Book.
	 * 
	 * @throws FileNotFoundException
	 */
	public void addBook (String fileName) throws FileNotFoundException {
		
		//Adds book to the model
		this.model.addBook (fileName);
	}
	
	/**
	 * 
	 * @return a set of titles of books in the model object
	 */
	public Set<String> getBookTitleList () {
		
		return this.model.getBookTitles();
	}
	
	/**
	 * @return the List object of Book objects that t
	 * his program's model holds
	 */
	public List<Book> getBookList (){
		
		return this.model.getBookList();
	}
}
