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

@Entity
@Table(name = "faktura_fz_towary")
public class FakturaFZTowary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "faktura_fz_id")
    private FakturaFZ fakturaFZ;
    
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

    public FakturaFZ getFakturaFZ() {
        return fakturaFZ;
    }

    public void setFakturaFZ(FakturaFZ fakturaFZ) {
        this.fakturaFZ = fakturaFZ;
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