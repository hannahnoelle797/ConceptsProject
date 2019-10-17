import java.io.*;
import java.util.Scanner;

/** hynes scratch code for scanner 
   FileName : "myScanner.java". */
class myScanner 
{ 
    public static void main(String args[]) //throws FileNotFoundException
    { 
        File inputFile = new File("C:\\Users\\ch027s\\Desktop\\juliaSimple1.txt");
   //inputFile.close();
    //out.close();
    }

	public static void main(String[] args) throws IOException 
	 { 
	     File julia = OpenAndReadFile.inputFile(); 
	     File read = OpenAndReadFile.readFile();
	     PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(read))); 
	     out.println("\"Hello, World!\""); 
	     out.close(); 
	
	     BufferedReader in = new BufferedReader(new FileReader(julia)); 
	     while (in.hasNext())
	     {
	        String str = in.next();
	        System.out.println(str);  
	     }
	     
	 }