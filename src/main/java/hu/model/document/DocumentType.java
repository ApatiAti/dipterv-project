package hu.model.document;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hu.model.document.enums.DocumentTypeEnum;
import hu.model.document.enums.ExtensionTypes;


@SuppressWarnings("serial")
@Entity
@Table(name = "DocumentType")
public class DocumentType implements Serializable {

	private Long id;
	private ExtensionTypes extensionType;
	private int maxSizeKB;
	private int minSizeKB;
	private DocumentTypeEnum typeName;

	public DocumentType() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ExtensionTypes getExtensionType() {
		return this.extensionType;
	}

	public void setExtensionType(ExtensionTypes extensionType) {
		this.extensionType = extensionType;
	}

	@Column(name = "maxSize_kB")
	public int getMaxSizeKB() {
		return this.maxSizeKB;
	}

	public void setMaxSizeKB(int maxSize) {
		this.maxSizeKB = maxSize;
	}

	@Column(name = "minSize_kB")
	public int getMinSizeKB() {
		return this.minSizeKB;
	}

	public void setMinSizeKB(int minSize) {
		this.minSizeKB = minSize;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 255)
	public DocumentTypeEnum getTypeName() {
		return this.typeName;
	}

	public void setTypeName(DocumentTypeEnum typeName) {
		this.typeName = typeName;
	}

}