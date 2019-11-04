import DAO.DepartmentDAO;
import DAO.LectorDAO;
import DAO.UniversityDAO;

import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        System.out.println(".......Hibernate Crud Operations Example.......\n");
        System.out.println("\n=======CREATE RECORDS WORKER=======\n");
        UniversityDAO.createRecord();
        DepartmentDAO.createRecord();
        LectorDAO.createRecord();
        System.out.println("COUNT " + DepartmentDAO.showCountOfEmployee("Hey1"));
        System.out.println("AVG " + DepartmentDAO.showAverageSalaryForDepartment("Hey1"));
        System.out.println("Lectors: " + LectorDAO.globalSearchByTemplate("Joey1"));
        DepartmentDAO.getLectorsByRank("dd");
////////////////////////////////////////

        boolean isRunning = true;
        int chose = TASKS.size();

        while (isRunning) {
            System.out.println("Enter number of task: \n");
            for (int i = 0; i < TASKS.size(); i++) {
                System.out.println(i + " " + TASKS.get(i));
            }
            System.out.println(TASKS.size() + " Exit");
            try {
                chose = Integer.parseInt(BUFFERED_READER.readLine());
            } catch (Exception e) {
                System.out.println("You can use numbers only");
                continue;
            }

            if (chose == TASKS.size()) {
                isRunning = false;
            } else if ((chose < TASKS.size()) && (chose >= 0)) {
                TASKS.get(chose).start(BUFFERED_READER);
            } else {
                System.out.println("Try to write one more time");
            }

            System.out.println("\n------------------------------------------------------");
        }
    }
}
