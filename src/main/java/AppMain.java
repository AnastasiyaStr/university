import DAO.DepartmentDAO;
import DAO.LectorDAO;
import DAO.UniversityDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AppMain {
    private static final BufferedReader BUFFERED_READER;
    private static final Integer SIZE;

    static {
        BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
        SIZE = 5;
    }

    public static void main(String[] args) {
        UniversityDAO.createRecord();
        DepartmentDAO.createRecord();
        LectorDAO.createRecord();
        boolean isRunning = true;
        int chose;
        while (isRunning) {
            System.out.println(DepartmentDAO.options());
            System.out.println(LectorDAO.options());
            System.out.println(0 + " Exit");
            System.out.println("Enter number of task: \n");
            try {
                chose = Integer.parseInt(BUFFERED_READER.readLine());
            } catch (Exception e) {
                System.out.println("You can use numbers only");
                continue;
            }
            if (chose == 0) {
                isRunning = false;
            } else if ((chose <= SIZE) && (chose >= 0)) {
                System.out.println("{ENTER value}\n");
                String value;
                try {
                    value = BUFFERED_READER.readLine();
                } catch (IOException e) {
                    System.out.println("Error in entering!!!");
                    continue;
                }
                switch (chose) {
                    case 1:
                        DepartmentDAO.showHeadOfDepartment(value);
                        break;
                    case 2:
                        DepartmentDAO.getLectorsByRank(value);
                        break;
                    case 3:
                        DepartmentDAO.showAverageSalaryForDepartment(value);
                        break;
                    case 4:
                        DepartmentDAO.showCountOfEmployee(value);
                        break;
                    case 5:
                        LectorDAO.globalSearchByTemplate(value);
                }
            } else {
                System.out.println("Try to write one more time");
            }
            System.out.println("\n------------------------------------------------------");
        }
        System.exit(0);
    }
}
