package twenty23.first.B2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class B2 { // TODO (failed)

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "first", "B");
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

            ArrayList<Integer> solution = null;
            solution = solve(p, 41);
            if (solution == null) {
                outputLines.add("Case #" + (i + 1) + ": " + -1);
            } else {
                Collections.sort(solution);
                StringBuilder builder = new StringBuilder();
                builder.append(solution.size());
                for (int number : solution) {
                    builder.append(" ");
                    builder.append(number);
                }
                outputLines.add("Case #" + (i + 1) + ": "+ builder);
            }
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }

    private static ArrayList<Integer> solve(int product, int start) {

        ArrayList<Integer> dividers = new ArrayList<>();
        int[] simpleNumbers = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
        int minSum = 0;

        for (int i = 0; i < simpleNumbers.length; )
        {
            int divider = simpleNumbers[i];
            if(product % divider==0)
            {
                product /= divider;
                minSum += divider;
                dividers.add(divider);
                if(product == 1)
                {
                    break;
                }
            }
            else
            {
                ++i;
            }
        }

        if(product != 1 || minSum > start)
        {
            return null;
        }

//        while (minSum < start)
//        {
//            dividers.add(1);
//            minSum++;
//        }


        while (Merge(dividers, start));


        minSum = Sum(dividers);
        while (minSum < start)
        {
            dividers.add(1);
            minSum++;
        }

        return dividers;
    }

    static boolean Merge(ArrayList<Integer> dividers, int start)
    {
        for (int i = dividers.size() - 1; i >= 0; --i) {
            for (int j = i -1 ; j >= 0; --j) {
                if (MergeSum(dividers, i, j) <= start) {
                    int merge1 = dividers.get(i);
                    int merge2 = dividers.get(j);
                    dividers.set(i, merge1 * merge2);
                    dividers.remove(j);
                    return true;
                }
            }
        }
        return false;
    }

    static int MergeSum(ArrayList<Integer> arrayList, int mergeIndex1,  int mergeIndex2)
    {
        int result = 0;
        for (int i = 0, max = arrayList.size(); i < max; ++i )  {
            if(i == mergeIndex1 || i == mergeIndex2){continue;}
            result+=arrayList.get(i);
        }
        result += arrayList.get(mergeIndex1) * arrayList.get(mergeIndex2);
        return result;
    }

    static int Sum(ArrayList<Integer> arrayList)
    {
        int result = 0;
        for (int i = 0, max = arrayList.size(); i < max; ++i )  {
            result+=arrayList.get(i);
        }

        return result;
    }
    private static void writeOutputToFile(Path file) throws IOException {
        String defaultSystemSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");

        Files.write(file, outputLines);

        System.setProperty("line.separator", defaultSystemSeparator);
    }
}
