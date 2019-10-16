import java.String;
import java.io.*;

public class ScannerMary {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new FileReader("D:\\Fall Semester 2019\\CS 4308 Concepts\\Project\\ViewAttachment.txt"));

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