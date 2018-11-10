package Exceptions;

public class SymbolSyntaxError extends Error
{
	public SymbolSyntaxError(String bad){
		super("Use the "+bad+" symbol in a expression");
	}
}
