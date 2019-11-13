//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The MyParser class calls all the sub-classes for each LHS abstraction of the
 * Julia grammar via the block class. It grabs all lines of the file and passes
 * them to a single block instance. This class does not use the output from the
 * MyScanner class. It uses the original file inputed that contains the Julia
 * code.
 */
public class MyParser {
    private ArrayList<String> allLines = new ArrayList<>();
    private Scanner fileRead;
    private String filename;
    private block fileBlock;

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

            fileBlock = new block(allLines);
            fileBlock.toGrammar();

            fileRead.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}