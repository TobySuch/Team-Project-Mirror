package database.tables;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class maps the staff session the database.
 *
 * @author Marcus Messer
 */
@Entity
@Table(name = "STAFF_SESSION")
public class StaffSession {

  /**
   * This field is a random hash generated by the server, it should be unique for each session.
   */
  @Id
  private String staffSessionId;

  /**
   * This field relates the staff session to the <code>Staff</code> table.
   */
  @ManyToOne
  @JoinColumn(name = "employeeNumber")
  private Staff staff;

  /**
   * This empty constructor is used by Hibernate.
   */
  public StaffSession() {
    // Empty Body
  }

  /**
   * This constructor is used to add new staff sessions to the database.
   *
   * @param staffSessionId The random has of the session.
   * @param staff The staff member related to the session.
   */
  public StaffSession(String staffSessionId, Staff staff) {
    this.staffSessionId = staffSessionId;
    this.staff = staff;
  }

  public Staff getStaff() {
    return staff;
  }

  public void setStaff(Staff staff) {
    this.staff = staff;
  }

  public String getStaffSessionId() {
    return staffSessionId;
  }

  public void setStaffSessionId(String staffSessionId) {
    this.staffSessionId = staffSessionId;
  }

  @Override
  public String toString() {
    return "StaffSession{" +
        "staffSessionId='" + staffSessionId + '\'' +
        ", staff=" + staff +
        '}';
  }
}
