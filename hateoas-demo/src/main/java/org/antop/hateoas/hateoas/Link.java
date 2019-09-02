package org.antop.hateoas.hateoas;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Link {
    @XmlAttribute(name = "rel")
    private String rel;
    @XmlAttribute(name = "href")
    private String href;

    public static Link of(String rel, String href) {
        Link link = new Link();
        link.rel = rel;
        link.href = href;
        return link;
    }
}
