package com.assen.faktury.encje;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "towar")
public class Towar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String index1;

    @Column
    private String index2;

    @Column(name = "czy_cena_wyzsza")
    private boolean czyCenaWyzsza;

    @Column
    private double cena;

    @ManyToOne
    @JoinColumn(name = "grupa_id")
    private Grupa grupa;

    @ManyToOne
    @JoinColumn(name = "opakowanie_zbiorcze_id")
    private OpakowanieZbiorcze opakowanie;

    @ManyToOne
    @JoinColumn(name = "stawka_vat_id")
    private StawkaVAT stawka;

    @ManyToOne
    @JoinColumn(name = "jednostka_miaryid")
    private JednostkaMiary jednostka;

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getIndex1() {
        return index1;
    }

    public void setIndex1(String index1) {
        this.index1 = index1;
    }

    public String getIndex2() {
        return index2;
    }

    public void setIndex2(String index2) {
        this.index2 = index2;
    }

    public boolean isCzyCenaWyzsza() {
        return czyCenaWyzsza;
    }

    public void setCzyCenaWyzsza(boolean czyCenaWyzsza) {
        this.czyCenaWyzsza = czyCenaWyzsza;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public OpakowanieZbiorcze getOpakowanie() {
        return opakowanie;
    }

    public void setOpakowanie(OpakowanieZbiorcze opakowanie) {
        this.opakowanie = opakowanie;
    }

    public StawkaVAT getStawka() {
        return stawka;
    }

    public void setStawka(StawkaVAT stawka) {
        this.stawka = stawka;
    }

    public JednostkaMiary getJednostka() {
        return jednostka;
    }

    public void setJednostka(JednostkaMiary jednostka) {
        this.jednostka = jednostka;
    }

}
