package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * ConsultationHourType
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T11:37:00.219Z")

public class ConsultationHourType   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("consultationTypeid")
  private Long consultationTypeid = null;

  @JsonProperty("name")
  private String name = null;

  public ConsultationHourType id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Id of ConsultationHourType
   * @return id
  **/
  @ApiModelProperty(value = "Id of ConsultationHourType")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ConsultationHourType consultationTypeid(Long consultationTypeid) {
    this.consultationTypeid = consultationTypeid;
    return this;
  }

   /**
   * Id of ConsultationHourType from server
   * @return consultationTypeid
  **/
  @ApiModelProperty(value = "Id of ConsultationHourType from server")
  public Long getConsultationTypeid() {
    return consultationTypeid;
  }

  public void setConsultationTypeid(Long consultationTypeid) {
    this.consultationTypeid = consultationTypeid;
  }

  public ConsultationHourType name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of the ConsultationHourType
   * @return name
  **/
  @ApiModelProperty(value = "Name of the ConsultationHourType")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsultationHourType consultationHourType = (ConsultationHourType) o;
    return Objects.equals(this.id, consultationHourType.id) &&
        Objects.equals(this.consultationTypeid, consultationHourType.consultationTypeid) &&
        Objects.equals(this.name, consultationHourType.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, consultationTypeid, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsultationHourType {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    consultationTypeid: ").append(toIndentedString(consultationTypeid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

