import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Demo {

    static FileWriter fw;
    static int lineNum = 0;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        MyScanner ms = new MyScanner();
        String[] l;
        String[] newInt;
        String tryagain = "yes";

        do {
            lineNum = 0;
            System.out.print("Enter name of Julia file with .txt extension: ");
            String filename = kb.nextLine();
            try {
                File file = new File(filename);

                System.out.print("Enter name of output file with .txt extension: ");
                String output = kb.nextLine();
                Scanner fileRead = new Scanner(file);
                try {
                    fw = new FileWriter(output);

                    while (fileRead.hasNextLine()) {
                        lineNum++;
                        String line = fileRead.nextLine();
                        String[] parts = line.split(" ");
                        for (int i = 0; i < parts.length; i++) {
                            if (parts[i].length() > 0) {
                                if (ms.if_exists(parts[i])) {
                                    String[] currLex = new String[2];
                                    currLex = ms.returnLexeme();
                                    addData(currLex);
                                } else {
                                    if (isInt(parts[i])) {
                                        String[] data = new String[2];
                                        data = ms.setUserVariable(parts[i], "Integer");
                                        addData(data);
                                    } else {
                                        if (parts[i].length() == 1) {
                                            String[] data = new String[2];
                                            data = ms.setUserVariable(parts[i], "Variable");
                                            addData(data);
                                        } else {
                                            String part = parts[i];
                                            int start = 0;
                                            char first = part.charAt(0);
                                            String sub = Character.toString(first);
                                            for (int j = 0; j < part.length(); j++) {
                                                sub = part.substring(start, (j + 1));
                                                if (ms.if_exists(sub)) {
                                                    String[] data = new String[2];
                                                    data = ms.returnLexeme();
                                                    addData(data);
                                                    start = j + 1;
                                                } else {
                                                    String last = Character.toString(sub.charAt(sub.length() - 1));
                                                    if (ms.if_exists(last)) {
                                                        String newSub = sub.substring(0, sub.length() - 1);
                                                        if (isInt(newSub)) {
                                                            newInt = new String[2];
                                                            newInt = ms.setUserVariable(newSub, "Integer");
                                                            addData(newInt);
                                                        } else {
                                                            l = new String[2];
                                                            l = ms.setUserVariable(newSub, "Variable");
                                                            addData(l);
                                                        }
                                                        l = new String[2];
                                                        l = ms.returnLexeme();
                                                        addData(l);
                                                        start = j + 1;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                fileRead.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.print("Run again with new file? (Yes/No) ");
            tryagain = kb.nextLine();
        } while (tryagain.equalsIgnoreCase("yes"));

        // printArrayList(ms, lexemes);

        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        kb.close();
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void addData(String[] data) {
        try {
            String lex = data[0];
            String type = data[1];
            String out = "Line Number: " + lineNum + "\tLexeme: " + lex + "\tToken: " + type + "\n";
            System.out.print(out);
            fw.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}