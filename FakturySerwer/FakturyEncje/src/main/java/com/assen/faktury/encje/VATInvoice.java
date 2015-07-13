package com.assen.faktury.encje;

import com.assen.faktury.model.DocumentCode;
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
@Table(name = "vat_invoice")
public class VATInvoice extends BasicEntity implements Serializable {

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
    private Date dataWystawienia;

    @Column(name = "date_of_payment")
    @Temporal(TemporalType.DATE)
    private Date dateOfPayment;

    @Column
    private String signature;

    @ManyToOne
    @JoinColumn(name = "addressee_id")
    private Contractor addresse;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private InvoicesUser vendor;
    
    @OneToMany(mappedBy = "VATInvoice", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
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

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
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

    public InvoicesUser getVendor() {
        return vendor;
    }

    public void setVendor(InvoicesUser vendor) {
        this.vendor = vendor;
    }

    public List<VATInvoiceGoods> getListOfGoods() {
        return listOfGoods;
    }

    public void setListOfGoods(List<VATInvoiceGoods> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }

    
}
