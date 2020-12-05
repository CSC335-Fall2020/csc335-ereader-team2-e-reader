/**
 * @author chelseybergmann
 * File: EReaderjava
 * Project: Final Project - E-Reader
 * Purpose: This class launches the EReader gui view.
 */

import java.io.FileNotFoundException;
import javafx.application.Application;

public class EReader {
	public static void main(String[] args) throws FileNotFoundException {
			Application.launch(EReaderView.class, args);
	}
	

}