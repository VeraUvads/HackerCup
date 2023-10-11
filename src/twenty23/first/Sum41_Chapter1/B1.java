package twenty23.first.Sum41_Chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class B1 {

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "first", "Sum41_Chapter1");
    private static boolean solved = false;
    private static boolean possible = true;

    public static void main(String[] args) throws IOException {
        BufferedReader buff = Files.newBufferedReader(RES_FOLDER.resolve("input.txt"));
        int numOfCases = Integer.parseInt(buff.readLine());

        for (int i = 0; i < numOfCases; i++) {
            String line = buff.readLine();
            String[] array = line.split(" ");
            int p = Integer.parseInt(array[0]);
            solved = false;
            ArrayList<Integer> solution = solve(p, 41);
            if (solution == null) {
                outputLines.add("Case #" + (i + 1) + ": " + -1);
            } else {
                StringBuilder builder = new StringBuilder();
                builder.append(solution.size());
                for (int number : solution) {
                    builder.append(" ");
                    builder.append(number);
                }
                outputLines.add("Case #" + (i + 1) + ": " + builder);
            }
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }

    private static ArrayList<Integer> solve(int product, int start) {

        ArrayList<Integer> dividers = new ArrayList<>();
        int[] simpleNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
        int minSum = 0;

        for (int i = 0; i < simpleNumbers.length; ) {
            int divider = simpleNumbers[i];
            if (product % divider == 0) {
                product /= divider;
                minSum += divider;
                dividers.add(divider);
                if (product == 1) {
                    break;
                }
            } else {
                ++i;
            }
        }

        if (product != 1 || minSum > start) {
            return null;
        }

        while (minSum < 41) {
            dividers.add(1);
            minSum++;
        }
        return dividers;
    }

    private static void writeOutputToFile(Path file) throws IOException {
        String defaultSystemSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");

        Files.write(file, outputLines);

        System.setProperty("line.separator", defaultSystemSeparator);
    }
}
