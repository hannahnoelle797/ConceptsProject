 import java.io.*; 
 
 /** First phase project
  * Scanner program that carries out the following tasks: 
  *      Opens a file with the name juliaSimple1.txt. 
  *      Stores a message &quot;Hello, World!&quot; in the file. 
  *      Close the file. 
  *      Open the same file again. 
  *      Read the message into a string variable and print it. 
  */ 
 public class OpenAndReadFile 
 { 
     public static void main(String[] args) throws IOException 
     { 
         File julia = inputFile(); 
         File read = readFile();
         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(read))); 
         out.println("\"Hello, World!\""); 
         out.close(); 
 
         BufferedReader in = new BufferedReader(new FileReader(julia)); 
         while (in.hasNextLine())
         {
            String str = in.nextLine();
            System.out.println(str);  
         }
         
     } 
 
     /** 
      * Gets the hello.txt file. 
      * 
      * @return the file object 
      */ 
     public static File inputFile() 
     { 
         return new File("C:\\Users\\ch027s\\Desktop\\juliaSimple1.txt"); 
     } 
     public static File readFile()
     {
         return new File("C:\\Users\\ch027s\\Desktop\\hello.txt");
     }
} 
