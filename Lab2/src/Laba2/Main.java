package Laba2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name of file .csv ");
        String fileIn = scanner.next();
        System.out.print("\nEnter delim in .csv: ");
        String delimInp = scanner.next();
        System.out.print("\nEnter a file name to output .txt: ");
        String fileOut = scanner.next();
        System.out.print("\nEnter delim in .txt: ");
        String delimOutp = scanner.next();
        Laba2 parser = new Laba2(delimInp, delimOutp);
        parser.fileParsing(fileIn, fileOut);
    }
}
