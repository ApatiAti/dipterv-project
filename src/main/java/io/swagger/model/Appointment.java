package io.swagger.model;

import java.util.Objects;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.web.util.json.SwaggerDateTimeJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
/**
 * Appointment
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T11:37:00.219Z")

public class Appointment   {
  @JsonProperty("appointmentId")
  private Long appointmentId = null;

  @JsonSerialize(using = SwaggerDateTimeJsonSerializer.class)
  @JsonProperty("beginDate")
  private DateTime beginDate = null;

  @JsonSerialize(using = SwaggerDateTimeJsonSerializer.class)
  @JsonProperty("endDate")
  private DateTime endDate = null;

  @JsonProperty("room")
  private String room = null;

  @JsonProperty("doctorsName")
  private String doctorsName = null;

  @JsonProperty("complaints")
  private String complaints = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("consultationHourId")
  private Long consultationHourId = null;

  @JsonProperty("departmentId")
  private Long departmentId = null;

  @JsonProperty("consultationTypeId")
  private Long consultationTypeId = null;

  public Appointment appointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
    return this;
  }

   /**
   * Id of Appointment from server
   * @return appointmentId
  **/
  @ApiModelProperty(value = "Id of Appointment from server")
  public Long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
  }

  public Appointment beginDate(DateTime beginDate) {
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

  public Appointment endDate(DateTime endDate) {
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

  public Appointment room(String room) {
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

  public Appointment doctorsName(String doctorsName) {
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

  public Appointment complaints(String complaints) {
    this.complaints = complaints;
    return this;
  }

   /**
   * Complaint of the patient
   * @return complaints
  **/
  @ApiModelProperty(value = "Complaint of the patient")
  public String getComplaints() {
    return complaints;
  }

  public void setComplaints(String complaints) {
    this.complaints = complaints;
  }

  public Appointment userName(String userName) {
    this.userName = userName;
    return this;
  }

   /**
   * Username of the patient
   * @return userName
  **/
  @ApiModelProperty(value = "Username of the patient")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Appointment consultationHourId(Long consultationHourId) {
    this.consultationHourId = consultationHourId;
    return this;
  }

   /**
   * Id of ConsultationHour from server
   * @return consultationHourId
  **/
  @ApiModelProperty(value = "Id of ConsultationHour from server")
  public Long getConsultationHourId() {
    return consultationHourId;
  }

  public void setConsultationHourId(Long consultationHourId) {
    this.consultationHourId = consultationHourId;
  }

  public Appointment departmentId(Long departmentId) {
    this.departmentId = departmentId;
    return this;
  }

   /**
   * Id of Department
   * @return departmentId
  **/
  @ApiModelProperty(value = "Id of Department")
  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  public Appointment consultationTypeId(Long consultationTypeId) {
    this.consultationTypeId = consultationTypeId;
    return this;
  }

   /**
   * Id of ConsultationHourType from server
   * @return consultationTypeId
  **/
  @ApiModelProperty(value = "Id of ConsultationHourType from server")
  public Long getConsultationTypeId() {
    return consultationTypeId;
  }

  public void setConsultationTypeId(Long consultationTypeId) {
    this.consultationTypeId = consultationTypeId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Appointment appointment = (Appointment) o;
    return Objects.equals(this.appointmentId, appointment.appointmentId) &&
        Objects.equals(this.beginDate, appointment.beginDate) &&
        Objects.equals(this.endDate, appointment.endDate) &&
        Objects.equals(this.room, appointment.room) &&
        Objects.equals(this.doctorsName, appointment.doctorsName) &&
        Objects.equals(this.complaints, appointment.complaints) &&
        Objects.equals(this.userName, appointment.userName) &&
        Objects.equals(this.consultationHourId, appointment.consultationHourId) &&
        Objects.equals(this.departmentId, appointment.departmentId) &&
        Objects.equals(this.consultationTypeId, appointment.consultationTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appointmentId, beginDate, endDate, room, doctorsName, complaints, userName, consultationHourId, departmentId, consultationTypeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Appointment {\n");
    
    sb.append("    appointmentId: ").append(toIndentedString(appointmentId)).append("\n");
    sb.append("    beginDate: ").append(toIndentedString(beginDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    doctorsName: ").append(toIndentedString(doctorsName)).append("\n");
    sb.append("    complaints: ").append(toIndentedString(complaints)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    consultationHourId: ").append(toIndentedString(consultationHourId)).append("\n");
    sb.append("    departmentId: ").append(toIndentedString(departmentId)).append("\n");
    sb.append("    consultationTypeId: ").append(toIndentedString(consultationTypeId)).append("\n");
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

