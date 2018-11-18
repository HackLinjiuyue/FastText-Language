package FastText;

import lib.Init;

public class Main
{
	public static void main(String[] a){
		RUN(
			"/storage/emulated/0/AppProjects/FastText/src/test/t2.ft");
	}
	
	public static void RUN(String p){
		Init.INIT();
		BlockReader ft = new BlockReader();
		ft.openAbsFile(p);
	}
}
