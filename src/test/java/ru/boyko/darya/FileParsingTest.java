package ru.boyko.darya;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileParsingTest extends TestBase{

    static final String ZIP_FILE = "check list.zip";
    static final String CSV_EXTENSION = "csv";
    static final String PDF_EXTENSION = "pdf";
    static final String XLSX_EXTENSION = "xlsx";
    static String[] columns = {"ID", "Check description", "Result"};


    @Test
    void readPdfFromArchiveTest() {
        try (InputStream is = classLoader.getResourceAsStream(ZIP_FILE);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null){
                if (zipEntry.getName().endsWith(PDF_EXTENSION)){
                    PDF pdf = new PDF(zis);
                    System.out.println(pdf.text);
                    Assertions.assertAll(()->pdf.text.contains(columns[0]),
                            () -> pdf.text.contains(columns[1]),
                            () -> pdf.text.contains(columns[2]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readXlsFromArchiveTest() {
        final String FIRST_SHEET_NAME = "Get a single user";

        try(InputStream is = classLoader.getResourceAsStream(ZIP_FILE);
            ZipInputStream zis = new ZipInputStream(is)){
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null){
                if (zipEntry.getName().endsWith(XLSX_EXTENSION)){
                    XLS xls = new XLS(zis);
                    Assertions.assertAll(
                            () -> Assertions.assertNotNull(xls.excel.getSheet(FIRST_SHEET_NAME), "Check if the sheet exists"),
                            () -> xls.excel.getSheet(FIRST_SHEET_NAME).getRow(0).getCell(0).getStringCellValue().
                                    equalsIgnoreCase(columns[0]),
                            () -> xls.excel.getSheet(FIRST_SHEET_NAME).getRow(0).getCell(1).getStringCellValue().
                                    equalsIgnoreCase(columns[1]),
                            () -> xls.excel.getSheet(FIRST_SHEET_NAME).getRow(0).getCell(2).getStringCellValue().
                                    equalsIgnoreCase(columns[2])
                            );
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readCsvFromArchiveTest() {
        try(InputStream is = classLoader.getResourceAsStream(ZIP_FILE);
            ZipInputStream zis = new ZipInputStream(is)){
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null){
                if (zipEntry.getName().endsWith(CSV_EXTENSION)){
                    System.out.println(zipEntry.getName());
                    //create csv parser with specific separator to read ; separator
                    CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
                    CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(zis)).withCSVParser(csvParser).build();
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(columns, content.get(0));
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
