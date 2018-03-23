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
        if (args.length == 0) throw new IllegalArgumentException("Need 1 parameter for file path");

        Interpreter interpreter = new Interpreter(args[0]);
        System.out.println("Converting:" + args[0]);
        write(args[0], interpreter.convert());
        System.out.println("\n\n\n");
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
