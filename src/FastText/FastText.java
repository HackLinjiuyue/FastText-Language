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

public class FastText {
	static File file;
	static BufferedReader reader;
	static int Countline = 0;

	// HashMap LocalValues.values = new HashMap();
	// HashMap <String,Integer> LocalValues.NumValues = new
	// HashMap<String,Integer>();

	public void openAbsFile(String path) {
		if (path.substring(path.lastIndexOf(".") - 1, path.length()).equals("ft")) {

		}
		this.file = new File(path);
		getReader();
		readLineFromFile();
	}

	public void openFile(String path) {
		this.file = new File(path);
		getReader();
		readLineFromFile();
	}

	static void getReader() {
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void readLineFromFile(){
		ArrayList FILE_LINE = new ArrayList();
		String line;
		try
		{
			while ((line = reader.readLine()) != null)
			{
				FILE_LINE.add(line);
			}
			
			ReadLine(FILE_LINE);
		}
		catch (IOException e)
		{}
	}
	

	static void ReadLine(Collection c) {
		
			for(String line:c) {
				Countline++;
				line = line.replaceAll(" ", "");
				// System.out.println(line);
				// System.out.println(line.indexOf(Grammar.FT_letValueB));
				try {

					if (line.startsWith(Grammar.FT_mark)) {
						continue;
					}

					/*
					 * let [NAME] > [VALUE]|([Expression])
					 */

					if (line.startsWith(Grammar.FT_letValueA)) {
						String VALUE_NAME = line.substring(
								line.indexOf(Grammar.FT_letValueA) + Grammar.FT_letValueA.length(),
								line.indexOf(Grammar.FT_letValueB));

						String VALUE = line.substring(line.indexOf(Grammar.FT_letValueB) + 1, line.length());

						// System.out.println("VALUE="+VALUE);
						if (Expression.isNotExpression(VALUE)) {
							String TYPE = VALUE.matches("\\d+") ? "int" : "str";
							letValue(VALUE_NAME, TYPE, VALUE);

							// System.out.println("VALUE_NAME="+VALUE_NAME);

							// System.out.println("TYPE="+TYPE);
						} else if (VALUE.startsWith(Grammar.FT_expA)) {
							// System.out.println("is exp"+VALUE);
							VALUE = String.valueOf(Expression.solveExpression(Expression.getExpression(VALUE)));
							String TYPE = VALUE.matches("\\d+") ? "int" : "str";
							letValue(VALUE_NAME, TYPE, VALUE);
						}

					}

					/*
					 * if[Expression]
					 */

					if (line.startsWith(Grammar.FT_ifConditionA)) {
						String expression = line.substring(
								line.indexOf(Grammar.FT_ifConditionA) + Grammar.FT_ifConditionA.length(),
								line.indexOf(Grammar.FT_ifConditionB));
						System.out.println(Condition.ifCondition(expression));

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

				} catch (StringIndexOutOfBoundsException e) {
					e.printStackTrace();
					// throw new SyntaxError(Countline);
				}
			}
		
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
