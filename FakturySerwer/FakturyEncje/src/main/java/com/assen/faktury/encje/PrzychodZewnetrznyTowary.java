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
@Table(name = "przychod_zewnetrzny_towary")
public class PrzychodZewnetrznyTowary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "przychod_zewnetrzny_id")
    private PrzychodZewnetrzny przychodZewnetrzny;
    
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

    public PrzychodZewnetrzny getPrzychodZewnetrzny() {
        return przychodZewnetrzny;
    }

    public void setPrzychodZewnetrzny(PrzychodZewnetrzny przychodZewnetrzny) {
        this.przychodZewnetrzny = przychodZewnetrzny;
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
