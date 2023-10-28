package com.adekpopiel.worldcup.dateformatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ISO8601DateFormatterTest {

    private static final String DATE_CANNOT_BE_NULL = "Date cannot be null!";
    private static final String ISO8601_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    @InjectMocks
    ISO8601DateFormatter iso8601DateFormatter;

    @Test
    public void testDateFormatting() {
        //given
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 30, 7, 30, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.setTimeZone(TimeZone.getTimeZone("CET"));
        Date startTimeDate = new Date(calendar.getTimeInMillis());
        //when
        String formattedDate = iso8601DateFormatter.formatDate(startTimeDate);
        //then
        assertEquals(formattedDate, "2023-10-30T07:30:00.000+01:00");
    }

    @Test
    public void testDateFormattingIfDateIsNull() {
        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> iso8601DateFormatter.formatDate(null));
        //then
        assertEquals(exception.getMessage(), DATE_CANNOT_BE_NULL);
    }

    @Test
    public void testGetFormatReturnsExpectedPattern() {
        //when
        String pattern = iso8601DateFormatter.getFormatterString();
        //then
        assertEquals(pattern, ISO8601_DATE_TIME_PATTERN);
    }
}