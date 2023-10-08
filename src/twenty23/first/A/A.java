package twenty23.first.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class A {

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "first", "B2");

    public static void main(String[] args) throws IOException {
        BufferedReader buff = Files.newBufferedReader(RES_FOLDER.resolve("input.txt"));
        int numOfCases = Integer.parseInt(buff.readLine());

        for (int i = 0; i < numOfCases; i++) {
            String line = buff.readLine();
            String[] array = line.split(" ");
            int elvesCount = Integer.parseInt(array[0]);
            final StringTokenizer splitter = new StringTokenizer(buff.readLine(), " ");
            double[] elves = new double[elvesCount];
            for (int j = 0; j < elvesCount; j++) {
                double weight = Double.parseDouble(splitter.nextToken());
                elves[j] = weight;
            }

            double solution = solve(elves);
            String temp = String.valueOf(solution);
            String[] splinted = temp.split("\\.");
            if (Objects.equals(splinted[1], "0")) {
                outputLines.add("Case #" + (i + 1) + ": " + splinted[0]);
            } else {
                outputLines.add("Case #" + (i + 1) + ": " + solution);
            }
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }

    private static double solve(double[] elves) {
        int n = elves.length;
        Arrays.sort(elves);
        double first = elves[0];
        double second = elves[1];
        double preLast = elves[n - 2];
        double last = elves[n - 1];

        double firstPoint = first + (second - first) / 2.0;
        double lastPoint = preLast + (last - preLast) / 2.0;
        if (elves.length == 5) {
            double newFirstPoint = first + (elves[2] - first) / 2.0;
            double newLastPoint = elves[2] + (last - elves[2]) / 2.0;
            return Math.max(lastPoint - newFirstPoint, newLastPoint - firstPoint);
        }
        return lastPoint - firstPoint;
    }

    private static void writeOutputToFile(Path file) throws IOException {
        String defaultSystemSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");

        Files.write(file, outputLines);

    }
}
