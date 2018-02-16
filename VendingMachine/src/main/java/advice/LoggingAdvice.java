package advice;

import dao.VendingMachineAuditDao;
import dao.VendingMachinePersistenceException;
import org.aspectj.lang.JoinPoint;
import service.InsufficientFundsException;
import service.InvalidAmountException;
import service.NoItemInventoryException;

public class LoggingAdvice {

    VendingMachineAuditDao auditDao;

    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    // creates an audit entry for a successful method call
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";

        String itemInfo="";
        for (Object currentArg : args) {
            itemInfo += currentArg;
        }
        if(itemInfo.equals("")){
            auditEntry += "successful.";
        } else {
            auditEntry += " user input: " + itemInfo + " :successful.";
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e){
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    // creates an audit entry for when an exception is thrown when method is called
    public void createExceptionAuditEntry(JoinPoint jp, Exception e){
            Object[] args = jp.getArgs(); // args are the parameters of the JoinPoint (method called)

            String auditEntry;
            String itemInfo = "";
            for (Object currentArg : args) {
                itemInfo += currentArg;
            }

            if (itemInfo != "") {
                auditEntry = jp.getSignature().getName() + ": user input: " + itemInfo + " :Throws " + e.toString();
            } else {
                auditEntry = jp.getSignature().getName() +  " :Throws " + e.toString();
            }

            try {
//            auditDao.writeAuditEntry("There has been an exception for the above method call: " + e.toString());
                auditDao.writeAuditEntry(auditEntry);
            } catch (VendingMachinePersistenceException ex) {
                System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
            }

    }

}
