package DAO;

import entity.Degree;
import entity.Department;
import entity.Lector;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LectorDAO {
    private static final String SUCCESS_MSG = "\nSuccessfully Created Records In The Database!\n";
    private static final String ROLLBACK_MSG = "\nTransaction Is Being Rolled Back\n";
    private static final Double SALARY = 100.111;
    private static final Integer LECTORS_COUNT = 5;
    private static Session SESSION;

    public static void createRecord() {
        Lector lector = null;
        try {
            SESSION = HibernateUtil.getSessionFactory().openSession();
            SESSION.beginTransaction();
            for (int j = 1; j <= LECTORS_COUNT; j++) {
                lector = new Lector();
                lector.setId(j);
                lector.setFullName("Joey Black" + j);
                lector.setSalary(SALARY+j);
                Set<Department> departments = new HashSet<>();
                departments.add( SESSION.get(Department.class, j));
                departments.add( SESSION.get(Department.class, new Random().nextInt(6)+1));
                lector.setDepartments(departments);
                lector.setDegree(j%2==0?Degree.ASSISTANT:Degree.ASSOSIATE_PROFESSOR);
                SESSION.save(lector);
            }
            SESSION.getTransaction().commit();
            System.out.println(SUCCESS_MSG);
        } catch (Exception sqlException) {
            if (null != SESSION.getTransaction()) {
                System.out.println(ROLLBACK_MSG);
                SESSION.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (SESSION != null) {
                SESSION.close();
            }
        }
    }

    public static void globalSearchByTemplate(String template) {
        try {
            SESSION = HibernateUtil.getSessionFactory().openSession();
            SESSION.beginTransaction();
        CriteriaBuilder builder = SESSION.getCriteriaBuilder();
        CriteriaQuery<Lector> criteriaQuery = builder.createQuery(Lector.class);
        Root<Lector> lector = criteriaQuery.from(Lector.class);
        criteriaQuery.where(builder.like(lector.get("fullName"), "%" + template + "%"));
        Query<Lector> query = SESSION.createQuery(criteriaQuery);
            List<Lector> lectors = query.getResultList();
            if(!lectors.isEmpty())
            System.out.println("Global search by "+ template + " is "+ lectors );
            else{
                System.out.println("No data found!");
            }
            SESSION.getTransaction().commit();
            System.out.println(SUCCESS_MSG);
        } catch (Exception sqlException) {
            if (null != SESSION.getTransaction()) {
                System.out.println(ROLLBACK_MSG);
                SESSION.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (SESSION != null) {
                SESSION.close();
            }
        }
    }


    public  static String options() {
        return "5 - Global search by template - \n{ENTER TEMPLATE}";
    }
}
