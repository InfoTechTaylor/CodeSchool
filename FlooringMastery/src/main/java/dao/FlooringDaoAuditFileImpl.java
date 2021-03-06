package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class FlooringDaoAuditFileImpl implements FlooringDaoAudit {

    public static final String AUDIT_FINAL = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws FlooringPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FINAL, true));
        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not persist audit information", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
