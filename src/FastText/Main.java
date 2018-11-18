package FastText;

import lib.Init;

public class Main
{
	public static void main(String[] a){
		RUN(
				"src\\test\\t1.ft");
	}
	
	public static void RUN(String p){
		Init.INIT();
		BlockReader ft = new BlockReader();
		ft.openAbsFile(p);
	}
}
