package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;
import javax.validation.constraints.*;
/**
 * ConsultationHourSearch
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T21:43:41.978Z")

public class ConsultationHourSearch   {
  @JsonProperty("departmentId")
  private Long departmentId = null;

  @JsonProperty("typeId")
  private Long typeId = null;

  @JsonProperty("beginDate")
  private DateTime beginDate = null;

  @JsonProperty("endDate")
  private DateTime endDate = null;

  public ConsultationHourSearch departmentId(Long departmentId) {
    this.departmentId = departmentId;
    return this;
  }

   /**
   * Department's id
   * @return departmentId
  **/
  @ApiModelProperty(value = "Department's id")
  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  public ConsultationHourSearch typeId(Long typeId) {
    this.typeId = typeId;
    return this;
  }

   /**
   * Id of the consultationHourType
   * @return typeId
  **/
  @ApiModelProperty(value = "Id of the consultationHourType")
  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public ConsultationHourSearch beginDate(DateTime beginDate) {
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

  public ConsultationHourSearch endDate(DateTime endDate) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsultationHourSearch consultationHourSearch = (ConsultationHourSearch) o;
    return Objects.equals(this.departmentId, consultationHourSearch.departmentId) &&
        Objects.equals(this.typeId, consultationHourSearch.typeId) &&
        Objects.equals(this.beginDate, consultationHourSearch.beginDate) &&
        Objects.equals(this.endDate, consultationHourSearch.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(departmentId, typeId, beginDate, endDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsultationHourSearch {\n");
    
    sb.append("    departmentId: ").append(toIndentedString(departmentId)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    beginDate: ").append(toIndentedString(beginDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
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

