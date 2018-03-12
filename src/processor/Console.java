package processor;

import java.io.File;
import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter File Path To Convert: ");
//        String in = scanner.nextLine();
//        System.out.println(in);
        Interpreter interpreter = new Interpreter("test.txt");
        interpreter.convert();
//        StringBuilder stringBuilder = new StringBuilder(interpreter.convert());



    }
}
