package org.antop.hateoas.hateoas;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
abstract public class HasLink {
    @XmlElement(name = "link")
    private List<Link> links;

    public List<Link> getLinks() {
        if (links == null) {
            links = new ArrayList<>();
        }
        return links;
    }

    public void addLink(Link link) {
        getLinks().add(link);
    }

    public void addLink(String rel, String href) {
        addLink(Link.of(rel, href));
    }

}
