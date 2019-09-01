package org.antop.verbs.model;

import lombok.Data;
import org.antop.verbs.adatper.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;

@Data
@XmlRootElement(name = "slot")
@XmlAccessorType(XmlAccessType.FIELD)
public class Slot {
    public static final Slot NONE = Slot.of(-1, LocalTime.MIN, LocalTime.MAX, "");
    @XmlAttribute(name = "id")
    private int id;
    @XmlAttribute(name = "start")
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime start;
    @XmlAttribute(name = "end")
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime end;
    @XmlAttribute(name = "doctor")
    private String doctor;

    public static Slot of(int id, LocalTime start, LocalTime end, String doctor) {
        Slot slot = new Slot();
        slot.id = id;
        slot.start = start;
        slot.end = end;
        slot.doctor = doctor;
        return slot;
    }

}
