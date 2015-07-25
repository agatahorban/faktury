/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Agata
 */

@Entity
@Table(name = "user_role")
@XmlRootElement(name = "user_role")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRole extends BasicEntity implements Serializable{
    @Column
    @XmlElement
    private String username;
    
    @Column
    @XmlElement
    private String role;

    public UserRole(User user, Role role) {
        this.username = user.getLogin();
        this.role = role.getName();
    } 

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
