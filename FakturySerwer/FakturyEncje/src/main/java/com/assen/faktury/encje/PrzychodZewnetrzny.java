package com.assen.faktury.encje;

import com.assen.faktury.encje.base.BaseEntity;
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
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "przychod_zewnetrzny")
public class PrzychodZewnetrzny extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "kod_dokumentu")
    private KodDokumentu kodDokumentu;

    @ManyToOne
    @JoinColumn(name = "dostawca_id")
    private Kontrahent dostawca;

    @ManyToOne
    @JoinColumn(name = "odbiorca_id")
    private Uzytkownik odbiorca;

    @Column
    @Size(max = 20)
    private String maska;

    @Column
    private int numer;

    @Column(name = "data_wystawienia")
    @Temporal(TemporalType.DATE)
    private Date dataWystawienia;

    @Column(name = "numer_wz_dostawcy")
    private int numerWZdostawcy;

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

    public int getNumerWZdostawcy() {
        return numerWZdostawcy;
    }

    public void setNumerWZdostawcy(int numerWZdostawcy) {
        this.numerWZdostawcy = numerWZdostawcy;
    }

    public List<Towar> getListaTowarow() {
        return listaTowarow;
    }

    public void setListaTowarow(List<Towar> listaTowarow) {
        this.listaTowarow = listaTowarow;
    }

}
