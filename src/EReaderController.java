import java.util.List;

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
	
	public List<String[]> getPages() {
		return model.createPages();
	}
	
	public String getAuthor() {
		return model.getAuthor();
	}
	
	public String getTitle() {
		return model.getTitle();
	}
}
