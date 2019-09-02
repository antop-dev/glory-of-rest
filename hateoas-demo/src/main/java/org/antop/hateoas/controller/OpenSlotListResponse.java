package org.antop.hateoas.controller;

import org.antop.hateoas.model.Slot;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "openSlotList")
public class OpenSlotListResponse {
    @XmlElement(name = "slot")
    private List<Slot> slots;

    public List<Slot> getSlot() {
        if (slots == null) {
            slots = new ArrayList<>();
        }
        return slots;
    }

    public static OpenSlotListResponse of(List<Slot> slots) {
        OpenSlotListResponse response = new OpenSlotListResponse();
        response.slots = slots;
        return response;
    }

}
