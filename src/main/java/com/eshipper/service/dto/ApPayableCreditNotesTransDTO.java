package com.eshipper.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ApPayableCreditNotesTrans} entity.
 */
public class ApPayableCreditNotesTransDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String type;

    @Max(value = 11)
    private Integer amount;


    private Long apPayableId;

    private Long apPayeeId;

    private Long apPayableId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getApPayableId() {
        return apPayableId;
    }

    public void setApPayableId(Long apPayableId) {
        this.apPayableId = apPayableId;
    }

    public Long getApPayeeId() {
        return apPayeeId;
    }

    public void setApPayeeId(Long apPayeeId) {
        this.apPayeeId = apPayeeId;
    }

    public Long getApPayableId() {
        return apPayableId;
    }

    public void setApPayableId(Long apPayableId) {
        this.apPayableId = apPayableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO = (ApPayableCreditNotesTransDTO) o;
        if (apPayableCreditNotesTransDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), apPayableCreditNotesTransDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApPayableCreditNotesTransDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", amount=" + getAmount() +
            ", apPayable=" + getApPayableId() +
            ", apPayee=" + getApPayeeId() +
            ", apPayable=" + getApPayableId() +
            "}";
    }
}
