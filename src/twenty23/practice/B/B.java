package twenty23.practice.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class B {

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "practice", "B");

    public static void main(String[] args) throws IOException {
        BufferedReader buff = Files.newBufferedReader(RES_FOLDER.resolve("input.txt"));
        int numOfCases = Integer.parseInt(buff.readLine());

        for (int i = 0; i < numOfCases; i++) {
            String line = buff.readLine();
            String[] array = line.split(" ");
            int row = Integer.parseInt(array[0]);
            int col = Integer.parseInt(array[1]);
            int aliceToDown = Integer.parseInt(array[2]);
            int bobToRight = Integer.parseInt(array[2]);

            boolean solution = solve(row, col, aliceToDown, bobToRight);
            outputLines.add("Case #" + (i + 1) + ": " + (solution ? "YES" : "NO"));
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }

    private static boolean solve(int row, int col, int aliceToDown, int bobToRight) {
        return col < row;
    }

    private static void writeOutputToFile(Path file) throws IOException {
        String defaultSystemSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");

        Files.write(file, outputLines);

        System.setProperty("line.separator", defaultSystemSeparator);
    }
}
