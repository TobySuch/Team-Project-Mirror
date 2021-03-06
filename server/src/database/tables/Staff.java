package database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * This class maps to the Staff table in the database.
 *
 * @author Marcus Messer
 */
@Entity
@Table(name = "STAFF")
public class Staff {

  /**
   * This filed stores the employee number. It is an auto generated incremented number.
   */
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private Long employeeNumber;

  /**
   * This field stores the first name of the employee.
   */
  private String firstName;

  /**
   * This field stores the surname of the employee.
   */
  private String surname;

  /**
   * This field stores the password for the employee.
   */
  @Column(name = "password")
  private String password;
  /**
   * This field stores the department the employee belongs too.
   */
  @Column(name = "department")
  private Department department;

  /**
   * This field store which franchise the employee belongs too.
   */
  @ManyToOne
  @JoinColumn(name = "franchiseId", nullable = false)
  private Franchise franchise;

  /**
   * This empty constructor is used by hibernate.
   */
  public Staff() {
    //Empty Body
  }

  /**
   * This constructor allows us to create new employees.
   *
   * @param firstName The first name of the new employee
   * @param surname The surname of the new employee
   * @param password The new employees password.
   * @param department The new employees department.
   * @param franchise The store the employee belongs too.
   */
  public Staff(String firstName, String surname, String password, Department department,
      Franchise franchise) {
    this.password = password;
    this.department = department;
    this.franchise = franchise;
    this.firstName = firstName;
    this.surname = surname;
  }

  public Long getEmployeeNumber() {
    return employeeNumber;
  }

  public void setEmployeeNumber(Long employeeNumber) {
    this.employeeNumber = employeeNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Franchise getFranchise() {
    return franchise;
  }

  public void setFranchise(Franchise franchise) {
    this.franchise = franchise;
  }

  @Override
  public String toString() {
    return "Staff{" +
        "employeeNumber=" + employeeNumber +
        ", password='" + password + '\'' +
        ", department=" + department +
        ", franchise=" + franchise +
        '}';
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
}