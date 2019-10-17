import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        String[][] lexeme;
        String type = "user variable";
        ArrayList<String[][]> lexemes = new ArrayList<>();

        MyScanner ms = new MyScanner();

        System.out.print("Enter name of Julia file with .txt extension: ");
        String filename = kb.nextLine();
        try {
            File file = new File(filename);
            Scanner fileRead = new Scanner(file);

            while (fileRead.hasNextLine()) {
                String data = fileRead.nextLine();
                int start = 0;
                String sub = "", prevLex = "";
                for (int i = 0; i < data.length(); i++) {
                    System.out.println("Previous Lexeme: " + prevLex);
                    sub = data.substring(start, (i + 1));
                    lexeme = ms.if_exists(sub);
                    if (sub.length() > 1) {
                        if (!lexeme[0][0].equalsIgnoreCase("DNE")) {
                            lexemes.add(lexeme);
                            prevLex = ms.getLexeme(lexeme);
                            start = i + 1;
                        } else {
                            String[][] last = ms.if_exists(Character.toString(sub.charAt(sub.length() - 1)));
                            if (!last[0][0].equalsIgnoreCase("DNE")) {
                                sub = data.substring(start, i);
                                if (prevLex.equals("\""))
                                    type = "string";
                                lexeme = ms.setUserVariable(sub, type);
                                lexemes.add(lexeme);
                                lexemes.add(last);
                                prevLex = ms.getLexeme(lexeme);
                                start = i + 1;
                            }
                        }
                    } else {
                        if (!lexeme[0][0].equalsIgnoreCase("DNE")) {
                            lexemes.add(lexeme);
                            prevLex = ms.getLexeme(lexeme);
                            start = i + 1;
                        }
                    }
                }
            }

            fileRead.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        printArrayList(ms, lexemes);

        kb.close();
    }

    public static void printArrayList(MyScanner ms, ArrayList<String[][]> arrList) {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter name of output file with .txt extension: ");
        String output = kb.nextLine();

        try {
            FileWriter fw = new FileWriter(output);

            for (int i = 0; i < arrList.size(); i++) {
                String[][] lex = arrList.get(i);
                String type = lex[0][0];
                String out = "Lexeme: " + ms.getLexeme(lex) + "\tToken: " + type + "\n";
                System.out.print(out);
                fw.write(out);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        kb.close();
    }
}