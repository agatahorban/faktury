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
import javax.validation.constraints.Size;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "wz")
public class WydanieZewnetrzne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "kod_dokumentu")
    private KodDokumentu kodDokumentu;

    @Column
    @Size(max = 20)
    private String maska;

    @Column
    private int numer;

    @Column
    @Size(max = 10)
    private String waluta;

    @Column(name = "data_wystawienia")
    @Temporal(TemporalType.DATE)
    private Date dataWystawienia;

    @ManyToOne
    @JoinColumn(name = "odbiorca_id")
    private Kontrahent odbiorca;

    @ManyToOne
    @JoinColumn(name = "sprzedawca_id")
    private Uzytkownik sprzedawca;

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

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public Kontrahent getOdbiorca() {
        return odbiorca;
    }

    public void setOdbiorca(Kontrahent odbiorca) {
        this.odbiorca = odbiorca;
    }

    public Uzytkownik getSprzedawca() {
        return sprzedawca;
    }

    public void setSprzedawca(Uzytkownik sprzedawca) {
        this.sprzedawca = sprzedawca;
    }

    public List<Towar> getListaTowarow() {
        return listaTowarow;
    }

    public void setListaTowarow(List<Towar> listaTowarow) {
        this.listaTowarow = listaTowarow;
    }

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

}
