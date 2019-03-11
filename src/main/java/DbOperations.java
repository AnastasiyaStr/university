import java.util.*;

import entity.Availability;
import entity.Department;
import entity.Worker;
import org.hibernate.*;
public class DbOperations {

    static Session sessionObj;

    // Method 1: This Method Used To Create A New Worker Record In The Database Table
    public static void createRecord() {
        int count = 0;
       Worker workerObj = null;
        Department departmentObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            for(int j = 101; j <= 103; j++) {

                departmentObj = new  Department();
                departmentObj.setName("Programming Department"+j);
                departmentObj.setStatus(false);
                sessionObj.save(departmentObj);
            }



            // Creating Transaction Entities
            for(int j = 101; j <= 105; j++) {
                count = count + 1;
                workerObj = new Worker();
                //workerObj.setId(j);
                workerObj.setFullName("Joey");
                workerObj.setAge(j);
                workerObj.setDepartment(departmentObj);
                workerObj.setAvailability(Availability.FULLTIME);
                sessionObj.save(workerObj);
            }

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            // logger.info("\nSuccessfully Created '" + count + "' Records In The Database!\n");
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

    // Method 2: This Method Is Used To Display The Records From The Database Table
    @SuppressWarnings("unchecked")
    public static List displayRecords() {
        List workersList = new ArrayList();
        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            workersList = sessionObj.createQuery("FROM Worker").list();
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
        return workersList;
    }

    // Method 3: This Method Is Used To Update A Record In The Database Table
    public static void updateRecord(int worker_id) {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entity
            Worker worObj = (Worker) sessionObj.get(Worker.class, worker_id);
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

    // Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
    public static void deleteRecord(Integer worker_id) {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            Worker workObj = findRecordById(worker_id);
            sessionObj.delete(workObj);

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            //logger.info("\nStudent With Id?= " + student_id + " Is Successfully Deleted From The Database!\n");
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                //logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    // Method 4(b): This Method To Find Particular Record In The Database Table
    public static Worker findRecordById(Integer find_worker_id) {
        Worker findWorkerObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            findWorkerObj = (Worker) sessionObj.load(Worker.class, find_worker_id);
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                //logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        return findWorkerObj;
    }

    // Method 5: This Method Is Used To Delete All Records From The Database Table
    public static void deleteAllRecords() {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            Query queryObj = sessionObj.createQuery("DELETE FROM Worker");
            queryObj.executeUpdate();

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            // logger.info("\nSuccessfully Deleted All Records From The Database Table!\n");
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                //logger.info("\n.......Transaction Is Being Rolled Back.......\n");
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
