

/** Author: Korre Henry
 * 
 * Description: This class is used to store data related to a specific page.
 * In this fashion the a Page class is handled and should always be stored
 * in a Book Object.
 * 
 * This Page Class holds meta data about itself such as:
 * 		
 * 		Page Number: The Page number of the book it is in.
 * 		Book Name: The book title that it is associated to.
 * 		Page Content: Information relating to the content of the page itself.
 * 		Line Content: Information relating to the content of a specific line number.
 * 
 */
public class Page {
	
	private int pageNumber;
	private String bookName;
	private String [] pageContent;
	
	
	
	
	Page (String bookName, int number, String[] content){
		
		this.bookName = bookName;
		
		this.pageNumber = number;
		
		this.pageContent = content;
	}
	
	
	
	
	public String getBookName () {
		
		
		return this.bookName;
		
	}
	
	
	public int getPageNumber () {
		
		
		
		return this.pageNumber;
	}
	
	/** 
	 * @returns array list of lines in this page object as a String [] array.
	 * 
	 */
	public String [] getPageContentArray () {
		
		return this.pageContent;
	}
	
	
	/**
	 * 
	 * Given a line number returns the content of the specified line number 
	 * as a string value string. This is not indexed at 0. Should be given values 
	 * higher than 0.
	 * 
	 * Accepts real numbers from 1 - 1,000.
	 * 
	 * @param lineNumber
	 * @return
	 */
	public String getLine ( int lineNumber) {
		
		//Gets the index number in the array of the string [] array of content.
		int indexNumber = lineNumber - 1;
		
		if (indexNumber > -1 && lineNumber < this.pageContent.length) {
			
			return this.pageContent[indexNumber];
		}
		
		//Line number too high or too low, out of bounds.
		return null;
		
	}
	
	
	/** @returns All the lines in this Page object as one String delimited by new line characters.
	 * 
	 */
	public String getContent() {
		
		String content = "";
		for (String line: this.pageContent) {
			
			if (line != null) {
				content += line +"\n";
			}
			
		}
		return content;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
