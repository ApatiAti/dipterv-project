package io.swagger.model;

import java.util.Objects;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.web.util.json.SwaggerDateTimeJsonDeserializer;
import hu.web.util.json.SwaggerDateTimeJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
/**
 * ConsultationHour
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T21:36:14.400Z")

public class ConsultationHour   {
  @JsonProperty("consultationHourId")
  private Long consultationHourId = null;

  @JsonProperty("typeId")
  private Long typeId = null;

  @JsonSerialize(using = SwaggerDateTimeJsonSerializer.class)
  @JsonDeserialize(using = SwaggerDateTimeJsonDeserializer.class)
  @JsonProperty("beginDate")
  private DateTime beginDate = null;

  @JsonSerialize(using = SwaggerDateTimeJsonSerializer.class)
  @JsonDeserialize(using = SwaggerDateTimeJsonDeserializer.class)
  @JsonProperty("endDate")
  private DateTime endDate = null;

  @JsonProperty("room")
  private String room = null;

  @JsonProperty("doctorsName")
  private String doctorsName = null;

  @JsonProperty("maxPatientCount")
  private Integer maxPatientCount = null;

  @JsonProperty("currentPatientCount")
  private Integer currentPatientCount = null;

  public ConsultationHour consultationHourId(Long consultationHourId) {
    this.consultationHourId = consultationHourId;
    return this;
  }

   /**
   * Id of ConsultationHour
   * @return consultationHourId
  **/
  @ApiModelProperty(value = "Id of ConsultationHour")
  public Long getConsultationHourId() {
    return consultationHourId;
  }

  public void setConsultationHourId(Long consultationHourId) {
    this.consultationHourId = consultationHourId;
  }

  public ConsultationHour typeId(Long typeId) {
    this.typeId = typeId;
    return this;
  }

   /**
   * Id of ConsultationHourType from server
   * @return typeId
  **/
  @ApiModelProperty(value = "Id of ConsultationHourType from server")
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

  public ConsultationHour room(String room) {
    this.room = room;
    return this;
  }

   /**
   * Room of the ConsultationHour
   * @return room
  **/
  @ApiModelProperty(value = "Room of the ConsultationHour")
  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public ConsultationHour doctorsName(String doctorsName) {
    this.doctorsName = doctorsName;
    return this;
  }

   /**
   * Doctors name who goint to holf the ConsultationHour
   * @return doctorsName
  **/
  @ApiModelProperty(value = "Doctors name who goint to holf the ConsultationHour")
  public String getDoctorsName() {
    return doctorsName;
  }

  public void setDoctorsName(String doctorsName) {
    this.doctorsName = doctorsName;
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
    return Objects.equals(this.consultationHourId, consultationHour.consultationHourId) &&
        Objects.equals(this.typeId, consultationHour.typeId) &&
        Objects.equals(this.beginDate, consultationHour.beginDate) &&
        Objects.equals(this.endDate, consultationHour.endDate) &&
        Objects.equals(this.room, consultationHour.room) &&
        Objects.equals(this.doctorsName, consultationHour.doctorsName) &&
        Objects.equals(this.maxPatientCount, consultationHour.maxPatientCount) &&
        Objects.equals(this.currentPatientCount, consultationHour.currentPatientCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(consultationHourId, typeId, beginDate, endDate, room, doctorsName, maxPatientCount, currentPatientCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsultationHour {\n");
    
    sb.append("    consultationHourId: ").append(toIndentedString(consultationHourId)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    beginDate: ").append(toIndentedString(beginDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    doctorsName: ").append(toIndentedString(doctorsName)).append("\n");
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

