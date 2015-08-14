package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.Role;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class RoleWrapper {

    private Role role;
    
    private StringProperty name;

    public RoleWrapper(Role role) {
        this.role = role;
        setWrapperValues();
    }

    public Role getRole() {
        getWrapperValues();
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        name = new SimpleStringProperty(role.getName());
    }
    
    private void getWrapperValues() {
        role.setName(getName());
    }
}
