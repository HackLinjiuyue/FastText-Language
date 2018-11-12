package Shell;
import FastText.FastText;

import java.util.*;

	public class Shell
{
	static ArrayList line = new ArrayList();
	public static void main(String[] a){

		System.out.println("the Shell for FastText");
		System.out.println("========Shell========");


		while(true){
			System.out.print(">>");
			line.add(new Scanner(System.in).nextLine());
			FastText.ReadLine(line);
			line.clear();
		}
	}
}
