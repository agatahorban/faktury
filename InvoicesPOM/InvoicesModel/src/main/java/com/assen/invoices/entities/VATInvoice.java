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
@Table(name = "vat_invoice")
@XmlRootElement(name = "VATInvoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class VATInvoice extends BasicEntity implements Serializable {

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

    @Column(name = "date_of_issue")
    @Temporal(TemporalType.DATE)
    @XmlElement
    private Date dateOfIssue;

    @Column(name = "date_of_payment")
    @Temporal(TemporalType.DATE)
    @XmlElement
    private Date dateOfPayment;

    @Column
    @XmlElement
    private String signature;

    @ManyToOne
    @JoinColumn(name = "addressee_id")
    @XmlElement
    private Contractor addresse;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @XmlElement
    private User vendor;
    
    @OneToMany(mappedBy = "VATInvoice", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @XmlElement
    private List<VATInvoiceGoods> listOfGoods;
    
    public VATInvoice() {
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

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Contractor getAddresse() {
        return addresse;
    }

    public void setAddresse(Contractor addresse) {
        this.addresse = addresse;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }

    public List<VATInvoiceGoods> getListOfGoods() {
        return listOfGoods;
    }

    public void setListOfGoods(List<VATInvoiceGoods> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }

    
}
