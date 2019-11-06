import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class block {
    private ArrayList<statement> fileStatements = new ArrayList<>();
    private ArrayList<String> allFileLines = new ArrayList<>();
    private FileWriter fw;

    public block(String filename) {
        try {
            boolean keepGoing = true;
            File file = new File(filename);
            Scanner fileRead = new Scanner(file);
            String output_file = "parsed_" + filename;
            fw = new FileWriter(output_file);

            while (fileRead.hasNextLine()) {
                String line = fileRead.nextLine();
                if (line.charAt(0) == 'F') {
                    line = line.substring((line.indexOf("~") + 1));
                    allFileLines.add(line);
                }
            }

            fw.close();
            fileRead.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}