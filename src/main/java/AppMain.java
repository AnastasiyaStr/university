import DAO.DepartmentDAO;
import DAO.LectorDAO;
import DAO.UniversityDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AppMain {
    private static final BufferedReader BUFFERED_READER;
    private static final BufferedReader BUFFERED_READER1;
    private static final Integer SIZE;

    static {
        BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
        BUFFERED_READER1 = new BufferedReader(new InputStreamReader(System.in));
        SIZE = 5;
    }

    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        LectorDAO lectorDAO = new LectorDAO();
        System.out.println(".......Hibernate Crud Operations Example.......\n");
        System.out.println("\n=======CREATE RECORDS WORKER=======\n");
        UniversityDAO.createRecord();
        DepartmentDAO.createRecord();
        LectorDAO.createRecord();
        System.out.println("COUNT " + DepartmentDAO.showCountOfEmployee("Hey1"));
        System.out.println("AVG " + DepartmentDAO.showAverageSalaryForDepartment("Hey1"));
        System.out.println("Lectors: " + LectorDAO.globalSearchByTemplate("Joey1"));
        DepartmentDAO.getLectorsByRank("dd");
//////////////////////////////////////

        boolean isRunning = true;

        System.out.println(departmentDAO);
        System.out.println(lectorDAO);
        int chose =0;
        while (isRunning) {
            System.out.println("Enter number of task: \n");
//            for (int i = 0; i < TASKS.size(); i++) {
//                System.out.println(i + " " + TASKS.get(i));
//            }
            System.out.println(0 + " Exit");
            try {
                chose = Integer.parseInt(BUFFERED_READER.readLine());
            } catch (Exception e) {
                System.out.println("You can use numbers only");
                continue;
            }
            if (chose == 0) {
                isRunning = false;
            } else if ((chose < SIZE) && (chose >= 0)) {
                switch (chose) {
                    case 1:
                        break;
                    case 2:
                        try {
                            DepartmentDAO.getLectorsByRank( BUFFERED_READER1.readLine());
                        } catch ( IOException e) {
                            System.out.println("Error in entering!!!");
                        }
                        break;
                    case 3:
                        try {
                        DepartmentDAO.showAverageSalaryForDepartment(BUFFERED_READER1.readLine());
                        } catch ( IOException e) {
                            System.out.println("Error in entering!!!");
                        }
                        break;
                    case 4:
                        try {
                       DepartmentDAO.showCountOfEmployee(BUFFERED_READER1.readLine());
                        } catch ( IOException e) {
                            System.out.println("Error in entering!!!");
                        }
                        break;
                    default:
                        // The user input an unexpected choice.
                }


            } else {
                System.out.println("Try to write one more time");
            }

            System.out.println("\n------------------------------------------------------");

        }
        System.exit(0);
    }
}
