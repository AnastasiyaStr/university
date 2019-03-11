import org.hibernate.Session;
import java.util.*;

import entity.Availability;
import entity.Department;
import entity.Worker;
import org.hibernate.*;
public class DepartmentDAO {
    static Session sessionObj;

    // Method 1: This Method Used To Create A New Worker Record In The Database Table
    public static void createRecord() {
        int count = 0;
        Department departmentObj = null;

        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            for (int j = 101; j <= 103; j++) {
                departmentObj = new Department();
                departmentObj.setName("Programming Department" + j);
                departmentObj.setStatus(false);
                sessionObj.save(departmentObj);
            }

            sessionObj.getTransaction().commit();
            // logger.info("\nSuccessfully Created '" + count + "' Records In The Database!\n");
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                // logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    // Method 2: This Method Is Used To Display The Records From The Database Table
    @SuppressWarnings("unchecked")
    public static List displayRecords() {
        List departmentList = new ArrayList();
        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            departmentList = sessionObj.createQuery("FROM Department").list();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                // logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return departmentList;
    }
    // Method 3: This Method Is Used To Update A Record In The Database Table
    public static void updateRecord(int department_id) {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entity
            Department depObj = (Department) sessionObj.get(Department.class, department_id);
            worObj.setFullName("Java Code Geek");
            worObj.setAge(200);
            worObj.setAvailability(Availability.PARTTIME);


            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            // logger.info("\nStudent With Id?= " + student_id + " Is Successfully Updated In The Database!\n");
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                // logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }

}