package twenty23.first.Sum41_Chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class B2 {

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "first", "Sum41_Chapter2");
    private static ArrayList<Integer> output;


    public static void main(String[] args) throws IOException {
        BufferedReader buff = Files.newBufferedReader(RES_FOLDER.resolve("input.txt"));
        int numOfCases = Integer.parseInt(buff.readLine());

        for (int i = 0; i < numOfCases; i++) {
            String line = buff.readLine();
            String[] array = line.split(" ");
            int p = Integer.parseInt(array[0]);
            output = null;
            solve(p, 41, new ArrayList<>());
            if (output == null) {
                outputLines.add("Case #" + (i + 1) + ": " + -1);
            } else {
                StringBuilder builder = new StringBuilder();
                builder.append(output.size());
                for (int number : output) {
                    builder.append(" ");
                    builder.append(number);
                }
                outputLines.add("Case #" + (i + 1) + ": " + builder);
            }
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }

    private static void solve(int product, int start, ArrayList<Integer> answer) {
        if (output != null && answer.size() > output.size()) return;
        if (product == 1) {
            ArrayList<Integer> solution = new ArrayList<>(answer);
            for (int i = 0; i < start; i++) {
                solution.add(1);
            }
            if (output == null || output.size() > solution.size()) {
                output = solution;
            }
            return;
        }
        for (int i = start; i >= 2; i--) {
            if (product % i == 0) {
                answer.add(i);
                solve(product / i, start - i, answer);
                answer.remove(answer.size() - 1);
            }
        }
    }

    private static void writeOutputToFile(Path file) throws IOException {
        String defaultSystemSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");

        Files.write(file, outputLines);

        System.setProperty("line.separator", defaultSystemSeparator);
    }
}
