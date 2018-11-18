package Program;

import FastText.Main;
import Shell.Shell;
import lib.Init;

public class FastText
{
	public static void main(String[] argv){
		Init.INIT();
		if(argv.length != 0){
			Main.RUN(argv[0]);
		}else{
			Shell.SHELL();
		}
	}
}
