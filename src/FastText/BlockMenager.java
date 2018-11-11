package FastText;
import java.util.*;

public class BlockMenager{
	
	private static HashMap<String,Block> all_blocks;
	
	public static void addBlock(String name,Block b){
		all_blocks.put(name,b);
	}
	
	public static Block getBlock(String name){
		return all_blocks.get(name);
	}
}
