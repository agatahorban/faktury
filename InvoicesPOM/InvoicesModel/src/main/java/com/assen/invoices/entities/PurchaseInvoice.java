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

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "purchase_invoice")
public class PurchaseInvoice extends BasicEntity implements Serializable {


    @Enumerated(EnumType.STRING)
    @Column(name = "document_code")
    private DocumentCode documentCode;

    @Column
    @Size(max = 20)
    private String mask;

    @Column
    private int number;

    @Column(name = "date_of_issue")
    @Temporal(TemporalType.DATE)
    private Date dateOfIssue;

    @Column
    private String signature;

    @Column(name = "vat_invoice_of_supplier_number")
    private int VATInvoiceOfSupplierNumber;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Contractor supplier;

    @ManyToOne
    @JoinColumn(name = "addressee_id")
    private User addressee;
    
    @OneToMany(mappedBy = "purchaseInvoice", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<PurchaseInvoiceGoods> listOfGoods;
    
    public PurchaseInvoice() {
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getVATInvoiceOfSupplierNumber() {
        return VATInvoiceOfSupplierNumber;
    }

    public void setVATInvoiceOfSupplierNumber(int VATInvoiceOfSupplierNumber) {
        this.VATInvoiceOfSupplierNumber = VATInvoiceOfSupplierNumber;
    }

    public Contractor getSupplier() {
        return supplier;
    }

    public void setSupplier(Contractor supplier) {
        this.supplier = supplier;
    }

    public User getAddressee() {
        return addressee;
    }

    public void setAddressee(User addressee) {
        this.addressee = addressee;
    }

    public List<PurchaseInvoiceGoods> getListOfGoods() {
        return listOfGoods;
    }

    public void setListOfGoods(List<PurchaseInvoiceGoods> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }

  
}
