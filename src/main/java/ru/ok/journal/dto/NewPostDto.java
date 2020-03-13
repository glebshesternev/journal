package ru.ok.journal.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Data
public class NewPostDto {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String name;
    private String data;
    private String date;

    public void setSubmissionDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.date = dateFormat.format(date);
    }
}
