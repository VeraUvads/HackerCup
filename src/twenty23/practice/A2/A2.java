package twenty23.practice.A2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class A2 {

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "practice", "A2");

    public static void main(String[] args) throws IOException {
        BufferedReader buff = Files.newBufferedReader(RES_FOLDER.resolve("input.txt"));
        int numOfCases = Integer.parseInt(buff.readLine());

        for (int i = 0; i < numOfCases; i++) {
            String line = buff.readLine();
            String[] array = line.split(" ");
            long singleCost = Long.parseLong(array[0]);
            long doubleCost = Long.parseLong(array[1]);
            long haveDollars = Long.parseLong(array[2]);
            try {
                long solution = solve(
                        singleCost,
                        doubleCost,
                        haveDollars
                );
                outputLines.add("Case #" + (i + 1) + ": " + solution);

            } catch (StackOverflowError error) {
                System.out.println("test case fail " + i);
            }
            System.out.println();
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }

    private static long solve(long singleCost, long doubleCost, long haveDollars) {

        long canBuySingles = howManyK(haveDollars / singleCost, 0);
        long canBuyDoubles = howManyK(0, haveDollars / doubleCost);

        long oneSingleK = 0;
        long doublesIfOneSingle = (haveDollars - singleCost) / doubleCost;
        if (haveDollars - singleCost >= 0) {
            oneSingleK = howManyK(1, doublesIfOneSingle);
        }

        long twoSingleK = 0;
        if (haveDollars - singleCost - singleCost >= 0) {
            long doublesIfTwoSingle = (haveDollars - singleCost - singleCost) / doubleCost;
            twoSingleK = howManyK(2, doublesIfTwoSingle);
        }

        return Math.max(Math.max(Math.max(canBuySingles, canBuyDoubles), oneSingleK), twoSingleK);
    }


    private static long howManyK(long s, long d) {
        long buns = s * 2 + d * 2;
        long patties = s + 2 * d;
        if (buns < 2) return 0;
        return Math.min(buns - 1, patties);
    }

    private static void writeOutputToFile(Path file) throws IOException {
        String defaultSystemSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");

        Files.write(file, outputLines);

        System.setProperty("line.separator", defaultSystemSeparator);
    }
}
