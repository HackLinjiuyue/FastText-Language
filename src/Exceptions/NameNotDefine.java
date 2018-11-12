package Exceptions;
import FastText.*;

public class NameNotDefine extends Error
{
	public NameNotDefine(){
		super("at:"+Share.lineCount+" Name is not define");
	}
}
