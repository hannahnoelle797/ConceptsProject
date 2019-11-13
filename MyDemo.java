//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.Scanner;

/**
 * The MyDemo class contains the main method of this project. It creates a
 * MyScanner object with the two file names entered and calls the public method
 * Scan to scan the inputed file. A MyParser object is created with the input
 * file name and the public method parse is called. The user then has the option
 * to run another file through the scanner and parser.
 */
public class MyDemo {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String tryagain = "yes";

        do {
            System.out.print("Enter name of Julia file with .txt or .jl extension: ");
            String filename = kb.nextLine();

            System.out.print("Enter name of output file with .txt extension: ");
            String output = kb.nextLine();

            MyScanner ms = new MyScanner(filename, output);

            ms.Scan();

            MyParser mp = new MyParser(filename);
            mp.Parse();

            System.out.print("Do you want to do another file? (Yes/No) ");
            tryagain = kb.nextLine();

        } while (tryagain.equalsIgnoreCase("yes"));
        kb.close();
    }
}
