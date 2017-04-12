package hu.model.document;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import hu.model.hospital.ConsultationHourType;


@SuppressWarnings("serial")
@Entity
@Table(name = "documenttype_to_consultationhourtype")
public class DocumentTypeToConsultationHourType implements Serializable {

	private Long id;
	private ConsultationHourType consultationHourType;
	private DocumentType documentType;
	private Date validFrom;
	private Date validTo;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DocumentTypeToConsultationHourType() {
	}

	@ManyToOne
	@JoinColumn(name = "consultationHourTypeId", nullable = false)
	public ConsultationHourType getConsultationHourType() {
		return this.consultationHourType;
	}

	public void setConsultationHourType(ConsultationHourType consultationHourType) {
		this.consultationHourType = consultationHourType;
	}

	@ManyToOne
	@JoinColumn(name = "documentTypeId", nullable = false)
	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Column
	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

}