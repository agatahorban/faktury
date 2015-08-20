package com.assen.invoices.dto;

import com.assen.invoices.entities.Group;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arek
 */
@XmlRootElement(name = "groupList")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupListDto {

    @XmlElement(name = "group")
    private List<Group> groups;

    public GroupListDto() {
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
