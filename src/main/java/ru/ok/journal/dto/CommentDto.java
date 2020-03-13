package ru.ok.journal.dto;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Data
public class CommentDto {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String data;
    private String date;

    public Date getSubmissionDateConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.date);
    }
}