package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;




/**
 * Appointment
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T03:07:35.390+02:00")

public class Appointment   {
  private Long appointmentId = null;

  private DateTime beginDate = null;

  private DateTime endDate = null;

  private String room = null;

  private String doctorsName = null;

  private String complaints = null;

  private Long userId = null;

  private Long consultationHourId = null;

  public Appointment appointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
    return this;
  }

   /**
   * Id of Appointment
   * @return appointmentId
  **/
  @ApiModelProperty(value = "Id of Appointment")
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

  public Appointment userId(Long userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Id of the patient
   * @return userId
  **/
  @ApiModelProperty(value = "Id of the patient")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Appointment consultationHourId(Long consultationHourId) {
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
        Objects.equals(this.userId, appointment.userId) &&
        Objects.equals(this.consultationHourId, appointment.consultationHourId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appointmentId, beginDate, endDate, room, doctorsName, complaints, userId, consultationHourId);
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
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    consultationHourId: ").append(toIndentedString(consultationHourId)).append("\n");
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

