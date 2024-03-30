import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static java.util.Objects.nonNull;

/**
 * @author harish.kumar-mbp
 * createdOn 28/03/24
 */
public class CounterUtil {

    public static void generateResult(String input, boolean countWords, boolean countLines, boolean countCharacter, boolean countBytes){
        StringBuilder finalOutput = new StringBuilder();
        File file = new File(input.trim());
        if (file.isFile()) {
            if (countBytes) {
                finalOutput.append(countBytes(file)).append(" ");
            }
            if (countWords) {
                finalOutput.append(countWords(file)).append(" ");
            }
            if (countLines) {
                finalOutput.append(countLines(file)).append(" ");
            }
            if (countCharacter) {
                finalOutput.append(countCharacters(file)).append(" ");
            }
            if (!(countWords || countLines || countCharacter || countBytes)) {
                finalOutput.append(countBytes(file)).append(" ")
                        .append(countWords(file)).append(" ")
                        .append(countLines(file)).append(" ")
                        .append(countCharacters(file)).append(" ");
            }
            finalOutput.append(file.getName());
            System.out.println(finalOutput);
        } else {
            System.err.println("File " + input.trim() + " not found!");
        }
    }


    public static void generateResultForPiped(String input, boolean countWords, boolean countLines, boolean countCharacter, boolean countBytes){
        StringBuilder finalOutput = new StringBuilder();
        if (countBytes) {
            finalOutput.append(countBytes(input)).append(" ");
        }
        if (countWords) {
            finalOutput.append(countWords(input)).append(" ");
        }
        if (countLines) {
            finalOutput.append(countLines(input)).append(" ");
        }
        if (countCharacter) {
            finalOutput.append(countCharacters(input)).append(" ");
        }
        if (!(countWords || countLines || countBytes || countCharacter)) {
            finalOutput.append(countBytes(input)).append(" ")
                    .append(countWords(input)).append(" ")
                    .append(countLines(input)).append(" ")
                    .append(countCharacters(input));
        }
        System.out.println(finalOutput);
    }


    private static Integer countBytes(File file) {
        try {
            byte[] fileContents = Files.readAllBytes(file.toPath());
            return fileContents.length;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static Integer countBytes(String contents) {
        return contents.getBytes().length;
    }

    private static Integer countLines(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            int lineCount = 0;
            while (nonNull(line)) {
                lineCount++;
                line = br.readLine();
            }
            return (lineCount);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static Long countLines(String input) {
        return input.lines().count();
    }

    private static Long countWords(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            long wordCount = 0;
            while (nonNull(line)) {
                String[] words = line.trim().split("\\s+");
                wordCount += Arrays.stream(words)
                        .filter(w -> !w.isEmpty() && !w.matches("\\s+"))
                        .count();
                line = br.readLine();
            }
            return (wordCount);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return 0L;
    }

    private static Long countWords(String contents) {
        String[] words = contents.split("\\s+");
        return Arrays.stream(words)
                .filter(w -> !w.isEmpty() && !w.matches("\\s+"))
                .count();
    }

    private static Integer countCharacters(File file) {
        try {
            byte[] fileContents = Files.readAllBytes(file.toPath());
            String content = new String(fileContents);
            return content.length();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static Long countCharacters(String contents) {
        return contents.chars().count();
    }


}
