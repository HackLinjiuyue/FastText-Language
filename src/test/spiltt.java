package test;

public class spiltt
{
	public static void main(String[] ar){
		String a = "a(b)";
		a = a.replace(" ","");
		
		System.out.println(a);
		a = a.replaceAll("[a-zA-Z0-9]","");
		System.out.println(a);
		System.out.println(a.replaceAll("([^']+)",""));
//		String[] part = a.split("==");
//		for(String p:part){
//			System.out.println(p);
//		}
	}
}
