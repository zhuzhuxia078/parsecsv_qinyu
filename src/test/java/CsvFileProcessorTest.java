import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CsvFileProcessorTest {

    private CsvFileProcessor processor;

    @Mock
    private CSVReader csvReader;
    @Mock
    private CSVWriter csvWriter;

    @BeforeEach
    public void setUp() {
        processor = new CsvFileProcessor(csvReader, csvWriter);
    }

    @Test
    public void testHappyCase() throws CsvValidationException, IOException {
        when(csvReader.readNext())
                .thenReturn(null);
        doNothing().when(csvWriter).writeNext(any(String[].class));

        processor.processCsvFile();
        verify(csvReader).readNext();
        verify(csvWriter).writeNext(any(String[].class));
    }

    @Test
    public void testCsvValidationException() throws CsvValidationException, IOException {
        when(csvReader.readNext()).thenThrow(CsvValidationException.class);

        Assertions.assertThrows(CsvValidationException.class, () -> {
            processor.processCsvFile();
        });

        verify(csvReader).readNext();
    }

    @Test
    public void testIOException() throws CsvValidationException, IOException {
        when(csvReader.readNext()).thenThrow(IOException.class);

        Assertions.assertThrows(IOException.class, () -> {
            processor.processCsvFile();
        });
        verify(csvReader).readNext();
    }
}
