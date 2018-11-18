package FastText;

public class Import {
	private static String PATH = Share.libPath.get(0);
	
	public static void IMPORT_FILE(String name) {
		//System.out.println(PATH+name+".ft");
		new BlockReader().openAbsFile(PATH+name+".ft");
	}
}

