import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Book {
	private String title;
	private String author;
	private List<Page> pages;
	
	//Maps a page number to a specific page object.
	HashMap< Integer, Page > pageMap = new HashMap<Integer, Page> ();
	
	public Book(String fileName) throws FileNotFoundException {
		this.pages = new ArrayList<Page>();
		read(fileName);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	private void read(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		
		while (scanner.hasNextLine()) {
			String curr = scanner.nextLine();
			String[] split = curr.split(": ");
			if (curr.startsWith("Author")) {
				author = split[1];
			} else if (curr.startsWith("Title")) {
				title = split[1];
				
				//else if (curr.startsWith("***")) -- Chelsey's previous code
				
			} else {
				
				int lineCount = 50; //Specifies the line count per page, it is set to be 50
				buildPages(scanner, lineCount);
				
				//Done creating pages from this file.
				break; 

			}
			
			
		}
		
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
	 * @returns the number of pages this book has.
	 */
	 public int getPageCount (){
	 
		 return this.pages.size();
	 }
	 
	 
	
	
	/**
	 * Returns the page object specified by a page number.
	 * 
	 * @param pageNumber
	 * @return a Page Object of associated with that page number
	 */
	public Page getPage(int pageNumber ) {
		
		if (this.pageMap.containsKey(pageNumber)) {
			
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
		
		//The Page doesn't exist or line count is not in bounds of th page.
		return null;
	}
	/**
	 * @return a List of Page Objects contained in this book object
	 */
	public List<Page> getPages() {
		return this.pages;
	}
}