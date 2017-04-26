package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;




/**
 * ConsultationHour
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T03:07:35.390+02:00")

public class ConsultationHour   {
  private Long id = null;

  private Long typeId = null;

  private DateTime beginDate = null;

  private DateTime endDate = null;

  private Integer maxPatientCount = null;

  private Integer currentPatientCount = null;

  public ConsultationHour id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Id of ConsultationHour
   * @return id
  **/
  @ApiModelProperty(value = "Id of ConsultationHour")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ConsultationHour typeId(Long typeId) {
    this.typeId = typeId;
    return this;
  }

   /**
   * Id of ConsultationHourType
   * @return typeId
  **/
  @ApiModelProperty(value = "Id of ConsultationHourType")
  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public ConsultationHour beginDate(DateTime beginDate) {
    this.beginDate = beginDate;
    return this;
  }

   /**
   * Start date of the ConsultationHour
   * @return beginDate
  **/
  @ApiModelProperty(value = "Start date of the ConsultationHour")
  public DateTime getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(DateTime beginDate) {
    this.beginDate = beginDate;
  }

  public ConsultationHour endDate(DateTime endDate) {
    this.endDate = endDate;
    return this;
  }

   /**
   * End date of the ConsultationHour
   * @return endDate
  **/
  @ApiModelProperty(value = "End date of the ConsultationHour")
  public DateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(DateTime endDate) {
    this.endDate = endDate;
  }

  public ConsultationHour maxPatientCount(Integer maxPatientCount) {
    this.maxPatientCount = maxPatientCount;
    return this;
  }

   /**
   * Maxinum number of patient count allowed in this consultationHour
   * @return maxPatientCount
  **/
  @ApiModelProperty(value = "Maxinum number of patient count allowed in this consultationHour")
  public Integer getMaxPatientCount() {
    return maxPatientCount;
  }

  public void setMaxPatientCount(Integer maxPatientCount) {
    this.maxPatientCount = maxPatientCount;
  }

  public ConsultationHour currentPatientCount(Integer currentPatientCount) {
    this.currentPatientCount = currentPatientCount;
    return this;
  }

   /**
   * Current number of patient count in this consultationHour
   * @return currentPatientCount
  **/
  @ApiModelProperty(value = "Current number of patient count in this consultationHour")
  public Integer getCurrentPatientCount() {
    return currentPatientCount;
  }

  public void setCurrentPatientCount(Integer currentPatientCount) {
    this.currentPatientCount = currentPatientCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsultationHour consultationHour = (ConsultationHour) o;
    return Objects.equals(this.id, consultationHour.id) &&
        Objects.equals(this.typeId, consultationHour.typeId) &&
        Objects.equals(this.beginDate, consultationHour.beginDate) &&
        Objects.equals(this.endDate, consultationHour.endDate) &&
        Objects.equals(this.maxPatientCount, consultationHour.maxPatientCount) &&
        Objects.equals(this.currentPatientCount, consultationHour.currentPatientCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typeId, beginDate, endDate, maxPatientCount, currentPatientCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsultationHour {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    beginDate: ").append(toIndentedString(beginDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    maxPatientCount: ").append(toIndentedString(maxPatientCount)).append("\n");
    sb.append("    currentPatientCount: ").append(toIndentedString(currentPatientCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

