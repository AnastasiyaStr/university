
import entity.Worker;

import java.util.List;

//import org.apache.log4j.Logger;

public class AppMain {

    //  public final static Logger logger = Logger.getLogger(AppMain.class);

    public static void main(String[] args) {
        System.out.println(".......Hibernate Crud Operations Example.......\n");

        System.out.println("\n=======CREATE RECORDS=======\n");
        DbOperations.createRecord();

        System.out.println("\n=======READ RECORDS=======\n");
        List<Worker> viewWorkers = DbOperations.displayRecords();
        if(viewWorkers != null & viewWorkers.size() > 0) {
            for(Worker workerObj : viewWorkers) {
                //  logger.info(studentObj.toString());
                System.out.println(workerObj);
            }
        }

        System.out.println("\n=======UPDATE RECORDS=======\n");
        int updateId = 1;
        DbOperations.updateRecord(updateId);
        System.out.println("\n=======READ RECORDS AFTER UPDATION=======\n");
        List<Worker> updateWorker = DbOperations.displayRecords();
        if(updateWorker != null & updateWorker.size() > 0) {
            for(Worker workerObj : updateWorker) {
                System.out.println(workerObj.toString());
            }
        }

        System.out.println("\n=======DELETE RECORD=======\n");
        int deleteId = 1;
        DbOperations.deleteRecord(deleteId);
      /*  System.out.println("\n=======READ RECORDS AFTER DELETION=======\n");
        List<Worker> deleteWorkerRecord = DbOperations.displayRecords();
        for(Worker workerObj : deleteWorkerRecord) {
            System.out.println( workerObj.toString());
        }

        System.out.println("\n=======DELETE ALL RECORDS=======\n");
        DbOperations.deleteAllRecords();
        System.out.println("\n=======READ RECORDS AFTER ALL RECORDS DELETION=======");
        List deleteAll = DbOperations.displayRecords();
        if(deleteAll.size() == 0) {
            System.out.println("\nNo Records Are Present In The Database Table!\n");
        }*/
        System.exit(0);
    }
}
