import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvFileProcessor {
    private final CSVReader csvReader;
    private final CSVWriter csvWriter;

    public CsvFileProcessor(final CSVReader csvReader, final CSVWriter csvWriter) {
        this.csvReader = csvReader;
        this.csvWriter = csvWriter;
    }

    // 1. read file
    // 2. write to new CSV file
    public void processCsvFile() throws CsvValidationException, IOException {
        String [] nextLine;
        Map<String, Integer> map = new HashMap<>();
        boolean firstLine = true;
        while ((nextLine = csvReader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            if (firstLine) {
                firstLine = false;
                continue;
            }
            final String mineral = nextLine[4];
            final Integer quantity = Integer.parseInt(nextLine[3]);
            map.put(mineral, map.getOrDefault(mineral, 0) + quantity);
        }

        for (String key : map.keySet()) {
            System.out.println("Mineral: " + key +  " Quantity: " + map.get(key));
        }

        String[] headers = {"Mineral", "Quantity"};
        csvWriter.writeNext(headers);

        for (final String key : map.keySet()) {
            final String[] data = {key, map.get(key).toString()};
            csvWriter.writeNext(data);
        }

        csvWriter.close();
    }
}
