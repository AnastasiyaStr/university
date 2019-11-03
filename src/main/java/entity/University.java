package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="university")
public class University {
    @Id
    @Column(name="university_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="university_name")
    private String universityName;

    @OneToMany(mappedBy="university")
    private Set<Department> departments = new HashSet<>();
}
