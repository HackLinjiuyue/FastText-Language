package Exceptions;
import FastText.*;

public class NameNotDefine extends Error
{
	public NameNotDefine(){
		super("at:"+Share.lineCount+" Name is not define");
	}
	public NameNotDefine(String name){
		super("at:"+Share.lineCount+" Name \""+name+"\" is not define");
	}
}
