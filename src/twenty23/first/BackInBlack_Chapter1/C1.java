package twenty23.first.BackInBlack_Chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class C1 {

    private static final List<String> outputLines = new LinkedList<>();
    private static final Path RES_FOLDER = Paths.get("src", "twenty23", "first", "BackInBlack_Chapter1");

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

            boolean[] pressed = new boolean[line.length() + 1];
            int qBrother = Integer.parseInt(buff.readLine());

            for (int j = 0; j < qBrother; j++) {
                int button = Integer.parseInt(buff.readLine());
                pressed[button] = !pressed[button];
            }
            int solution = solve(line, pressed);
            outputLines.add("Case #" + (i + 1) + ": " + solution);
        }

        buff.close();
        writeOutputToFile(RES_FOLDER.resolve("output.txt"));
    }

    private static int solve(String line, boolean[] pressed) {
        boolean[] buttons = new boolean[line.length() + 1]; // white true, black false
        for (int i = 0; i < line.length(); i++) {
            buttons[i + 1] = line.charAt(i) == '1';
        }
        for (int i = 1; i < pressed.length; i++) {
            if (pressed[i]) {
                for (int j = i; j < pressed.length; j += i) {
                    buttons[j] = !buttons[j];
                }
            }
        }
        int answer = 0;

        for (int i = 1; i < pressed.length; i++) {
            boolean isWhite = buttons[i];
            if (isWhite) {
                answer++;
                for (int j = i; j < pressed.length; j += i) {
                    buttons[j] = !buttons[j];
                }
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
