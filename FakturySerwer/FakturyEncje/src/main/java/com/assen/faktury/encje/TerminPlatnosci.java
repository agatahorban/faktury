package com.assen.faktury.encje;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "termin_platnosci")
public class TerminPlatnosci implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "opis")
    private String opis;

    @Column(name = "ilosc_dni")
    private int iloscDni;

    public TerminPlatnosci(String opis, int iloscDni) {
        this.opis = opis;
        this.iloscDni = iloscDni;
    }

    public TerminPlatnosci(int id, String opis, int iloscDni) {
        this.id = id;
        this.opis = opis;
        this.iloscDni = iloscDni;
    }
    
    public TerminPlatnosci() {
        
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getIloscDni() {
        return iloscDni;
    }

    public void setIloscDni(int iloscDni) {
        this.iloscDni = iloscDni;
    }

}
