import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MyParser {
    private ArrayList<block> allBlocks = new ArrayList<>();
    private ArrayList<String[]> userVariables = new ArrayList<>();
    private boolean keepGoing = true;
    int lineStart, lineEnd;

    public MyParser(String filename) {
        try {
            File file = new File(filename);
            Scanner fileRead = new Scanner(file);

            do {
                keepGoing = false;
                lineStart = fileRead.nextInt();
                String line = fileRead.nextLine();
                if (line.contains("(")) {

                }
            } while (keepGoing);

            fileRead.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}