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
@Table(name = "wydanie_zewnetrzne_towary")
public class WydanieZewnetrzneTowary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "wydanie_zewnetrzne_id")
    private WydanieZewnetrzne wydanieZewnetrzne;
    
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

    public WydanieZewnetrzne getWydanieZewnetrzne() {
        return wydanieZewnetrzne;
    }

    public void setWydanieZewnetrzne(WydanieZewnetrzne wydanieZewnetrzne) {
        this.wydanieZewnetrzne = wydanieZewnetrzne;
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
