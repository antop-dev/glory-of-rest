package org.antop.hateoas.model;

import lombok.Data;
import org.antop.hateoas.hateoas.HasLink;
import org.antop.hateoas.adatper.LocalDateAdapter;
import org.antop.hateoas.adatper.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@XmlRootElement(name = "slot")
@XmlAccessorType(XmlAccessType.FIELD)
public class Slot extends HasLink {
    public static final Slot NONE = Slot.of(-1, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "");
    @XmlAttribute(name = "id")
    private int id;
    @XmlAttribute(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;
    @XmlAttribute(name = "start")
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime start;
    @XmlAttribute(name = "end")
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime end;
    @XmlAttribute(name = "doctor")
    private String doctor;

    public static Slot of(int id, LocalDate date, LocalTime start, LocalTime end, String doctor) {
        Slot slot = new Slot();
        slot.id = id;
        slot.date = date;
        slot.start = start;
        slot.end = end;
        slot.doctor = doctor;
        return slot;
    }

}
