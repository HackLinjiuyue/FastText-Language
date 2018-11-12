package FastText;

import java.util.HashMap;

public class LocalValues {
	public static HashMap values = new HashMap();
	public static HashMap <String,Integer> NumValues = new HashMap<String,Integer>();
	
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




