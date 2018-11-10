package FastText;

import Exceptions.NameNotDefine;
import Exceptions.SyntaxError;

public class Expression {
	static String getExpression(String str){
		
		String exp = str.substring(
			str.indexOf(Grammar.FT_expA)+Grammar.FT_expA.length(),
			str.indexOf(Grammar.FT_expB)
			);
		//System.out.println(exp.replaceAll("[A-Za-z0-9]",""));
			
		if(
				exp.replaceAll("[A-Za-z0-9]","").matches("\\"+Grammar.FT_plus)||
				exp.replaceAll("[A-Za-z0-9]","").matches(Grammar.FT_div)||
				exp.replaceAll("[A-Za-z0-9]","").matches("\\"+Grammar.FT_mult)||
				exp.replaceAll("[A-Za-z0-9]","").matches(Grammar.FT_sub)){
				
			//exp = exp.replaceAll("\\(\\)","");
			return exp;
			//System.out.println("e="+exp);
		}else{
			return getExpression(exp);
		}
		
	}
	
	static int solveExpression(String expression){
		expression = expression.replaceAll(" ","");
		String symbol = expression.replaceAll("[a-zA-Z]","");
		//System.out.println(expression);
		String[] PART;
		
		try
		{
			switch (symbol)
			{
				case Grammar.FT_plus:
					PART = expression.split("\\" + Grammar.FT_plus);
					return LocalValues.NumValues.get(PART[0]) + LocalValues.NumValues.get(PART[1]);

				case Grammar.FT_sub:
					PART = expression.split(Grammar.FT_sub);
					return LocalValues.NumValues.get(PART[0]) - LocalValues.NumValues.get(PART[1]);

				case Grammar.FT_mult:
					PART = expression.split("\\" + Grammar.FT_mult);
					return LocalValues.NumValues.get(PART[0]) * LocalValues.NumValues.get(PART[1]);

				case Grammar.FT_div:
					PART = expression.split(Grammar.FT_div);
					return LocalValues.NumValues.get(PART[0]) / LocalValues.NumValues.get(PART[1]);

				default:
					System.out.println("s="+symbol);
					throw new SyntaxError();
			}
		}
		catch (NullPointerException e)
		{
			throw new NameNotDefine();
		}
	}
	
	static boolean isNotExpression(String part){
		part = part.replaceAll(" ","");
		//System.out.print(part+"==");
		//System.out.println(part.replaceAll("[A-Za-z0-9]","").length() == 0);
		return part.replaceAll("[A-Za-z0-9]","").length() == 0;
	}
}
