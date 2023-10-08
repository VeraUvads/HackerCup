package twenty23.first.C1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class C1 {

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "first", "C1");
/*
* Case #1: 1
Case #2: 1
Case #3: 4
Case #4: 4
Case #5: 0
Case #6: 86747
Case #7: 38042

* */
    public static void main(String[] args) throws IOException {
        BufferedReader buff = Files.newBufferedReader(RES_FOLDER.resolve("input.txt"));
        int numOfCases = Integer.parseInt(buff.readLine());

        for (int i = 0; i < numOfCases; i++) {
            int lineSize = Integer.parseInt(buff.readLine());
            String line = buff.readLine();

            TreeSet<Integer> pressed = new TreeSet<>();
            int qBrother = Integer.parseInt(buff.readLine());

            for (int j = 0; j < qBrother; j++) {
                int button = Integer.parseInt(buff.readLine());
                if (pressed.contains(button)) {
                    pressed.remove(button);
                } else {
                    pressed.add(button);
                }
            }
            int solution = solve(line, pressed);
            outputLines.add("Case #" + (i + 1) + ": " + solution);
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }

    private static int solve(String line, TreeSet<Integer> pressed) {
        int answer = 0;
        for (int i = 0; i < line.length(); i++) {
            int button = i + 1;
            int changed = 0;
            boolean isWhite = line.charAt(i) == '1';
            for (int pressedButton : pressed) {
                if (pressedButton > button) break;
                if (button % pressedButton == 0) {
                    changed++;
                }
            }
            boolean isChanged = changed % 2 == 1;
            if (isChanged) {
                isWhite = !isWhite;
            }


            if (isWhite) {
                if (!pressed.add(button)) {
                    pressed.remove(button);
                }
                answer++;
            }
        }
        return answer;
    }


    private static void writeOutputToFile(Path file) throws IOException {
        String defaultSystemSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");

        Files.write(file, outputLines);

        System.setProperty("line.separator", defaultSystemSeparator);
    }
}
