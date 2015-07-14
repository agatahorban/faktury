package com.assen.invoices.entities;

import com.assen.invoices.model.DocumentCode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "external_income")
@XmlRootElement(name = "externalIncome")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalIncome extends BasicEntity implements Serializable {


    @Enumerated(EnumType.STRING)
    @Column(name = "document_code")
    @XmlElement
    private DocumentCode documentCode;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @XmlElement
    private Contractor supplier;

    @ManyToOne
    @JoinColumn(name = "addressee_id")
    @XmlElement
    private User addresse;

    @Column
    @Size(max = 20)
    @XmlElement
    private String mask;

    @Column
    @XmlElement
    private int number;

    @Column(name = "date_of_issue")
    @Temporal(TemporalType.DATE)
    @XmlElement
    private Date dateOfIssue;

    @Column(name = "delivery_note_number_of_supplier")
    @XmlElement
    private int deliveryNoteNumberOfSupplier;
    
    @OneToMany(mappedBy = "externalIncome", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @XmlElement
    private List<ExternalIncomeGoods> listOfGoods;
    
    public ExternalIncome() {
        listOfGoods = new ArrayList<>();
    }

    public DocumentCode getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(DocumentCode documentCode) {
        this.documentCode = documentCode;
    }

    public Contractor getSupplier() {
        return supplier;
    }

    public void setSupplier(Contractor supplier) {
        this.supplier = supplier;
    }

    public User getAddresse() {
        return addresse;
    }

    public void setAddresse(User addresse) {
        this.addresse = addresse;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public int getDeliveryNoteNumberOfSupplier() {
        return deliveryNoteNumberOfSupplier;
    }

    public void setDeliveryNoteNumberOfSupplier(int deliveryNoteNumberOfSupplier) {
        this.deliveryNoteNumberOfSupplier = deliveryNoteNumberOfSupplier;
    }

    public List<ExternalIncomeGoods> getListOfGoods() {
        return listOfGoods;
    }

    public void setListOfGoods(List<ExternalIncomeGoods> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }

    
}
