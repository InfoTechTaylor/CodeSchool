package advice;

import dao.FlooringDaoAudit;
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

//        try{
//            auditDao.writeAuditEn
//        }
    }
}
