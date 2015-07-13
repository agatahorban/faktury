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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "external_income")
public class ExternalIncome extends BasicEntity implements Serializable {


    @Enumerated(EnumType.STRING)
    @Column(name = "document_code")
    private DocumentCode documentCode;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Contractor supplier;

    @ManyToOne
    @JoinColumn(name = "addressee_id")
    private InvoicesUser addresse;

    @Column
    @Size(max = 20)
    private String mask;

    @Column
    private int number;

    @Column(name = "date_of_issue")
    @Temporal(TemporalType.DATE)
    private Date dateOfIssue;

    @Column(name = "delivery_note_number_of_supplier")
    private int deliveryNoteNumberOfSupplier;
    
    @OneToMany(mappedBy = "externalIncome", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
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

    public InvoicesUser getAddresse() {
        return addresse;
    }

    public void setAddresse(InvoicesUser addresse) {
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
