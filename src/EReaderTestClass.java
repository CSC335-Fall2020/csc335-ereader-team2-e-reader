import java.io.FileNotFoundException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author korrehenry
 * 
 * COURSE: CSC 335; Fall 2020
 * Assignment:  Team 2 - E-Reader Project
 * 
 * Description: This Test class primarily tests the Model & Controller
 * of the MVC structure for this E-Reader Program. This test
 * class ensures that the saving state of the following: font type,
 * font size, book being viewed, book title, book author, number
 * of books, & more.
 * 
 * Testing Details: This Test Class ensures at least 90% statement coverage for both
 * the Model Class (EReaderModel.java) & Controller class (EReaderController.java).
 * 
 *
 */
public class EReaderTestClass extends TestCase {


	
	/**
	 * Test Model & Controller for keeping track of how many
	 * books have been added to the model. Additionally enforces
	 * that both the model and the controller are keeping track 
	 * of the same references to the same book that is currently being
	 * broadcasted/ viewed by the user.
	 * 
	 * @throws FileNotFoundException
	 */
	public void testAddBook () throws FileNotFoundException {
	
			EReaderModel  model = new EReaderModel();
			EReaderController  controller = new EReaderController(model);
			
			controller.addBook ("Books/salembelle.txt");
			
			assertEquals( controller.getBookList().size(), 1);
			
			controller.addBook ("Books/starship.txt");
			
			assertEquals(model.getPages(), controller.getPages());
			
			assertEquals(model.getCurrentBook(), controller.getBook("The Salem Belle"));
			assertEquals( controller.getBookList().size(), 2);
	
	}
	
	/**
	 * Test Model & Controller for keeping track of the current Title
	 * of the current book being viewed by the user / being broadcasted.
	 * 
	 * @throws FileNotFoundException
	 */
	public void testGetTitle () throws FileNotFoundException {
		
		EReaderModel  model = new EReaderModel();
		EReaderController  controller = new EReaderController(model);
		
		controller.addBook ("Books/salembelle.txt");
		controller.addBook ("Books/starship.txt");
		
		assertEquals( controller.getTitle(), "The Salem Belle");

	}
	
	
	/**
	 * Test Model & Controller for keeping track of the current Author
	 * of the current book being viewed by the user / being broadcasted.
	 *  
	 * @throws FileNotFoundException
	 */
	public void testGetAuthor () throws FileNotFoundException {
		
		EReaderModel  model = new EReaderModel();
		EReaderController  controller = new EReaderController(model);
		
		controller.addBook ("Books/salembelle.txt");
		controller.addBook ("Books/starship.txt");
		
		assertEquals( controller.getAuthor(), "Ebenezer Wheelwright");

	}
	
	/**
	 * Test Model & Controller for proper functionality of
	 * change a book to a different book title.
	 * 
	 * @throws FileNotFoundException
	 */
	public void testChangeBook () throws FileNotFoundException {
		
		EReaderModel  model = new EReaderModel();
		EReaderController  controller = new EReaderController(model);
		
		controller.addBook ("Books/salembelle.txt");
		controller.addBook ("Books/starship.txt");
		
		assertEquals( controller.getTitle(), "The Salem Belle");
		controller.changeBook("Star Ship");
		
		
		assertEquals( controller.getTitle(), "Star Ship");

	}
	
	/**
	 * Test Model & Controller for save state reference of keeping
	 * track of font type and font size.
	 * @throws FileNotFoundException
	 */
	public void testChangeFonts () throws FileNotFoundException {
		
		EReaderModel  model = new EReaderModel();
		EReaderController  controller = new EReaderController(model);
		
		controller.addBook ("Books/salembelle.txt");
		controller.addBook ("Books/starship.txt");
		
		
		//Change Font type
		assertEquals( controller.getFont(),"Times New Roman" );
		controller.setFont("Arial");
		
		assertEquals( controller.getFont(),"Arial" );
		
		//Change Font Size
		assertEquals( controller.getFontSize(), 12 );
		controller.setFontSize(120);
		
		assertEquals( controller.getFontSize(),120 );

	}
	
	/**
	 * Test Model & Controller for save state reference of keeping
	 * track of the same page/current page object that is being
	 * broadcasted.
	 *  
	 * @throws FileNotFoundException
	 */
	public void testCurrentPage () throws FileNotFoundException {
		
		EReaderModel  model = new EReaderModel();
		EReaderController  controller = new EReaderController(model);
		
		controller.addBook ("Books/salembelle.txt");
		controller.addBook ("Books/starship.txt");
		
		assertEquals( controller.getTitle(), "The Salem Belle");
		
		//Change page to 20
		controller.goToPage(20);
		
		assertEquals( controller.getPage().getPageNumber(), 20);

		//Page out of bounds test
		controller.goToPage(2000);
		
		assertEquals( controller.getPage().getPageNumber(), 20);
		
		//Change page to 78
		controller.goToPage(78);
		
		assertEquals( controller.getPage().getPageNumber(), 78);

	}
	
	/**
	 * Test Model & Controller for save state reference of 
	 * keeping track of the same book.
	 * 
	 * @throws FileNotFoundException
	 */
	public void testBookRefs () throws FileNotFoundException {
		
		EReaderModel  model = new EReaderModel();
		EReaderController  controller = new EReaderController(model);
		
		controller.addBook ("Books/salembelle.txt");
		controller.addBook ("Books/starship.txt");
		
		assertEquals( controller.getBook(), model.getBook("The Salem Belle"));
		
		assertEquals( controller.getTitle(), "The Salem Belle");
		
		//Book List, and title list should be the same
		assertEquals( controller.getBookList().size(),   controller.getBookTitleList().size());
		
		
		


	}
	
	
	/**
	 * Test Model & Controller for Booking marking pages, save state of book marked
	 * pages, keeping  the same reference of Page object & more.
	 * @throws FileNotFoundException
	 */
	public void testBookMarkedPage() throws FileNotFoundException {
		
		EReaderModel  model = new EReaderModel();
		EReaderController  controller = new EReaderController(model);
		
	
		
		controller.addBook ("Books/salembelle.txt");
		controller.addBook ("Books/starship.txt");
		
		
		assertEquals( controller.getTitle(), "The Salem Belle");
		
		controller.bookmarkPage (30);
		
		assertEquals( controller.getBookMarkedPage().getPageNumber(), 30);
		
		assertEquals( controller.getBook().getCurrentPage().getPageNumber(), 1);
		
		
		//Checking out of bounds tests
		controller.bookmarkPage (300000);
		
		assertEquals( controller.getBookMarkedPage().getPageNumber(), 30);
		
		controller.goToPage (30);
		
		//Double Checks the Page model & controller have the same Page Reference.
		assertEquals(controller.getPage(), model.getCurrentPage());
		assertEquals(controller.getPage().getLine(4), model.getCurrentPage().getLine(4));
				
		assertEquals( controller.getPage().getPageNumber(), 30);
		
		
		//Checks if the model still keeps track of book marked page 
		controller.goToPage (40);
		
		assertEquals( controller.getBookMarkedPage().getPageNumber(), 30);
		
		assertEquals( controller.getPage().getPageNumber(), 40);
		


	}

}
