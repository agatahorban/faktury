package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.Group;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class GroupWrapper {

    private Group group;
    
    private StringProperty name;

    public GroupWrapper(Group group) {
        this.group = group;
        setWrapperValues();
    }

    public Group getGroup() {
        getWrapperValues();
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        setWrapperValues();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    private void setWrapperValues() {
        name = new SimpleStringProperty(group.getName());
    }
    
    private void getWrapperValues() {
        group.setName(getName());
    }
}
