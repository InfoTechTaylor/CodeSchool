package flooringmasteryspringmvc.advice;


import flooringmasteryspringmvc.dao.FlooringDaoAudit;
import flooringmasteryspringmvc.dao.FlooringPersistenceException;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {

    FlooringDaoAudit auditDao;

    public LoggingAdvice(FlooringDaoAudit auditDao){
        this.auditDao = auditDao;
    }

    // create an audit entry for a successful method call
    public void createSuccessAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";

        String itemInfo="";
        for (Object currentArg : args) {
            itemInfo += currentArg;
        }

        if(itemInfo.equals("")){
            auditEntry += "SUCCESS";
        } else {
            auditEntry += " user input: " + itemInfo + " : SUCCESS";
        }

        try{
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e){
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createExceptionAuditEntry(JoinPoint jp, Exception e){
        Object[] args = jp.getArgs();

        String auditEntry;
        String itemInfo = "";
        for(Object currentArg : args) {
            itemInfo += currentArg;
        }

        if(itemInfo != "") {
            auditEntry = jp.getSignature().getName() + ": user input: " + itemInfo + " :Throws " + e.toString();
        } else {
            auditEntry = jp.getSignature().getName() + " :Throws " + e.toString();
        }

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException ex){
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
