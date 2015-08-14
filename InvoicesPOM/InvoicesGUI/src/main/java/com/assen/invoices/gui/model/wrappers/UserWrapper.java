package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class UserWrapper {

    private User user;
    
    private StringProperty login;
    private StringProperty password;
    private StringProperty fullName;
    private StringProperty NIP;
    private AddressWrapper addressWrapper;

    public UserWrapper(User user) {
        this.user = user;
        setWrapperValues();
    }

    public User getUser() {
        getWrapperValues();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        setWrapperValues();
    }

    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getNIP() {
        return NIP.get();
    }

    public void setNIP(String NIP) {
        this.NIP.set(NIP);
    }

    public AddressWrapper getAddressWrapper() {
        return addressWrapper;
    }

    public void setAddressWrapper(AddressWrapper addressWrapper) {
        this.addressWrapper = addressWrapper;
    }
    
    public StringProperty loginProperty() {
        return login;
    }
    
    public StringProperty passwordProperty() {
        return password;
    }
    
    public StringProperty fullNameProperty() {
        return fullName;
    }
    
    public StringProperty NIPProperty() {
        return NIP;
    }
    
    private void setWrapperValues() {
        login = new SimpleStringProperty(user.getLogin());
        password = new SimpleStringProperty(user.getPassword());
        fullName = new SimpleStringProperty(user.getFullName());
        NIP = new SimpleStringProperty(user.getNIP());
        addressWrapper = new AddressWrapper(user.getAddress());
    }
    
    private void getWrapperValues() {
        user.setLogin(getLogin());
        user.setAddress(getAddressWrapper().getAddress());
        user.setFullName(getFullName());
        user.setNIP(getNIP());
        user.setPassword(getPassword());
    }
}
