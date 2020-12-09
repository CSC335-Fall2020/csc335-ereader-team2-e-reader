/**
 * @author chelseybergmann, chloed, Korre Henry
 * File: EReaderjava
 * Project: Final Project - E-Reader
 * Purpose: This class launches the EReader gui view.
 */

import java.io.FileNotFoundException;
import javafx.application.Application;

/**
 * 
 * @author chelseybergmann, chloed, Korre Henry
 *
 * Purpose: This class launches the EReader gui view.
 */
public class EReader {
	
	/**
	 * Purpose: This class launches the EReader gui view.
	 * @param args 
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
			Application.launch(EReaderView.class, args);
	}
	

}