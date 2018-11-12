package Exceptions;

public class NoSuchBlock extends Error
{
	public NoSuchBlock(String name){
		super("No such block named \""+name+"\"");
	}
}
