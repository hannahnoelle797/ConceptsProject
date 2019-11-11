import java.util.Scanner;

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
