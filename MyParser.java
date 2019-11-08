import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MyParser {
    private ArrayList<String> allLines = new ArrayList<>();
    private Scanner fileRead;
    private String filename;

    public MyParser(String filename) {
        this.filename = filename;
    }

    public void Parse() {
        try {
            File file = new File(filename);
            fileRead = new Scanner(file);

            while (fileRead.hasNextLine()) {
                allLines.add(fileRead.nextLine().trim());
            }

            block fileBlock = new block(allLines);
            fileBlock.toGrammar();

            fileRead.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}