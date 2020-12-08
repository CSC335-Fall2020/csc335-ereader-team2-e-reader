/** 
 * @author: Korre Henry
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
	
	
	
	/**
	 * @purpose: Constructs a new Page object instance with
	 * a reference to the title of the E-Book it came from, 
	 * its page number and the stream on content contained
	 * on the specified page number.
	 * 
	 * @param bookName, the the Book title that this page is
	 * created from.
	 * 
	 * @param number, page number that this stream of content
	 * was derived from.
	 * 
	 * @param content, the string array of lines of content
	 * contained in the section of some page number in the 
	 * program's read-in E-Book reference.
	 */
	Page (String bookName, int number, String[] content){
		
		this.bookName = bookName;	
		this.pageNumber = number;
		this.pageContent = content;
	}
	
	
	
	/**
	 * Purpose: Gets the title.
	 * @return the title as a string
	 */
	public String getBookName () {
		return this.bookName;
	}
	
	
	/** 
	 * @returns the integer value of page 
	 * number that is associated with this meta 
	 * data contained in this Page object.
	 * 
	 */
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
	 * @purpose: Given a line number returns the content of the specified line number 
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
	
	
	/** 
	 * @returns All the lines in this Page object as one String delimited by new line characters.
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
