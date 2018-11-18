package Program;

import FastText.Main;
import Shell.Shell;

public class FastText
{
	public static void main(String[] argv){
		if(argv.length != 0){
			Main.RUN(argv[0]);
		}else{
			Shell.SHELL();
		}
	}
}
