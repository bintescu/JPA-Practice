package Entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobcategories")
public class JobCategory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idjobCategories")
    private int Id;

    private String name;

    @OneToMany(mappedBy = "jobCategory", cascade = CascadeType.REMOVE)
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployees(Employee employee){
        employees.add(employee);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public JobCategory(){
        employees = new ArrayList<Employee>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JobCategory{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
