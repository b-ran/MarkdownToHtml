package processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Console {

    private static final String fileFormat = ".txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter File Path To Convert: ");
        String in = scanner.nextLine();

        Interpreter interpreter = new Interpreter(in);
        write(in, interpreter.convert());
    }

    private static void write(String in, StringBuilder stringBuilder) {
        File fileOut = new File(in.substring(0,in.length()-fileFormat.length()) + "-convert" + ".html");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut))) {
            System.out.println(stringBuilder.toString());
            bufferedWriter.append(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
