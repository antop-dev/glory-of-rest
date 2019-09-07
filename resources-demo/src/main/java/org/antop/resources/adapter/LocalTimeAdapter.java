package org.antop.resources.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");

    @Override
    public LocalTime unmarshal(String v) {
        return LocalTime.parse(v, formatter);
    }

    @Override
    public String marshal(LocalTime v) {
        return v.format(formatter);
    }
}
