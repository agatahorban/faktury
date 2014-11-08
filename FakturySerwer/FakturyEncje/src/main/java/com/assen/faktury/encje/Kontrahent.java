package com.assen.faktury.encje;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "kontrahent")
public class Kontrahent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "czy_odbiorca")
    private boolean czyOdbiorca;

    @Column(name = "czy_dostawca")
    private boolean czyDostawca;

    @Column(name = "czy_platnik_vat")
    private boolean czyPlatnikVAT;

    @Column(name = "nazwa_skrocona")
    @Length(max = 30)
    private String nazwaSkrocona;

    @Column(name = "nazwa_pelna")
    private String nazwaPelna;

    @Column(name = "nip")
    @Length(min = 10, max = 13)
    private String NIP;

    @ManyToOne
    @JoinColumn(name = "adres_id")
    private Adres adres;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "termin_platnosci_id")
    private TerminPlatnosci terminPlatnosci;

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public boolean isCzyOdbiorca() {
        return czyOdbiorca;
    }

    public void setCzyOdbiorca(boolean czyOdbiorca) {
        this.czyOdbiorca = czyOdbiorca;
    }

    public boolean isCzyDostawca() {
        return czyDostawca;
    }

    public void setCzyDostawca(boolean czyDostawca) {
        this.czyDostawca = czyDostawca;
    }

    public boolean isCzyPlatnikVAT() {
        return czyPlatnikVAT;
    }

    public void setCzyPlatnikVAT(boolean czyPlatnikVAT) {
        this.czyPlatnikVAT = czyPlatnikVAT;
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public TerminPlatnosci getTerminPlatnosci() {
        return terminPlatnosci;
    }

    public void setTerminPlatnosci(TerminPlatnosci terminPlatnosci) {
        this.terminPlatnosci = terminPlatnosci;
    }

}
