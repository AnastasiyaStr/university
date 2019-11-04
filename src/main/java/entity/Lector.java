package entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQuery(name="get_by_rank", query=("SELECT p FROM Lector p JOIN p.departments c WHERE p.degree= :degree and c.departmentName= :dep_name"))
//@NamedQuery(
//        name="findAllCustomersWithName",
//        query="SELECT c FROM Customer c WHERE c.name LIKE :custName"
//)
@Table(name="lector")
public class Lector {
    @Id
    @Column(name="lector_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="degree")
    private Degree degree;

    @Column(name="salary")
    private Double salary;

    @ManyToMany( cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Lector_Department",
            joinColumns = { @JoinColumn(name = "lector_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") }
    )
    Set<Department> departments = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
