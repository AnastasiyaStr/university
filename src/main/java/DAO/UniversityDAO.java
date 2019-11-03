package DAO;

import entity.Department;
import entity.Lector;
import entity.University;
import org.hibernate.Session;

public class UniversityDAO {
    static Session sessionObj;
    public static void createRecord() {

        University university = null;

        try {
            // Getting Session Object From SessionFactory
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entities

                university = new University();
                //university.setId(1);
                university.setUniversityName("Hogwarts");
                sessionObj.save(university);

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


}
