package FastText;

import java.util.HashMap;
import Exceptions.*;

public class LocalValues {
	public static HashMap<String,String> values = new HashMap<String,String>();
	public static HashMap <String,Integer> NumValues = new HashMap<String,Integer>();
	
	public static void del(String name){
		if(values.containsKey(name)){
			values.remove(name);
		}else if(NumValues.containsKey(name)){
			NumValues.remove(name);
		}else{
			throw new NameNotDefine(name);
		}
	}
	
	public static void printAll(){
		System.out.print("All public VALUE=");
		for(String c:values.keySet()){
			System.out.print(c+" ");
		}
		System.out.println("");
		System.out.print("All public NUM_VALUE=");
		
		for(String c:NumValues.keySet()){
			System.out.print(c+" ");
		}
		
		System.out.println("");
	}
}




