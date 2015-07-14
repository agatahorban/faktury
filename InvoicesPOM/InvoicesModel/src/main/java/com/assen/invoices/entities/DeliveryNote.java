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
 * @author Agata
 */
@Entity
@Table(name = "delivery_note")
@XmlRootElement(name = "deliveryNote")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeliveryNote extends BasicEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "document_code")
    @XmlElement
    private DocumentCode documentCode;

    @Column
    @Size(max = 20)
    @XmlElement
    private String mask;

    @Column
    @XmlElement
    private int number;

    @Column
    @Size(max = 10)
    @XmlElement
    private String concurrency;

    @Column(name = "date_of_issue")
    @Temporal(TemporalType.DATE)
    @XmlElement
    private Date dateOfIssue;

    @ManyToOne
    @JoinColumn(name = "addressee_id")
    @XmlElement
    private Contractor addressee;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @XmlElement
    private User vendor;
    
    @OneToMany(mappedBy = "deliveryNote", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @XmlElement
    private List<DeliveryNoteGoods> listOfGoods;
    
    public DeliveryNote() {
        listOfGoods = new ArrayList<>();
    }

    public DocumentCode getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(DocumentCode documentCode) {
        this.documentCode = documentCode;
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

    public String getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(String concurrency) {
        this.concurrency = concurrency;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Contractor getAddressee() {
        return addressee;
    }

    public void setAddressee(Contractor addressee) {
        this.addressee = addressee;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }

    public List<DeliveryNoteGoods> getListOfGoods() {
        return listOfGoods;
    }

    public void setListOfGoods(List<DeliveryNoteGoods> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }
    
   
}
