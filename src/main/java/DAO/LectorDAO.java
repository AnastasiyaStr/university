package DAO;

import entity.Degree;
import entity.Department;
import entity.Lector;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LectorDAO {

    static Session sessionObj;

    // Method 1: This Method Used To Create A New Worker Record In The Database Table
    public static void createRecord() {

       Lector lector = null;

        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entities
            for(int j = 1; j <= 5; j++) {

                lector = new Lector();
                lector.setId(j);
                lector.setFirstName("Joey"+j);
                lector.setLastName("Joey"+j);
                lector.setSalary(100+0.1);
                Set<Department> departments = new HashSet<>();
                departments.add((Department) sessionObj.get(Department.class, j));
                departments.add((Department) sessionObj.get(Department.class, 1));
                lector.setDepartments(departments);
                lector.setDegree(Degree.ASSISTANT);
                sessionObj.save(lector);
            }
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();

            System.out.println("\nSuccessfully Created Records In The Database!\n");
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }

   public static List<Lector> globalSearchByTemplate(String template){
// Getting Session Object From SessionFactory
        sessionObj = HibernateUtil.getSessionFactory().openSession();
        // Getting Transaction Object From Session Object
        sessionObj.beginTransaction();
        CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
        CriteriaQuery<Lector> criteriaQuery = builder.createQuery(Lector.class);
        Root<Lector> lector = criteriaQuery.from(Lector.class);
        criteriaQuery.where(builder.like(lector.get("firstName"),"%"+template+"%"));
        Query<Lector> query = sessionObj.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public String toString() {
        return "\n5 - SEARCH BY TEMPLATE";
    }
}
