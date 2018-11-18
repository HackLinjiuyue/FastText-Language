package lib;

import java.io.File;

import FastText.Import;
import FastText.LocalValues;
import FastText.Share;

public class Init {
	private static File btn;
	public static void INIT() {
		
		//ADD THE LIB PATH
		btn = new File("");
		Share.libPath.add(btn.getAbsolutePath()+File.separator+"lib"+File.separator);
		//END
		
		//AUTO IMPORT __bulitins__ BLOCK
		Import.IMPORT_FILE("__bulitins__");
		//END
		
		//ADD SELF INFO
		LocalValues.values.put("__PATH__", btn.getAbsolutePath()+File.separator+"lib"+File.separator);
		//END
	}
}
