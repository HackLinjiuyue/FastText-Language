import Exceptions.*;
import java.io.*;
import java.util.*;

/*
*======================================
*=         FastText Language          =
*=                                    =
*=           Written by CHB           =
*======================================
*/

public class FastText
{
	private File file;
	private BufferedReader reader;
	private int Countline = 0;
	
	private HashMap values = new HashMap();
	private HashMap <String,Integer> NumValues = new HashMap<String,Integer>();
	
	public void openAbsFile(String path){
		if(path.substring(path.lastIndexOf(".")-1,path.length()).equals("ft")){
			
		}
		this.file = new File(path);
		getReader();
		ReadLine();
	}
	public void openFile(String path){
		this.file = new File(path);
		getReader();
		ReadLine();
	}
	
	private void getReader(){
		try
		{
			reader = new BufferedReader( new FileReader(this.file));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private void ReadLine(){
		String line;
		try
		{
			while ((line = reader.readLine()) != null)
			{
				Countline ++;
				line = line.replaceAll(" ","");
				//System.out.println(line);
				//System.out.println(line.indexOf(Grammar.FT_letValueB));
				try{
					
					if(line.startsWith(Grammar.FT_mark)){
						continue;
					}
					
					/*
					 * let [NAME] > [VALUE]|([Expression])
					*/
					
					
					if(line.startsWith(Grammar.FT_letValueA)){
						String VALUE_NAME = 
							line.substring(
								line.indexOf(Grammar.FT_letValueA)+ Grammar.FT_letValueA.length(),
								line.indexOf(Grammar.FT_letValueB)
								);
						
						String VALUE = line.substring( line.indexOf(Grammar.FT_letValueB)+1,line.length());
					
						//System.out.println("VALUE="+VALUE);
						if(isNotExpression(VALUE)){
							String TYPE = VALUE.matches("\\d+")?"int":"str";
							letValue(VALUE_NAME,TYPE,VALUE);
							
							//System.out.println("VALUE_NAME="+VALUE_NAME);
							
							//System.out.println("TYPE="+TYPE);
						}else if(VALUE.startsWith(Grammar.FT_expA)){
							//System.out.println("is exp"+VALUE);
							VALUE = String.valueOf(solveException(getExpression(VALUE)));
							String TYPE = VALUE.matches("\\d+")?"int":"str";
							letValue(VALUE_NAME,TYPE,VALUE);
						}
						
						
						
					}
					
					/*
					 * if[Expression]
					*/
					
					if(line.startsWith(Grammar.FT_ifConditionA)){
						String expression = 
							line.substring(
							line.indexOf(Grammar.FT_ifConditionA)+Grammar.FT_ifConditionA.length(),
							line.indexOf(Grammar.FT_ifConditionB));
						System.out.println(ifCondition(expression));
						
					}
					
					/*
					* print [NAME]|EXPRESSION]
					*/
					
					if(line.startsWith(Grammar.FT_out)){
						
						
						String VALUE_NAME = line.substring(
							line.indexOf(Grammar.FT_out)+Grammar.FT_out.length(),
							line.length());
						
							
						if(VALUE_NAME.startsWith(Grammar.FT_expA)){
							int res = solveException(getExpression(VALUE_NAME));
							letValue(String.valueOf(res),"int",String.valueOf(res));
							VALUE_NAME = String.valueOf(res);
						}
						
						
						outValue(VALUE_NAME);
					}
					
					
					
				}catch(StringIndexOutOfBoundsException e){
					e.printStackTrace();
					//throw new SyntaxError(Countline);
				}
			}
		}
		catch (IOException e)
		{}
	}
	
	private void letValue(String name,String type,String value){
		switch(type){
			case "int":
				NumValues.put(name,Integer.parseInt(value));
			
				break;
			case "str":
				values.put(name,value);
				
				break;
		}
	}
	
	private void outValue(String value){
		if(values.containsKey(value)){
			System.out.println(values.get(value));
		}
		else if(NumValues.containsKey(value)){
			System.out.println(NumValues.get(value));
		}
	}
	
	/*
	 *The IF statement
	*/
	
	private void addANumberValue(int num){
		letValue(String.valueOf(num),"int",String.valueOf(num));
	}
	
	private void addExpressionValue(String exp){
		
		System.out.println("add"+exp);
		
		letValue(
			exp,
			"int",
			String.valueOf(solveException(exp))
		);
	}
	
	private String getExpression(String str){
		
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
	
	private int solveException(String expression){
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
					return NumValues.get(PART[0]) + NumValues.get(PART[1]);

				case Grammar.FT_sub:
					PART = expression.split(Grammar.FT_sub);
					return NumValues.get(PART[0]) - NumValues.get(PART[1]);

				case Grammar.FT_mult:
					PART = expression.split("\\" + Grammar.FT_mult);
					return NumValues.get(PART[0]) * NumValues.get(PART[1]);

				case Grammar.FT_div:
					PART = expression.split(Grammar.FT_div);
					return NumValues.get(PART[0]) / NumValues.get(PART[1]);

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
	
	private boolean isNotExpression(String part){
		part = part.replaceAll(" ","");
		//System.out.print(part+"==");
		//System.out.println(part.replaceAll("[A-Za-z0-9]","").length() == 0);
		return part.replaceAll("[A-Za-z0-9]","").length() == 0;
	}
	
	private boolean ifCondition(String condition){
		
		//delete space
		//System.out.println(condition);
		condition = condition.replaceAll(" ","");
		String symbol = condition.replaceAll("[a-zA-Z]","")
													.replaceAll("\\+","")
													.replaceAll("\\-","")
													.replaceAll("\\*","")
													.replaceAll("\\/","");
		//System.out.println(symbol);
		switch(symbol){
			case Grammar.FT_equal:
				String[] PARTS = condition.split(Grammar.FT_equal);
				
				
				//System.out.println(PARTS[0]);
				if(isNotExpression(PARTS[0]) && isNotExpression(PARTS[1])){
					
					return 
						NumValues.containsKey(PARTS[0])&&NumValues.containsKey(PARTS[1])?
						NumValues.get(PARTS[0]).equals(NumValues.get(PARTS[1]))
						:
						values.get(PARTS[0]).equals(values.get(PARTS[1]));
						
				}else{
					if(!isNotExpression(PARTS[0]) && isNotExpression(PARTS[1])){
						return
							!isNotExpression(PARTS[0]) && isNotExpression(PARTS[1])?
							((Integer)solveException(PARTS[0])).equals(NumValues.get(PARTS[1])):
							((Integer)solveException(PARTS[1])).equals(NumValues.get(PARTS[0]));
						
					}else if(!isNotExpression(PARTS[0]) && !isNotExpression(PARTS[1])){
						return !isNotExpression(PARTS[0]) && !isNotExpression(PARTS[1])?
							((Integer)solveException(PARTS[0])).equals(solveException(PARTS[1])):
							false;
					}else{return false;}
					
				}
				
				
				
				
			case Grammar.FT_unequal:
				String[] PARTS_ = condition.split(Grammar.FT_unequal);

				if(isNotExpression(PARTS_[0]) && isNotExpression(PARTS_[1])){
					return 
					NumValues.containsKey(PARTS_[0])&&NumValues.containsKey(PARTS_[1])?
					!NumValues.get(PARTS_[0]).equals(NumValues.get(PARTS_[1]))
					:
					!values.get(PARTS_[0]).equals(values.get(PARTS_[1]));
				}else{
					if(!isNotExpression(PARTS_[0]) && isNotExpression(PARTS_[1])){
						return
							!isNotExpression(PARTS_[0]) && isNotExpression(PARTS_[1])?
							((Integer)solveException(PARTS_[0])).equals(NumValues.get(PARTS_[1])):
							((Integer)solveException(PARTS_[1])).equals(NumValues.get(PARTS_[0]));
						
					}else if(!isNotExpression(PARTS_[0]) && !isNotExpression(PARTS_[1])){
						return !isNotExpression(PARTS_[0]) && !isNotExpression(PARTS_[1])?
							!((Integer)solveException(PARTS_[0])).equals(solveException(PARTS_[1])):
							false;
					}
					
				}
				
				
				
			case Grammar.FT_elager:
				String[] _PARTS = condition.split(Grammar.FT_elager);

				if(isNotExpression(_PARTS[0]) && isNotExpression(_PARTS[1])){
					return 
						NumValues.containsKey(_PARTS[0])&&NumValues.containsKey(_PARTS[1])?
							NumValues.get(_PARTS[0]) 
							>= 
							NumValues.get(_PARTS[1]):false;
				}else{
					if(!isNotExpression(_PARTS[0]) && isNotExpression(_PARTS[1])){
						return
							!isNotExpression(_PARTS[0]) && isNotExpression(_PARTS[1])?
							((Integer)solveException(_PARTS[0])) >= (NumValues.get(_PARTS[1])):
							((Integer)solveException(_PARTS[1])) >= (NumValues.get(_PARTS[0]));
						
					}else if(!isNotExpression(_PARTS[0]) && !isNotExpression(_PARTS[1])){
						return !isNotExpression(_PARTS[0]) && !isNotExpression(_PARTS[1])?
							((Integer)solveException(_PARTS[0])) >= (solveException(_PARTS[1])):
							false;
					}
					
				}
				
				
			case Grammar.FT_esmaller:
				String[] _PARTS_ = condition.split(Grammar.FT_esmaller);

				if(isNotExpression(_PARTS_[0]) && isNotExpression(_PARTS_[1])){
					return 
						NumValues.containsKey(_PARTS_[0])&&NumValues.containsKey(_PARTS_[1])?
						NumValues.get(_PARTS_[0])
						<= 
						NumValues.get(_PARTS_[1]):false;
				}else{
					if(!isNotExpression(_PARTS_[0]) && isNotExpression(_PARTS_[1])){
						return
							!isNotExpression(_PARTS_[0]) && isNotExpression(_PARTS_[1])?
							((Integer)solveException(_PARTS_[0])) <= (NumValues.get(_PARTS_[1])):
							((Integer)solveException(_PARTS_[1])) <= (NumValues.get(_PARTS_[0]));

					}else if(!isNotExpression(_PARTS_[0]) && !isNotExpression(_PARTS_[1])){
						return !isNotExpression(_PARTS_[0]) && !isNotExpression(_PARTS_[1])?
							((Integer)solveException(_PARTS_[0])) <= (solveException(_PARTS_[1])):
							false;
					}
				}
				
				
				
			case Grammar.FT_smaller:
				String[] _PARTS__ = condition.split(Grammar.FT_smaller);

				if(isNotExpression(_PARTS__[0]) && isNotExpression(_PARTS__[1])){
					return 
						NumValues.containsKey(_PARTS__[0])&&NumValues.containsKey(_PARTS__[1])?
						NumValues.get(_PARTS__[0]) 
						< 
						NumValues.get(_PARTS__[1]):false;
				}else{
					if(!isNotExpression(_PARTS__[0]) && isNotExpression(_PARTS__[1])){
						return
							!isNotExpression(_PARTS__[0]) && isNotExpression(_PARTS__[1])?
							((Integer)solveException(_PARTS__[0])) < (NumValues.get(_PARTS__[1])):
							((Integer)solveException(_PARTS__[1])) < (NumValues.get(_PARTS__[0]));

					}else if(!isNotExpression(_PARTS__[0]) && !isNotExpression(_PARTS__[1])){
						return !isNotExpression(_PARTS__[0]) && !isNotExpression(_PARTS__[1])?
							((Integer)solveException(_PARTS__[0])) < (solveException(_PARTS__[1])):
							false;
					}
				}
				
				
		
			case Grammar.FT_larger:
				String[] __PARTS = condition.split(Grammar.FT_larger);

				if(isNotExpression(__PARTS[0]) && isNotExpression(__PARTS[1])){
					return 
					NumValues.containsKey(__PARTS[0])&&NumValues.containsKey(__PARTS[1])?
						NumValues.get(__PARTS[0]) 
						>
						NumValues.get(__PARTS[1]):false;
				}else{
					if(!isNotExpression(__PARTS[0]) && isNotExpression(__PARTS[1])){
						return
						!isNotExpression(__PARTS[0]) && isNotExpression(__PARTS[1])?
						((Integer)solveException(__PARTS[0])) > (NumValues.get(__PARTS[1])):
					((Integer)solveException(__PARTS[1])) > (NumValues.get(__PARTS[0]));

				}else if(!isNotExpression(__PARTS[0]) && !isNotExpression(__PARTS[1])){
			return !isNotExpression(__PARTS[0]) && !isNotExpression(__PARTS[1])?
				((Integer)solveException(__PARTS[0])) > (solveException(__PARTS[1])):
		false;
	}
				}
				
			default:
				throw new SymbolSyntaxError(symbol);
		}
	}
	
}
