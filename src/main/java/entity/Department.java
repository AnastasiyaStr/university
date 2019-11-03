package entity;

import entity.Lector;
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
@ToString
@Table(name="department")


public class Department {
    @Id
    @Column(name="department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="department_name")
    private String departmentName;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn (name="head_of_department")
    private Lector headOfDepartment;

    @ManyToMany(mappedBy = "departments")
    private Set<Lector> lectors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "university_id")
    private  University university;

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

