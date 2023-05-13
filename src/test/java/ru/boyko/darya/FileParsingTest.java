package ru.boyko.darya;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.codeborne.pdftest.PDF;

public class FileParsingTest {

    static final String ZIP_FILE = "check list.zip";
    @Test
    void readPdfFromArchiveTest() {
        ClassLoader classLoader = FileParsingTest.class.getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(ZIP_FILE);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null){
                if (zipEntry.getName().endsWith("pdf")){
                    PDF pdf = new PDF(zis);
                    System.out.println(pdf.text);
                    Assertions.assertAll(()->pdf.text.contains("ID"),
                            () -> pdf.text.contains("Check description"),
                            () -> pdf.text.contains("Result"));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readXlsFromArchiveTest() {
        final String FIRST_SHEET_NAME = "Get a single user";
        ClassLoader classLoader = FileParsingTest.class.getClassLoader();
        try(InputStream is = classLoader.getResourceAsStream(ZIP_FILE);
            ZipInputStream zis = new ZipInputStream(is)){
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null){
                if (zipEntry.getName().endsWith("xlsx")){
                    XLS xls = new XLS(zis);
                    System.out.println(xls.excel.getSheet("Wrong name"));
                    Assertions.assertAll(() -> Assertions.assertNotNull(xls.excel.getSheet(FIRST_SHEET_NAME), "Check if the sheet exists"),
                            () -> xls.excel.getSheet(FIRST_SHEET_NAME).getRow(0).getCell(0).getStringCellValue().
                            equalsIgnoreCase("ID"),
                            () -> xls.excel.getSheet(FIRST_SHEET_NAME).getRow(0).getCell(1).getStringCellValue().
                                    equalsIgnoreCase("Check description"),
                            () -> xls.excel.getSheet(FIRST_SHEET_NAME).getRow(0).getCell(2).getStringCellValue().
                                    equalsIgnoreCase("Result")
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
        ClassLoader classLoader = FileParsingTest.class.getClassLoader();
        try(InputStream is = classLoader.getResourceAsStream(ZIP_FILE);
            ZipInputStream zis = new ZipInputStream(is)){
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null){
                if (zipEntry.getName().endsWith("csv")){
                    System.out.println(zipEntry.getName());
                    CSVReader csvReader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    List<String[]> content = csvReader.readAll();
                    System.out.println(content);
                    Assertions.assertEquals(new String[] {"ID", "Check description", "Result"}, content.get(0));
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
