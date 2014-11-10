package com.assen.faktury.encje;

import com.assen.faktury.encje.base.BaseEntity;
import com.assen.faktury.model.TypUzytkownika;
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
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "uzytkownik")
public class Uzytkownik extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "login", unique = true)
    @Size(max = 30)
    private String login;

    @Column(name = "haslo")
    private String haslo;

    @Column(name = "nazwa_pelna")
    private String nazwaPelna;

    @Column(name = "nip")
    @Length(min = 14, max = 14)
    private String NIP;

    @ManyToOne
    @JoinColumn(name = "adres_id")
    private Adres adres;

    @Enumerated(EnumType.STRING)
    @Column(name = "typ_uzytkownika")
    private TypUzytkownika typUzytkownika;

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getNazwaPelna() {
        return nazwaPelna;
    }

    public void setNazwaPelna(String nazwaPelna) {
        this.nazwaPelna = nazwaPelna;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public TypUzytkownika getTypUzytkownika() {
        return typUzytkownika;
    }

    public void setTypUzytkownika(TypUzytkownika typUzytkownika) {
        this.typUzytkownika = typUzytkownika;
    }

}
