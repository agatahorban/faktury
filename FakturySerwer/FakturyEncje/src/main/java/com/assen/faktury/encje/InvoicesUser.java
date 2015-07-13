package com.assen.faktury.encje;

import com.assen.faktury.model.UserType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "invoices_user")
public class InvoicesUser extends BasicEntity implements Serializable {

    @Column(name = "login", unique = true)
    @Size(max = 30)
    private String login;

    @Column(name = "pass")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "nip")
    @Size(min = 10, max = 13)
    private String NIP;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
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
