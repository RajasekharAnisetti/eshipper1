package com.eshipper.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ApPayable} entity.
 */
public class ApPayableDTO implements Serializable {

    private Long id;

    private LocalDate invoiceDate;

    @Max(value = 11)
    private Integer invoiceAmount;

    @Size(max = 255)
    private String invoiceNo;

    @Size(max = 1000)
    private String comment;

    private Boolean isCredit;

    private Boolean isCreditNote;

    private Boolean isDispute;

    @Size(max = 255)
    private String docPath;

    private Float subTotal;

    private Float gst;

    private Float hst;

    private Float pst;

    private Float qst;

    private Float totalAmount;

    private Float balanceDue;


    private Long apPayeeTypeId;

    private Long apPayeeId;

    private Long apCategoryTypeId;

    private Long woPaymentMethodId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Integer invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean isIsCredit() {
        return isCredit;
    }

    public void setIsCredit(Boolean isCredit) {
        this.isCredit = isCredit;
    }

    public Boolean isIsCreditNote() {
        return isCreditNote;
    }

    public void setIsCreditNote(Boolean isCreditNote) {
        this.isCreditNote = isCreditNote;
    }

    public Boolean isIsDispute() {
        return isDispute;
    }

    public void setIsDispute(Boolean isDispute) {
        this.isDispute = isDispute;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Float getGst() {
        return gst;
    }

    public void setGst(Float gst) {
        this.gst = gst;
    }

    public Float getHst() {
        return hst;
    }

    public void setHst(Float hst) {
        this.hst = hst;
    }

    public Float getPst() {
        return pst;
    }

    public void setPst(Float pst) {
        this.pst = pst;
    }

    public Float getQst() {
        return qst;
    }

    public void setQst(Float qst) {
        this.qst = qst;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getBalanceDue() {
        return balanceDue;
    }

    public void setBalanceDue(Float balanceDue) {
        this.balanceDue = balanceDue;
    }

    public Long getApPayeeTypeId() {
        return apPayeeTypeId;
    }

    public void setApPayeeTypeId(Long apPayeeTypeId) {
        this.apPayeeTypeId = apPayeeTypeId;
    }

    public Long getApPayeeId() {
        return apPayeeId;
    }

    public void setApPayeeId(Long apPayeeId) {
        this.apPayeeId = apPayeeId;
    }

    public Long getApCategoryTypeId() {
        return apCategoryTypeId;
    }

    public void setApCategoryTypeId(Long apCategoryTypeId) {
        this.apCategoryTypeId = apCategoryTypeId;
    }

    public Long getWoPaymentMethodId() {
        return woPaymentMethodId;
    }

    public void setWoPaymentMethodId(Long woPaymentMethodId) {
        this.woPaymentMethodId = woPaymentMethodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApPayableDTO apPayableDTO = (ApPayableDTO) o;
        if (apPayableDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), apPayableDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApPayableDTO{" +
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
            ", apPayeeType=" + getApPayeeTypeId() +
            ", apPayee=" + getApPayeeId() +
            ", apCategoryType=" + getApCategoryTypeId() +
            ", woPaymentMethod=" + getWoPaymentMethodId() +
            "}";
    }
}
