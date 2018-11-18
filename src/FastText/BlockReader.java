package FastText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import Exceptions.NameNotDefine;
import Exceptions.SymbolSyntaxError;
import Exceptions.SyntaxError;
import java.util.*;

/*
*======================================
*=         FastText Language          =
*=                                    =
*=           Written by CHB           =
*======================================
*/

public class BlockReader {
	private static File file;
	private static BufferedReader reader;
	private static int Countline = 0;
	private static String originLine;

	// HashMap LocalValues.values = new HashMap();
	// HashMap <String,Integer> LocalValues.NumValues = new
	// HashMap<String,Integer>();

	public void openAbsFile(String path) {
		if (path.substring(path.lastIndexOf(".") - 1, path.length()).equals("ft")) {

		}
		file = new File(path);
		getReader();
		readLineFromFile();
	}

	public void openFile(String path) {
		file = new File(path);
		getReader();
		readLineFromFile();
	}

	private static void getReader() {
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void readLineFromFile(){
		ArrayList FILE_LINE = new ArrayList();
		String line;
		try
		{
			while ((line = reader.readLine()) != null)
			{
				FILE_LINE.add(line);
			}
			
			catchBlock(FILE_LINE);
		}
		catch (IOException e)
		{}
	}
	
	static void catchBlock(Collection<String> c){
		boolean isin = false;
		String now = Share.MAIN_BLOCK;
		for(String line:c){
			Share.lineCount++;
			letValue("FT__LINE__","int",String.valueOf(Share.lineCount));
			line = line.replaceAll("\t","");
			//System.out.println(line);
			
			if(line.startsWith(Grammar.FT_blockC)){
				isin = false;
			}
			
			if(isin){
				BlockMenager.getBlock(now).addLine(line);
				//System.out.println(line);
			}
			
			if(line.startsWith(Grammar.FT_blockA)){
				line = line.replaceAll(" ", "");
				
				String BLOCK_NAME = line.substring(
					line.indexOf(Grammar.FT_blockA)+Grammar.FT_blockA.length(),
					line.indexOf(Grammar.FT_blockB));
				
				isin = true;
				now = BLOCK_NAME;
					
				BlockMenager.addBlock(now,new Block());
			}
			
			if(line.startsWith(Grammar.FT_blockB)){
				//System.out.println("yes");
				isin = true;
				now = Share.MAIN_BLOCK;
				
				BlockMenager.addBlock(now,new Block());
			}
			if(line.startsWith(Grammar.FT_runb)){
				line = line.replaceAll(" ", "");
				if(!isin){
					String BLOCK_NAME = line.substring(Grammar.FT_runb.length(),line.length());
					//System.out.println(BLOCK_NAME);

					BlockMenager.getBlock(BLOCK_NAME).runBlock();
				}
			}
			if(line.startsWith(Grammar.FT_import)) {
				line = line.replaceAll(" ","");
				String NAME = line.substring(line.indexOf(Grammar.FT_import)+Grammar.FT_import.length(),line.length());
				Import.IMPORT_FILE(NAME);
			}
		}
	}

	public static void ReadLine(Collection<String> c) {
		String VALUE;
		String TYPE;
		//LocalValues.printAll();
			for(String line:c) {
				Countline++;
				originLine = line;
				line = line.replaceAll(" ", "").replaceAll("\t","");
				// System.out.println(line);
				// System.out.println(line.indexOf(Grammar.FT_letValueB));
				try {
	
					if (line.startsWith(Grammar.FT_mark)) {
						continue;
					}

					/*
					 * let [NAME] > [VALUE]|([Expression])|%r[origin]
					 */

					if (line.startsWith(Grammar.FT_letValueA)) {
						String VALUE_NAME = line.substring(
								line.indexOf(Grammar.FT_letValueA) + Grammar.FT_letValueA.length(),
								line.indexOf(Grammar.FT_letValueB));

						VALUE = line.substring(line.indexOf(Grammar.FT_letValueB) + 1, line.length());
						//System.out.println(VALUE);
						if(VALUE.startsWith(Grammar.FT_originA)) {
							VALUE = originLine.substring(originLine.indexOf(Grammar.FT_originA) + Grammar.FT_originA.length(),
													originLine.indexOf(Grammar.FT_originB));

							letValue(VALUE_NAME, "str", VALUE);
						} else if (Expression.isNotExpression(VALUE)) {
							TYPE = VALUE.matches("^\\d{0,}") ? "int" : "str";

							// System.out.println("VALUE_NAME="+VALUE_NAME);
							//System.out.println("TYPE="+TYPE);
							
							letValue(VALUE_NAME, TYPE, VALUE);
							
						} else if (VALUE.startsWith(Grammar.FT_expA)) {
							// System.out.println("is exp"+VALUE);
							VALUE = String.valueOf(Expression.solveExpression(Expression.getExpression(VALUE)));
							TYPE = VALUE.matches("\\d+") ? "int" : "str";
							
							letValue(VALUE_NAME, TYPE, VALUE);
						}

					}

					/*
					 * if[Expression] [->] [BLOCK]
					 */

					if (line.startsWith(Grammar.FT_ifConditionA)) {
						String expression = line.substring(
								line.indexOf(Grammar.FT_ifConditionA) + Grammar.FT_ifConditionA.length(),
								line.indexOf(Grammar.FT_ifConditionB));
						
						String back = line.substring(line.indexOf(Grammar.FT_ifConditionB)+Grammar.FT_ifConditionB.length(),line.length());
						
						if(back.length() != 0){
							if(back.startsWith(Grammar.FT_runb2)){
								String block = back.substring(Grammar.FT_runb2.length(),back.length());
								if(Condition.ifCondition(expression)){
									runBlock(block);
								}
							}
						}else{
							System.out.println(Condition.ifCondition(expression));
						}
					}

					/*
					 * print [NAME]|EXPRESSION]
					 */

					if (line.startsWith(Grammar.FT_out)) {

						String VALUE_NAME = line.substring(line.indexOf(Grammar.FT_out) + Grammar.FT_out.length(),
								line.length());

						if (VALUE_NAME.startsWith(Grammar.FT_expA)) {
							int res = Expression.solveExpression(Expression.getExpression(VALUE_NAME));
							letValue(String.valueOf(res), "int", String.valueOf(res));
							VALUE_NAME = String.valueOf(res);
							
						}

						outValue(VALUE_NAME);
					}
					
					/*
					 * run [BLOCK NAME]
					*/
					
					if(line.startsWith(Grammar.FT_runb)){
						String BLOCK_NAME = line.substring(Grammar.FT_runb.length(),line.length());
						//System.out.println(BLOCK_NAME);

						BlockMenager.getBlock(BLOCK_NAME).runBlock();
					}
					
					/*
					 * import [FILE NAME]
					 * */
					 
					 //Can not import in block
					
//					if(line.startsWith(Grammar.FT_import)) {
//						String NAME = line.substring(line.indexOf(Grammar.FT_import)+Grammar.FT_import.length(),line.length());
//						Import.IMPORT_FILE(NAME);
//					}
					
				} catch (StringIndexOutOfBoundsException e) {
					e.printStackTrace();
					// throw new SyntaxError(Countline);
				}
			}
		
	}
	
	static void runBlock(String name){
		BlockMenager.getBlock(name).runBlock();
	}

	static void letValue(String name, String type, String value) {
		switch (type) {
		case "int":
			LocalValues.NumValues.put(name, Integer.parseInt(value));

			break;
		case "str":
			LocalValues.values.put(name, value);

			break;
		}
	}

	static void outValue(String value) {
		if (LocalValues.values.containsKey(value)) {
			System.out.println(LocalValues.values.get(value));
		} else if (LocalValues.NumValues.containsKey(value)) {
			System.out.println(LocalValues.NumValues.get(value));
		}
	}

	/*
	 * The IF statement
	 */

	static void addANumberValue(int num) {
		letValue(String.valueOf(num), "int", String.valueOf(num));
	}

//	 static void addExpressionValue(String exp){
//		
//		System.out.println("add"+exp);
//		
//		letValue(
//			exp,
//			"int",
//			String.valueOf(solveExpression(exp))
//		);
//	}

}
