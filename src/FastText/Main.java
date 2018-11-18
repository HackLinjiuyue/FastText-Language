package FastText;

public class Main
{
	public static void main(String[] a){
		RUN(
				"src\\test\\loop.ft");
		
	}
	
	public static void RUN(String p){
		BlockReader ft = new BlockReader();
		ft.openAbsFile(p);
	}
}
