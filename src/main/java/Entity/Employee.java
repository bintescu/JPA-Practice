package Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "idemployees")
    private int id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "jobCategoryId")
    private JobCategory jobCategory;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    //    private int jobCategoryId;
//    private int departmentId;
    private boolean isManager;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private String address;
    private String CP;
    private String telephone;
    private String email;
    private LocalDate birthday;
    private int noChildren;
    private Double salary;
    private String studies;
    private String socialSecurityNumber;
    private boolean hasDrivingLicense;

    public Employee(){
    }

    public Employee(String firstName, String lastName, JobCategory jobCategoryId, Department departmentId, boolean isManager, LocalDate startDate, LocalDate endDate, boolean active, String address, String CP, String telephone, String email, LocalDate birthday, int noChildren, Double salary, String studies, String socialSecurityNumber, boolean hasDrivingLicense) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.jobCategory = jobCategoryId;
        jobCategoryId.addEmployees(this);

        this.department = departmentId;
        departmentId.addEmployees(this);

        this.isManager = isManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.address = address;
        this.CP = CP;
        this.telephone = telephone;
        this.email = email;
        this.birthday = birthday;
        this.noChildren = noChildren;
        this.salary = salary;
        this.studies = studies;
        this.socialSecurityNumber = socialSecurityNumber;
        this.hasDrivingLicense = hasDrivingLicense;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public void setJobCategoryId(JobCategory jobCategoryId) {
        this.jobCategory = jobCategoryId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jobCategory=" + jobCategory +
                ", department=" + department +
                ", isManager=" + isManager +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", active=" + active +
                ", address='" + address + '\'' +
                ", CP='" + CP + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", noChildren=" + noChildren +
                ", salary=" + salary +
                ", studies='" + studies + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", hasDrivingLicense=" + hasDrivingLicense +
                '}';
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getNoChildren() {
        return noChildren;
    }

    public void setNoChildren(int noChildren) {
        this.noChildren = noChildren;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public boolean isHasDrivingLicense() {
        return hasDrivingLicense;
    }

    public void setHasDrivingLicense(boolean hasDrivingLicense) {
        this.hasDrivingLicense = hasDrivingLicense;
    }
}
