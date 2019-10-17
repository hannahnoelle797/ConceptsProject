import java.io.*;
import java.util.Scanner;

public class MyScanner3 {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = null;
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter input file name with .txt. extension: ");
        String filename = kb.nextLine();

        try {
            reader = new BufferedReader(new FileReader(filename));

            int lineNumber = 1;
            String line = reader.readLine();
            while (line != null) {

                String[] lineChars = line.split(" ");
                for (String item : lineChars) {
                    System.out.println("Line Reader: " + lineNumber + "\t Lexeme: " + item + "\t Token: ");
                }
                line = reader.readLine();
                lineNumber++;
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}