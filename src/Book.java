/**
 * @author chelseybergmann, chloed, Korre Henry
 * File: Book.java
 * Project: Final Project - E-Reader
 * Purpose: This class does the behind the scene work for the e-reader such as getting
 * the font, current page, a bookmarked page, a list of all books, the title, and author.
 * It also has the abilities to change the current page, add a new book, and change
 * the current book.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Book {
	private String title;
	private String author;
	private int currentPageNumber;
	private int bookMarkedPage = -1;
	
	private List<Page> pages;
	
	//Maps a page number to a specific page object.
	HashMap< Integer, Page > pageMap = new HashMap<Integer, Page> ();
	
	public Book(String fileName) throws FileNotFoundException {
		this.pages = new ArrayList<Page>();
		System.out.print ("Book being made with " + fileName);
		read(fileName);
		
		//Sets current page to 1
		this.currentPageNumber = 1;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Page getCurrentPage() {
		
		return this.pageMap.get (this.currentPageNumber);
	}
	
	//Sets Book Marked Page
	public void bookMarkPage(int number) {
		
		this.bookMarkedPage = number;
	}
	
	public Page getbookMarkedPage() {
		
		if (this.bookMarkedPage != -1) {
			
			return this.pageMap.get (this.bookMarkedPage);
		}
		return this.pageMap.get (1);//First page
		
	}
	
	private void setCurrentPage(int number) {
		
		//If Page count is in bounds
		if (number > 0 && number <= getPageCount ()) {
			
			this.currentPageNumber = number;
		}
				

	}
	
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * Purpose: Reads the book by extracting the title, author, and each page with
	 * 50 lines per page.
	 * @param fileName the book which is a .txt
	 * @throws FileNotFoundException
	 */
	private void read(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		
		while (scanner.hasNextLine()) {
			String curr = scanner.nextLine();
			String[] split = curr.split(": ");
			if (curr.startsWith("Author")) {
				System.out.print ("Book title set as "+split[1]);
				this.author = split[1];
			} else if (curr.startsWith("Title")) {
				this.title = split[1];
				
			}
			
			if (this.title != null && this.author != null && curr.startsWith("***")) {
				break;
			}
		}

			
		//Start building pages
		int lineCount = 50;
		buildPages(scanner, lineCount);
		//Specifies the line count per page, it is set to be 50	
		
	}
	
	/** 
	 * Given a Scanner object loops through all the remaining content
	 * in the file add makes page of content where the line count is 
	 * any given number lineCount
	 * 
	 * @param scanner, scanner object of the file
	 * @param lineCount, integer  value number of lines this page will have
	 */
	private void buildPages(Scanner scanner, int lineCount) {
		String[] content = new String[lineCount]; // 50 lines per page.
		
		//Page number start at 1.
		int pageNumber = 1;
		
		//For all the lines in the file.
		while (scanner.hasNextLine()) {
				
				for (int i = 0; i < lineCount; i++) {
						
					//Gets Next line in page
					if (scanner.hasNextLine()) {
						content[i] = scanner.nextLine();
					}
					else {
						//No more lines are left to add to this page
						break;
					}
				}
					
				//Page Object with containing page number, book title and 
				//content of this page
				Page pageObj = new Page (this.title, pageNumber, content  );
					
				//Adds Page object to the Pages Hash Map.
				//Maps Page Number to the page Object.
				this.pageMap.put (pageNumber, pageObj);
					
				//Adds Page object to the 
				this.pages.add(pageObj);
					
					
				//Reset list of Strings
				content = new String[lineCount];
					
				//Go on to the next page.
				pageNumber++;
		}
		
		//No more lines pages left to make

					
	
	}
	
	
	/**
	 * Purpose: Gets the number of pages.
	 * @returns the number of pages this book has.
	 */
	 public int getPageCount (){
		 return this.pages.size();
	 }
	 
	/**
	 * Purpose: Returns the page object specified by a page number.
	 * 
	 * @param pageNumber an int representing the page number
	 * @return a Page Object of associated with that page number
	 */
	public Page getPage(int pageNumber ) {
		
		if (this.pageMap.containsKey(pageNumber)) {
			
			//Sets currentPage number to given number
			setCurrentPage (pageNumber);
			
			return this.pageMap.get(pageNumber);
			
		}
		return null;
	}
	
	/**
	 * When given a Page number and a lineNumber returns a 
	 * String line of the content that is on given page number,
	 * 
	 * PageNumber IS NOT indexed by 0. Should be given number 
	 * 1 - 1,000. For example, if you want line 1 then should be passed
	 * pageNumber = 1 not pageNumber = 0.
	 * 
	 * @param pageNumber, integer value representing the page number 
	 * @param lineNumber, integer value representing the line number of the page.
	 * 
	 * @return a String containing content in a specific line on the given page number
	 */
	public String getLineOfPage( int pageNumber, int lineNumber) {
		
		Page pageObj = getPage (pageNumber);
		
		if (pageObj != null) {
			
			return pageObj.getLine (pageNumber);
		}
		
		//The Page doesn't exist or line count is not in bounds of the page.
		return null;
	}
	
	
	/**
	 * Purpose: Gets the pages.
	 * @return a List of Page Objects contained in this book object
	 */
	public List<Page> getPages() {
		return this.pages;
	}
}