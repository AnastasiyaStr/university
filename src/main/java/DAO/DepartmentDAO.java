package DAO;

import entity.Degree;
import entity.Department;
import entity.Lector;
import entity.University;
import org.hibernate.Session;
import org.hibernate.query.Query;
//import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartmentDAO {
    static Session sessionObj;

    public static void createRecord() {

        Department department = null;

        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entities
            for (int j = 1; j <= 5; j++) {
                department = new Department();
                department.setId(j);
                department.setDepartmentName("Hey" + j);
                department.setHeadOfDepartment((Lector) sessionObj.get(Lector.class, j));
                department.setUniversity((University) sessionObj.get(University.class, 1));
                sessionObj.save(department);
            }


            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();

            System.out.println("\nSuccessfully Created Records In The Database!\n");
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }


    public static int showCountOfEmployee(String departmentName) {
        sessionObj = HibernateUtil.getSessionFactory().openSession();
        // Getting Transaction Object From Session Object
        sessionObj.beginTransaction();

        CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        query.select(root).where(builder.equal(root.get("departmentName"), "Hey1"));
        Query<Department> q = sessionObj.createQuery(query);
        Department department = q.getSingleResult();//q.getSingleResult();
        // System.out.println(q.getResultList());
        return department.getLectors().size(); //department.getLectors().size();
    }

    public static Double showAverageSalaryForDepartment(String departmentName) {
        sessionObj = HibernateUtil.getSessionFactory().openSession();
        // Getting Transaction Object From Session Object
        sessionObj.beginTransaction();
        CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        query.select(root).where(builder.equal(root.get("departmentName"), "Hey1"));
        Query<Department> q = sessionObj.createQuery(query);
        Department department = q.getSingleResult();//q.getSingleResult();
        // System.out.println(q.getResultList());
        return department.getLectors().stream()
                .mapToDouble(Lector::getSalary)
                .average()
                .orElse(0);
        //CriteriaBuilder builder = sessionObj.getCriteriaBuilder();

    }


    public static void getLectorsByRank(String departmentname) {
        sessionObj = HibernateUtil.getSessionFactory().openSession();
        // Getting Transaction Object From Session Object
        sessionObj.beginTransaction();
        List totalStudents = sessionObj.createNamedQuery("get_by_rank").setParameter("degree", Degree.ASSISTANT).setParameter("dep_name","Hey1").getResultList();
        System.out.println("Total Students: " + totalStudents);
        //        sessionObj = HibernateUtil.getSessionFactory().openSession();
//        // Getting Transaction Object From Session Object
//        sessionObj.beginTransaction();
//        CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
//        CriteriaQuery<Lector> query = builder.createQuery(Lector.class);
//        Root<Lector> employee = query.from(Lector.class);
//        ListJoin<Lector, Department> tasks = employee.join(Department_.);
//        query.select(employee)
//                .where(cb.equal(tasks.get(Task_.supervisor), "Denise"))
//                .distinct(true);
//        Query<Employee> query = em.createQuery(query);
//        query.getResultList().forEach(System.out::println);

    }

//    public static Lector showHeadOfDepartment(){
//
//    }




}


