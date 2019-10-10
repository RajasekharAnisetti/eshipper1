package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A ApPayable.
 */
@Entity
@Table(name = "ap_payable")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApPayable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Max(value = 11)
    @Column(name = "invoice_amount")
    private Integer invoiceAmount;

    @Size(max = 255)
    @Column(name = "invoice_no", length = 255)
    private String invoiceNo;

    @Size(max = 1000)
    @Column(name = "comment", length = 1000)
    private String comment;

    @Column(name = "is_credit")
    private Boolean isCredit;

    @Column(name = "is_credit_note")
    private Boolean isCreditNote;

    @Column(name = "is_dispute")
    private Boolean isDispute;

    @Size(max = 255)
    @Column(name = "doc_path", length = 255)
    private String docPath;

    @Column(name = "sub_total")
    private Float subTotal;

    @Column(name = "gst")
    private Float gst;

    @Column(name = "hst")
    private Float hst;

    @Column(name = "pst")
    private Float pst;

    @Column(name = "qst")
    private Float qst;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "balance_due")
    private Float balanceDue;

    @OneToMany(mappedBy = "apPayable")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApPayableCreditNotesTrans> apPayableCreditNotesTrans = new HashSet<>();

    @OneToMany(mappedBy = "apPayable")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApPayableCreditNotesTrans> creditUsedFrmAPPayables = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("apPayables")
    private ApPayeeType apPayeeType;

    @ManyToOne
    @JsonIgnoreProperties("apPayables")
    private ApPayee apPayee;

    @ManyToOne
    @JsonIgnoreProperties("apPayables")
    private ApCategoryType apCategoryType;

    @ManyToOne
    @JsonIgnoreProperties("apPayables")
    private WoPaymentMethod woPaymentMethod;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public ApPayable invoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getInvoiceAmount() {
        return invoiceAmount;
    }

    public ApPayable invoiceAmount(Integer invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
        return this;
    }

    public void setInvoiceAmount(Integer invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public ApPayable invoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getComment() {
        return comment;
    }

    public ApPayable comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean isIsCredit() {
        return isCredit;
    }

    public ApPayable isCredit(Boolean isCredit) {
        this.isCredit = isCredit;
        return this;
    }

    public void setIsCredit(Boolean isCredit) {
        this.isCredit = isCredit;
    }

    public Boolean isIsCreditNote() {
        return isCreditNote;
    }

    public ApPayable isCreditNote(Boolean isCreditNote) {
        this.isCreditNote = isCreditNote;
        return this;
    }

    public void setIsCreditNote(Boolean isCreditNote) {
        this.isCreditNote = isCreditNote;
    }

    public Boolean isIsDispute() {
        return isDispute;
    }

    public ApPayable isDispute(Boolean isDispute) {
        this.isDispute = isDispute;
        return this;
    }

    public void setIsDispute(Boolean isDispute) {
        this.isDispute = isDispute;
    }

    public String getDocPath() {
        return docPath;
    }

    public ApPayable docPath(String docPath) {
        this.docPath = docPath;
        return this;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public ApPayable subTotal(Float subTotal) {
        this.subTotal = subTotal;
        return this;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Float getGst() {
        return gst;
    }

    public ApPayable gst(Float gst) {
        this.gst = gst;
        return this;
    }

    public void setGst(Float gst) {
        this.gst = gst;
    }

    public Float getHst() {
        return hst;
    }

    public ApPayable hst(Float hst) {
        this.hst = hst;
        return this;
    }

    public void setHst(Float hst) {
        this.hst = hst;
    }

    public Float getPst() {
        return pst;
    }

    public ApPayable pst(Float pst) {
        this.pst = pst;
        return this;
    }

    public void setPst(Float pst) {
        this.pst = pst;
    }

    public Float getQst() {
        return qst;
    }

    public ApPayable qst(Float qst) {
        this.qst = qst;
        return this;
    }

    public void setQst(Float qst) {
        this.qst = qst;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public ApPayable totalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getBalanceDue() {
        return balanceDue;
    }

    public ApPayable balanceDue(Float balanceDue) {
        this.balanceDue = balanceDue;
        return this;
    }

    public void setBalanceDue(Float balanceDue) {
        this.balanceDue = balanceDue;
    }

    public Set<ApPayableCreditNotesTrans> getApPayableCreditNotesTrans() {
        return apPayableCreditNotesTrans;
    }

    public ApPayable apPayableCreditNotesTrans(Set<ApPayableCreditNotesTrans> apPayableCreditNotesTrans) {
        this.apPayableCreditNotesTrans = apPayableCreditNotesTrans;
        return this;
    }

    public ApPayable addApPayableCreditNotesTrans(ApPayableCreditNotesTrans apPayableCreditNotesTrans) {
        this.apPayableCreditNotesTrans.add(apPayableCreditNotesTrans);
        apPayableCreditNotesTrans.setApPayable(this);
        return this;
    }

    public ApPayable removeApPayableCreditNotesTrans(ApPayableCreditNotesTrans apPayableCreditNotesTrans) {
        this.apPayableCreditNotesTrans.remove(apPayableCreditNotesTrans);
        apPayableCreditNotesTrans.setApPayable(null);
        return this;
    }

    public void setApPayableCreditNotesTrans(Set<ApPayableCreditNotesTrans> apPayableCreditNotesTrans) {
        this.apPayableCreditNotesTrans = apPayableCreditNotesTrans;
    }

    public Set<ApPayableCreditNotesTrans> getCreditUsedFrmAPPayables() {
        return creditUsedFrmAPPayables;
    }

    public ApPayable creditUsedFrmAPPayables(Set<ApPayableCreditNotesTrans> apPayableCreditNotesTrans) {
        this.creditUsedFrmAPPayables = apPayableCreditNotesTrans;
        return this;
    }

    public ApPayable addCreditUsedFrmAPPayable(ApPayableCreditNotesTrans apPayableCreditNotesTrans) {
        this.creditUsedFrmAPPayables.add(apPayableCreditNotesTrans);
        apPayableCreditNotesTrans.setApPayable(this);
        return this;
    }

    public ApPayable removeCreditUsedFrmAPPayable(ApPayableCreditNotesTrans apPayableCreditNotesTrans) {
        this.creditUsedFrmAPPayables.remove(apPayableCreditNotesTrans);
        apPayableCreditNotesTrans.setApPayable(null);
        return this;
    }

    public void setCreditUsedFrmAPPayables(Set<ApPayableCreditNotesTrans> apPayableCreditNotesTrans) {
        this.creditUsedFrmAPPayables = apPayableCreditNotesTrans;
    }

    public ApPayeeType getApPayeeType() {
        return apPayeeType;
    }

    public ApPayable apPayeeType(ApPayeeType apPayeeType) {
        this.apPayeeType = apPayeeType;
        return this;
    }

    public void setApPayeeType(ApPayeeType apPayeeType) {
        this.apPayeeType = apPayeeType;
    }

    public ApPayee getApPayee() {
        return apPayee;
    }

    public ApPayable apPayee(ApPayee apPayee) {
        this.apPayee = apPayee;
        return this;
    }

    public void setApPayee(ApPayee apPayee) {
        this.apPayee = apPayee;
    }

    public ApCategoryType getApCategoryType() {
        return apCategoryType;
    }

    public ApPayable apCategoryType(ApCategoryType apCategoryType) {
        this.apCategoryType = apCategoryType;
        return this;
    }

    public void setApCategoryType(ApCategoryType apCategoryType) {
        this.apCategoryType = apCategoryType;
    }

    public WoPaymentMethod getWoPaymentMethod() {
        return woPaymentMethod;
    }

    public ApPayable woPaymentMethod(WoPaymentMethod woPaymentMethod) {
        this.woPaymentMethod = woPaymentMethod;
        return this;
    }

    public void setWoPaymentMethod(WoPaymentMethod woPaymentMethod) {
        this.woPaymentMethod = woPaymentMethod;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApPayable)) {
            return false;
        }
        return id != null && id.equals(((ApPayable) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApPayable{" +
            "id=" + getId() +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", invoiceAmount=" + getInvoiceAmount() +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", comment='" + getComment() + "'" +
            ", isCredit='" + isIsCredit() + "'" +
            ", isCreditNote='" + isIsCreditNote() + "'" +
            ", isDispute='" + isIsDispute() + "'" +
            ", docPath='" + getDocPath() + "'" +
            ", subTotal=" + getSubTotal() +
            ", gst=" + getGst() +
            ", hst=" + getHst() +
            ", pst=" + getPst() +
            ", qst=" + getQst() +
            ", totalAmount=" + getTotalAmount() +
            ", balanceDue=" + getBalanceDue() +
            "}";
    }
}
