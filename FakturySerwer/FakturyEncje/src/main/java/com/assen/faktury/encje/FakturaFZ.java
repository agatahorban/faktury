package com.assen.faktury.encje;

import com.assen.faktury.model.KodDokumentu;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "faktura_fz")
public class FakturaFZ implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "kod_dokumentu")
    private KodDokumentu kodDokumentu;

    @Column
    @Length(max = 20)
    private String maska;

    @Column
    private int numer;

    @Column(name = "data_wystawienia")
    @Temporal(TemporalType.DATE)
    private Date dataWystawienia;

    @Column
    private String sygnatura;

    @Column(name = "numer_faktury_vat_dostawcy")
    private int numerFakturyVATdostawcy;

    @ManyToOne
    @JoinColumn(name = "dostawca_id")
    private Kontrahent dostawca;

    @ManyToOne
    @JoinColumn(name = "odbiorca_id")
    private Uzytkownik odbiorca;

    @OneToMany
    private List<Towar> listaTowarow;

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public KodDokumentu getKodDokumentu() {
        return kodDokumentu;
    }

    public void setKodDokumentu(KodDokumentu kodDokumentu) {
        this.kodDokumentu = kodDokumentu;
    }

    public String getMaska() {
        return maska;
    }

    public void setMaska(String maska) {
        this.maska = maska;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public String getSygnatura() {
        return sygnatura;
    }

    public void setSygnatura(String sygnatura) {
        this.sygnatura = sygnatura;
    }

    public int getNumerFakturyVATdostawcy() {
        return numerFakturyVATdostawcy;
    }

    public void setNumerFakturyVATdostawcy(int numerFakturyVATdostawcy) {
        this.numerFakturyVATdostawcy = numerFakturyVATdostawcy;
    }

    public Kontrahent getDostawca() {
        return dostawca;
    }

    public void setDostawca(Kontrahent dostawca) {
        this.dostawca = dostawca;
    }

    public Uzytkownik getOdbiorca() {
        return odbiorca;
    }

    public void setOdbiorca(Uzytkownik odbiorca) {
        this.odbiorca = odbiorca;
    }

    public List<Towar> getListaTowarow() {
        return listaTowarow;
    }

    public void setListaTowarow(List<Towar> listaTowarow) {
        this.listaTowarow = listaTowarow;
    }

}
