package com.assen.faktury.encje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "magazyn")
public class Magazyn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    
    @Column(name = "nazwa")
    @Size(max = 25)
    private String nazwa;
    
    @OneToMany(mappedBy = "magazyn", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<MagazynTowary> magazynTowary;
    
    public Magazyn() {
        magazynTowary = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<MagazynTowary> getMagazynTowary() {
        return magazynTowary;
    }

    public void setMagazynTowary(List<MagazynTowary> magazynTowary) {
        this.magazynTowary = magazynTowary;
    }
}
