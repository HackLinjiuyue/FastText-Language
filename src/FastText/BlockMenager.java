package FastText;
import java.util.*;
import Exceptions.*;

public class BlockMenager
{
	private static HashMap<String,Block> blocks = new HashMap<String,Block>();
	
	public static void addBlock(String name,Block b){
		blocks.put(name,b);
	}
	
	public static Block getBlock(String name){
		//printAll();
		if(blocks.containsKey(name)){
			return blocks.get(name);
		}
		else{
			throw new NoSuchBlock(name);
		}
	}
	private static void printAll(){
		System.out.print("All BLOCK =");
		for(String c:blocks.keySet()){
			System.out.print(c+" ");
		}
		System.out.println("");
	}
}
