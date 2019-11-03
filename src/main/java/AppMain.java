import DAO.DepartmentDAO;
import DAO.LectorDAO;
import DAO.UniversityDAO;

public class AppMain {
    public static void main(String[] args) {
        System.out.println(".......Hibernate Crud Operations Example.......\n");
        System.out.println("\n=======CREATE RECORDS WORKER=======\n");
        UniversityDAO.createRecord();
        DepartmentDAO.createRecord();
        LectorDAO.createRecord();
        System.out.println("COUNT "+DepartmentDAO.showCountOfEmployee("Hey1"));
        System.out.println("AVG "+DepartmentDAO.showAverageSalaryForDepartment("Hey1"));
        System.out.println("Lectors: "+LectorDAO.globalSearchByTemplate("Joey1"));
DepartmentDAO.getLectorsByRank("dd");

        System.exit(0);
    }
}
