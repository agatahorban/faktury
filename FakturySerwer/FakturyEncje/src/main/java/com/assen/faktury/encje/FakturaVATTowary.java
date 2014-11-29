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

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "faktura_vat_towary")
public class FakturaVATTowary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "faktura_vat_id")
    private FakturaVAT fakturaVAT;
    
    @ManyToOne
    @JoinColumn(name = "towar_id")
    private Towar towar;
    
    @Column
    private int ilosc;
    
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public FakturaVAT getFakturaVAT() {
        return fakturaVAT;
    }

    public void setFakturaVAT(FakturaVAT fakturaVAT) {
        this.fakturaVAT = fakturaVAT;
    }

    public Towar getTowar() {
        return towar;
    }

    public void setTowar(Towar towar) {
        this.towar = towar;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
}
