import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;

/**
 * Main class for parsecsv project
 * The parsecsv is used to read from expeditions.csv and reformat into new CSV file called minerals.csv
 * The included files in this project:
 * 1. Main.java
 * 2. CsvFileProcessor.java
 *
 * Artifacts: parsecsv.jar
 *
 * Unit Test: CsvFileProcessorTest.java
 *
 *
 * How to Run:
 * Use parsecsv.jar and run below command line
 * java -jar parsecsv.jar <expeditions file path> <minerals file path>
 *
 * sample cli:
 * java -jar parsecsv.jar '/Users/zhuqinyu/IdeaProjects/parsecsv/src/main/java/expeditions.csv' '/Users/zhuqinyu/IdeaProjects/parsecsv/src/main/java/minerals.csv'
 */
public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {
        for (int i = 0; i < args.length; i++) {
            System.out.println("Argument: " + i + " : " + args[i]);
        }
        if (args.length != 2) {
            System.out.println("Sample command line java -jar csvprocess.jar '/Users/zhuqinyu/IdeaProjects/parsecsv/src/main/java/expeditions.csv' '/Users/zhuqinyu/IdeaProjects/parsecsv/src/main/java/minerals.csv'");
            throw new IllegalArgumentException("invalid input arguments. Please enter two args with readFilePath and writeFilePath");
        }

        CSVReader reader = new CSVReader(new FileReader(args[0]));
        CSVWriter writer = new CSVWriter(new FileWriter(args[1]));

        final CsvFileProcessor fileProcessor = new CsvFileProcessor(reader, writer);

        fileProcessor.processCsvFile();
    }
}
