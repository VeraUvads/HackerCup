package twenty23.practice.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class C {

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "practice", "C");

    public static void main(String[] args) throws IOException {
        BufferedReader buff = Files.newBufferedReader(RES_FOLDER.resolve("input.txt"));
        int numOfCases = Integer.parseInt(buff.readLine());

        for (int i = 0; i < numOfCases; i++) {
            String line = buff.readLine();
            String[] array = line.split(" ");
            int days = Integer.parseInt(array[0]);
            final StringTokenizer splitter = new StringTokenizer(buff.readLine(), " ");
            int[] weights = new int[days * 2 - 1];
            for (int j = 0; j < weights.length; j++) {
                int weight = Integer.parseInt(splitter.nextToken());
                weights[j] = weight;
            }
            int solution = solve(weights, days);
            outputLines.add("Case #" + (i + 1) + ": " + solution);
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }


    private static int solve(int[] weights, int days) {
        if (days == 1) return 1;
        int n = weights.length;
        Arrays.sort(weights);
        int firstAndLast = findMissedApple(weights, weights[0] + weights[n - 1]);

        int firstAndPreLast = findMissedApple(weights, weights[0] + weights[n - 2]);

        int secondAndLast = findMissedApple(weights, weights[1] + weights[n - 1]);

        int result = Math.min(Math.min(firstAndLast, firstAndPreLast), secondAndLast);
        if (result == Integer.MAX_VALUE) return -1;
        return result;
    }

    private static int findMissedApple(int[] weights, int sum) {
        int result = Integer.MAX_VALUE;
        int left = 0, right = weights.length - 1;
        while (left <= right) {
            if (left == right) {
                if (result == Integer.MAX_VALUE) return sum - weights[left];
                return Integer.MAX_VALUE;
            }
            int currentSum = weights[left] + weights[right];
            if (sum == currentSum) {
                left++;
                right--;
                continue;
            }
            if (result != Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (currentSum < sum) {
                result = sum - weights[left];
                left++;
            } else {
                result = sum - weights[right];
                right--;
            }
        }
        return result > 0 ? result : Integer.MAX_VALUE;
    }


    private static void writeOutputToFile(Path file) throws IOException {
        String defaultSystemSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");

        Files.write(file, outputLines);

        System.setProperty("line.separator", defaultSystemSeparator);
    }
}
