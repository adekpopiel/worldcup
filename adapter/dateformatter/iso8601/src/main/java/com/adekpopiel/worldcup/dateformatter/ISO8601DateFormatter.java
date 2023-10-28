package com.adekpopiel.worldcup.dateformatter;

import com.adekpopiel.worldcup.usecase.port.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ISO8601DateFormatter implements DateFormatter {

    private static final String ISO8601_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    @Override
    public String formatDate(Date dateToFormat) {
        if (dateToFormat == null) {
            throw new IllegalArgumentException("Date cannot be null!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_TIME_PATTERN);
        return sdf.format(dateToFormat);
    }

    @Override
    public String getFormatterString() {
        return ISO8601_DATE_TIME_PATTERN;
    }
}
