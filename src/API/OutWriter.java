package API;
import java.io.*;

public class OutWriter
{
	private static PrintWriter WRITER = new PrintWriter(System.out,true);
	private static PrintWriter STREAM;
	
	public static void set_stream(OutputStream out){
		STREAM = new PrintWriter(out,true);
	}
	
	public static void print_to_console(String str){
		WRITER.print(str);
		WRITER.flush();
	}
	
	public static void println_to_console(String str){
		WRITER.println(str);
		WRITER.flush();
	}
	
	public static void println_with_stream(String str){
		if(STREAM != null){
			STREAM.println(str);
			STREAM.flush();
		}
	}
	
	public static void print_with_stream(String str){
		if(STREAM != null){
			STREAM.print(str);
			STREAM.flush();
		}
	}
}
