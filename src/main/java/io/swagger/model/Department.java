package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ConsultationHourType;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * Department
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T11:37:00.219Z")

public class Department   {
  @JsonProperty("departmentId")
  private Long departmentId = null;

  @JsonProperty("departmentName")
  private String departmentName = null;

  @JsonProperty("consultationHourType")
  private List<ConsultationHourType> consultationHourType = new ArrayList<ConsultationHourType>();

  public Department departmentId(Long departmentId) {
    this.departmentId = departmentId;
    return this;
  }

   /**
   * Id of department from server
   * @return departmentId
  **/
  @ApiModelProperty(value = "Id of department from server")
  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  public Department departmentName(String departmentName) {
    this.departmentName = departmentName;
    return this;
  }

   /**
   * Name of the department
   * @return departmentName
  **/
  @ApiModelProperty(value = "Name of the department")
  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public Department consultationHourType(List<ConsultationHourType> consultationHourType) {
    this.consultationHourType = consultationHourType;
    return this;
  }

  public Department addConsultationHourTypeItem(ConsultationHourType consultationHourTypeItem) {
    this.consultationHourType.add(consultationHourTypeItem);
    return this;
  }

   /**
   * Get consultationHourType
   * @return consultationHourType
  **/
  @ApiModelProperty(value = "")
  public List<ConsultationHourType> getConsultationHourType() {
    return consultationHourType;
  }

  public void setConsultationHourType(List<ConsultationHourType> consultationHourType) {
    this.consultationHourType = consultationHourType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Department department = (Department) o;
    return Objects.equals(this.departmentId, department.departmentId) &&
        Objects.equals(this.departmentName, department.departmentName) &&
        Objects.equals(this.consultationHourType, department.consultationHourType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(departmentId, departmentName, consultationHourType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Department {\n");
    
    sb.append("    departmentId: ").append(toIndentedString(departmentId)).append("\n");
    sb.append("    departmentName: ").append(toIndentedString(departmentName)).append("\n");
    sb.append("    consultationHourType: ").append(toIndentedString(consultationHourType)).append("\n");
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

