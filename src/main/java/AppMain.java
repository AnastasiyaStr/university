
import DAO.DepartmentDAO;
import DAO.WorkerDAO;
import entity.Department;
import entity.Worker;

import java.util.List;
public class AppMain {



    public static void main(String[] args) {
        System.out.println(".......Hibernate Crud Operations Example.......\n");

        System.out.println("\n=======CREATE RECORDS WORKER=======\n");
        WorkerDAO.createRecord();
        System.out.println("\n=======CREATE RECORDS DEPARTMENT=======\n");
        DepartmentDAO.createRecord();
        System.out.println("\n=======READ RECORDS WORKER=======\n");
        List<Worker> viewWorkers = WorkerDAO.displayRecords();
        if(viewWorkers != null & viewWorkers.size() > 0) {
            for(Worker workerObj : viewWorkers) {

                System.out.println(workerObj);
            }
        }
        System.out.println("\n=======READ RECORDS DEPARTMENT=======\n");
        List<Department> viewDepartments = DepartmentDAO.displayRecords();
        if(viewDepartments != null & viewDepartments.size() > 0) {
            for(Department departmentObj : viewDepartments) {
                System.out.println(departmentObj);
            }
        }
        System.out.println("\n=======UPDATE RECORDS WORKER=======\n");
        int updateWorkerId = 1;
        WorkerDAO.updateRecord(updateWorkerId);
        System.out.println("\n=======READ RECORDS AFTER UPDATION WORKER=======\n");
        List<Worker> updateWorker = WorkerDAO.displayRecords();
        if(updateWorker != null & updateWorker.size() > 0) {
            for(Worker workerObj : updateWorker) {
                System.out.println(workerObj.toString());
            }
        }


        System.out.println("\n=======UPDATE RECORDS DEPARTMENT=======\n");
        int updateDepartmentId = 1;
        DepartmentDAO.updateRecord(updateDepartmentId);
        System.out.println("\n=======READ RECORDS AFTER UPDATION DEPARTMENT=======\n");
        List<Department> updateDepartment = DepartmentDAO.displayRecords();
        if(updateDepartment != null & updateDepartment.size() > 0) {
            for(Department departmentObj :updateDepartment) {
                System.out.println(departmentObj.toString());
            }
        }

        System.out.println("\n=======DELETE WORKER RECORD=======\n");
        int deleteWorkerId = 1;
        WorkerDAO.deleteRecord(deleteWorkerId);

        System.out.println("\n=======DELETE DEPARTMENT RECORD=======\n");
        int deleteDepartmentId = 1;
        DepartmentDAO.deleteRecord(deleteDepartmentId);

      System.out.println("\n=======READ WORKER RECORDS AFTER DELETION=======\n");
        List<Worker> deleteWorkerRecord = WorkerDAO.displayRecords();
        for(Worker workerObj : deleteWorkerRecord) {
            System.out.println( workerObj.toString());
        }

        System.out.println("\n=======READ DEPARTMENT RECORDS AFTER DELETION=======\n");
        List<Department> deleteDepartmentRecord = DepartmentDAO.displayRecords();
        for(Department departmentObj : deleteDepartmentRecord) {
            System.out.println( departmentObj.toString());
        }

        System.out.println("\n=======DELETE ALL WORKER RECORDS=======\n");
        WorkerDAO.deleteAllRecords();
        System.out.println("\n=======READ WORKER RECORDS AFTER ALL RECORDS DELETION=======");
        List deleteAll = WorkerDAO.displayRecords();
        if(deleteAll.size() == 0) {
            System.out.println("\nNo Worker Records Are Present In The Database Table!\n");
        }
        System.out.println("\n=======DELETE ALL DEPARTMENT RECORDS=======\n");
        DepartmentDAO.deleteAllRecords();
        System.out.println("\n=======READ DEPARTMENT RECORDS AFTER ALL RECORDS DELETION=======");
        deleteAll = WorkerDAO.displayRecords();
        if(deleteAll.size() == 0) {
            System.out.println("\nNo Department Records Are Present In The Database Table!\n");
        }
        System.exit(0);
    }
}
