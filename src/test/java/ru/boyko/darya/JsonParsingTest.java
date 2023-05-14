package ru.boyko.darya;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonParsingTest extends TestBase{
    static String jsonFile = "trip.json";
    static String jsonDateTimeFormat = "dd.MM.yyyy HH:mm";

    @Test
    void readJsonFileTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat(jsonDateTimeFormat);
        objectMapper.setDateFormat(df);
        try (InputStream is = classLoader.getResourceAsStream(jsonFile);
             InputStreamReader isr = new InputStreamReader(is)){
            Trip trip = objectMapper.readValue(isr, Trip.class);
            //verify trip status and return time after now
            Date now = new Date();
            Assertions.assertAll(() -> Assertions.assertFalse(trip.finished),
                    () -> Assertions.assertTrue(trip.getReturnArrivalDateTime().after(now)),
                    () -> Assertions.assertTrue(trip.getReturnDepartureDateTime().after(now))
            );
        }
    }

}
