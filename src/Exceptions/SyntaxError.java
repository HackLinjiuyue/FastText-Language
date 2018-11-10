package Exceptions;

public class SyntaxError extends Error
{
	public SyntaxError(int l){
		super("invalid syntax at line:"+l);
	}
	public SyntaxError(){
		super("invalid syntax");
	}
}
