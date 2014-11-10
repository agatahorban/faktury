package com.assen.faktury.encje;

import com.assen.faktury.encje.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "opakowanie_zbiorcze")
public class OpakowanieZbiorcze extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "nazwa_skrocona")
    @Size(max = 10)
    private String nazwaSkrocona;

    @Column(name = "nazwa_pelna")
    private String nazwaPelna;

    @Column
    private int pojemnosc;

    @Column
    private double waga;

    @Column
    private double szerokosc;

    @Column
    private double wysokosc;

    @Column
    private double glebokosc;

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getNazwaSkrocona() {
        return nazwaSkrocona;
    }

    public void setNazwaSkrocona(String nazwaSkrocona) {
        this.nazwaSkrocona = nazwaSkrocona;
    }

    public String getNazwaPelna() {
        return nazwaPelna;
    }

    public void setNazwaPelna(String nazwaPelna) {
        this.nazwaPelna = nazwaPelna;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(int pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public double getWaga() {
        return waga;
    }

    public void setWaga(double waga) {
        this.waga = waga;
    }

    public double getSzerokosc() {
        return szerokosc;
    }

    public void setSzerokosc(double szerokosc) {
        this.szerokosc = szerokosc;
    }

    public double getWysokosc() {
        return wysokosc;
    }

    public void setWysokosc(double wysokosc) {
        this.wysokosc = wysokosc;
    }

    public double getGlebokosc() {
        return glebokosc;
    }

    public void setGlebokosc(double glebokosc) {
        this.glebokosc = glebokosc;
    }

}
