package Shell;
import FastText.*;
import lib.Init;

import java.util.*;

public class Shell
{
	private static boolean IN_BLOCK = false;
	private static String NOW;
	private static String sline;
	
	static ArrayList<String> line = new ArrayList<String>();
	
	public static void main(String[] a){
		Init.INIT();
		SHELL();
	}
	
	@SuppressWarnings("resource")
	public static void SHELL(){

		System.out.println("the Shell for FastText "+Share.VERSION);
		System.out.println("================Shell================");


		while(true){
			if(!IN_BLOCK){
				System.out.print(">>");
				sline = new Scanner(System.in).nextLine();
				line.add(sline);
				BlockReader.ReadLine(line);
				line.clear();
			}else{
				System.out.print(NOW+" >>");
				sline = new Scanner(System.in).nextLine();
				line.add(sline);
			}
			
			sline = sline.replaceAll(" ","").replaceAll("\\t","");
			NOW = Share.MAIN_BLOCK;
			
			if(sline.startsWith(Grammar.FT_blockC)){
				IN_BLOCK = false;
			}

			if(IN_BLOCK){
				BlockMenager.getBlock(NOW).addLine(sline);
			}

			if(sline.startsWith(Grammar.FT_blockA)){
				String BLOCK_NAME = sline.substring(
					sline.indexOf(Grammar.FT_blockA)+Grammar.FT_blockA.length(),
					sline.indexOf(Grammar.FT_blockB));

				IN_BLOCK = true;
				NOW = BLOCK_NAME;

				BlockMenager.addBlock(NOW,new Block());
			}

			if(sline.startsWith(Grammar.FT_blockB)){
				IN_BLOCK = true;
				NOW = Share.MAIN_BLOCK;

				BlockMenager.addBlock(NOW,new Block());
			}
			
			
		}
	}
}
