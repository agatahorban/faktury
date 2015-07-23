package com.assen.invoices.entities;

import com.assen.invoices.model.UserType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "invoices_user")
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends BasicEntity implements Serializable {

    @Column(name = "login", unique = true)
    @Size(max = 30)
    @XmlElement
    private String login;

    @Column(name = "pass")
    @XmlElement
    private String password;

    @Column(name = "full_name")
    @XmlElement
    private String fullName;

    @Column(name = "nip")
    @Size(min = 10, max = 13)
    @XmlElement
    private String NIP;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @XmlElement
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    @XmlElement
    private UserType userType;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
