package org.antop.pox.endpoint;

import lombok.Data;
import org.antop.pox.adapter.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "openSlotRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class OpenSlotRequest {
    @XmlAttribute
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;
    @XmlAttribute(name = "doctor")
    private String doctor;
}
