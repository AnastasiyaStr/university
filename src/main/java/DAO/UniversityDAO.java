package DAO;

import entity.University;
import org.hibernate.Session;

/**
 * class to access info about university in database
 */
public class UniversityDAO {
    private static final String SUCCESS_MSG = "\nSuccessfully Created Records In The Database!\n";
    private static final String ROLLBACK_MSG = "\nTransaction Is Being Rolled Back\n";
    private static Session SESSION;

    public static void createRecord() {
        University university = null;
        try {
            SESSION = HibernateUtil.getSessionFactory().openSession();
            SESSION.beginTransaction();
            university = new University();
            university.setUniversityName("Hogwarts");
            SESSION.save(university);
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


}
