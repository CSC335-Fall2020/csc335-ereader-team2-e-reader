import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book {
	String title;
	String author;
	List<String[]> pages;
	
	public Book(String fileName) throws FileNotFoundException {
		pages = new ArrayList<String[]>();
		read(fileName);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
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
			} else if (curr.startsWith("***")) {
				getPages(scanner);
				break;
			}
		}
		
	}
	
	private void getPages(Scanner scanner) {
		String[] page = new String[50]; // 50 lines per page.
		
			for (int i = 0; i < 50; i++) {
				if (scanner.hasNextLine()) {
					page[i] = scanner.nextLine();
					if (i == 49) {
						pages.add(page);
					}
				
				} else {
					pages.add(page);
					break;
				}
				
			}
		}
	
	
	public List<String[]> getPages() {
		return pages;
	}
}