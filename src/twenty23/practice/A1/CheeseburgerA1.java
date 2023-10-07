package twenty23.practice.A1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CheeseburgerA1 { // 6 min

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "practice", "A1");

    public static void main(String[] args) throws IOException {
        BufferedReader buff = Files.newBufferedReader(RES_FOLDER.resolve("input.txt"));
        int numOfCases = Integer.parseInt(buff.readLine());

        for (int i = 0; i < numOfCases; i++) {
            String line = buff.readLine();
            String[] array = line.split(" ");
            int s = Integer.parseInt(array[0]);
            int d = Integer.parseInt(array[1]);
            int k = Integer.parseInt(array[2]);

            boolean solution = solve(s, d, k);
            outputLines.add("Case #" + (i + 1) + ": " + (solution ? "YES" : "NO"));
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }

    private static boolean solve(int s, int d, int k) {
        int buns = s * 2 + d * 2;
        int patties = s + 2 * d;
        if (patties < k) return false;
        if (k == 1) return buns >= k;

        return buns - 1 >= k;
    }

    private static void writeOutputToFile(Path file) throws IOException {
        String defaultSystemSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");
        Files.write(file, outputLines);
        System.setProperty("line.separator", defaultSystemSeparator);
    }
}
