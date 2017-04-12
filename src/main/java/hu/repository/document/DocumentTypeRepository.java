package hu.repository.document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hu.model.document.DocumentType;
import hu.model.document.enums.DocumentTypeEnum;
import hu.model.document.enums.ExtensionTypes;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

	DocumentType findByTypeName(String name);

	@Query("SELECT DISTINCT dt_ch_type.documentType.typeName FROM DocumentTypeToConsultationHourType dt_ch_type"
			+ " WHERE dt_ch_type.consultationHourType.id  = :consultationHourTypeId"
				+ " and dt_ch_type.validFrom <= CURRENT_DATE"
				+ " and ( dt_ch_type.validTo is null or dt_ch_type.validTo >= CURRENT_DATE)")
	List<DocumentTypeEnum> findTypeNameByConsultationHourTypeIdAndSysDate(@Param(value="consultationHourTypeId") Long consultationHourTypeId);

	
	@Query("SELECT dt_ch_type.documentType FROM DocumentTypeToConsultationHourType dt_ch_type"
			+ " WHERE dt_ch_type.consultationHourType.id  = :consultationHourTypeId"
				+ " and dt_ch_type.validFrom <= CURRENT_DATE"
				+ " and ( dt_ch_type.validTo is null or dt_ch_type.validTo >= CURRENT_DATE)"
				+ " and dt_ch_type.documentType.extensionType = :extensionType"
				+ " and dt_ch_type.documentType.typeName = :documentType")
	DocumentType getDocumentumTypeForUplodedFile(@Param(value="consultationHourTypeId")Long id
			, @Param(value="documentType") DocumentTypeEnum documentTypeEnum
			, @Param(value="extensionType") ExtensionTypes extensionType);

}
