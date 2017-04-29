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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T11:37:00.219Z")

public class ConsultationHourSearch   {
  @JsonProperty("departmentName")
  private String departmentName = null;

  @JsonProperty("type")
  private Long type = null;

  @JsonProperty("beginDate")
  private DateTime beginDate = null;

  @JsonProperty("endDate")
  private DateTime endDate = null;

  public ConsultationHourSearch departmentName(String departmentName) {
    this.departmentName = departmentName;
    return this;
  }

   /**
   * Department's name
   * @return departmentName
  **/
  @ApiModelProperty(value = "Department's name")
  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public ConsultationHourSearch type(Long type) {
    this.type = type;
    return this;
  }

   /**
   * Type of the consultationHour
   * @return type
  **/
  @ApiModelProperty(value = "Type of the consultationHour")
  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
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
    return Objects.equals(this.departmentName, consultationHourSearch.departmentName) &&
        Objects.equals(this.type, consultationHourSearch.type) &&
        Objects.equals(this.beginDate, consultationHourSearch.beginDate) &&
        Objects.equals(this.endDate, consultationHourSearch.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(departmentName, type, beginDate, endDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsultationHourSearch {\n");
    
    sb.append("    departmentName: ").append(toIndentedString(departmentName)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

