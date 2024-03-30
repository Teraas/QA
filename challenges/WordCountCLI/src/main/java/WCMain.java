
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import static java.util.Objects.nonNull;

/**
 * @author harish.kumar-mbp
 * createdOn 28/03/24
 */

@CommandLine.Command(name = "wc", description = "Word Count CLI tool in Java", version = "1.0.0")
public class WCMain implements Callable<Integer> {

    @CommandLine.Option(names = "-words", description = "get words count")
    private boolean countWords;

    @CommandLine.Option(names = "-lines", description = "get lines count")
    private boolean countLines;

    @CommandLine.Option(names = "-chars", description = "get characters count")
    private boolean countCharacter;

    @CommandLine.Option(names = "-bytes", description = "get bytes count")
    private boolean countBytes;

    @CommandLine.Option(names = "--isPipedInput", hidden = true)
    private boolean isPipedInput;

    @CommandLine.Parameters(index = "0", description = "The file/text whose count should be calculated.")
    private String input;

    @Override
    public Integer call() {
        try {
            StringBuilder finalOutput = new StringBuilder();
            if (isPipedInput) {
                CounterUtil.generateResultForPiped(input, countWords, countLines, countCharacter, countBytes);
            }
            else {
                CounterUtil.generateResult(input, countWords, countLines, countCharacter, countBytes);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        // Check if piped input has value. If available, read the value and set it to the args
        // else, continue (to throw error that input is missing or work with the file)
        if (System.in.available() > 0) {
            StringBuilder fileContents = new StringBuilder();
            InputStreamReader isReader = new InputStreamReader(System.in);
            BufferedReader bufReader = new BufferedReader(isReader);
            String inputStr;
            while (nonNull(inputStr = bufReader.readLine())) {
                try {
                    fileContents.append(inputStr).append("\n");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            // Add the read input as a commandline argument.
            List<String> argsList = new ArrayList<>(Arrays.asList(args));
            argsList.add(fileContents.toString());
            argsList.add("--isPipedInput");
            args = argsList.toArray(new String[0]);
        }
        int exitCode = new CommandLine(new WCMain()).execute(args);
        System.exit(exitCode);
    }
}
