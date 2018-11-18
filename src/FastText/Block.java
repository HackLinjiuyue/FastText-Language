package FastText;
import java.util.*;

public class Block
{
	//String name;
	private ArrayList<String> lines;
	
	public Block(){
		
		this.lines = new ArrayList<String>();
	}
	
	public void addLine(String line){
		lines.add(line);
	}
	
	public void runBlock(){
		BlockReader.ReadLine(this.lines);
	}
}
