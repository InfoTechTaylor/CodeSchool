package dao;

import dto.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FlooringDaoTaxesFileImpl implements FlooringDaoTaxes {

    private Map<String, Tax> taxRateByStateMap = new HashMap<>();
    private String filename;
    private static final String STRING_DELIMITER = ",";

    public FlooringDaoTaxesFileImpl(String filename){
        this.filename = filename;
    }

    @Override
    public Tax retrieveTaxByState(String state) throws FlooringPersistenceException{
        loadTaxes();
        return taxRateByStateMap.get(state);
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException{
        loadTaxes();
        return new ArrayList<>(taxRateByStateMap.values());
    }

    @Override
    public void createTax(Tax taxObject) throws FlooringPersistenceException{
        taxRateByStateMap.put(taxObject.getState(), taxObject);
        writeTaxes();
    }

    @Override
    public void updateTax(Tax taxObject) throws FlooringPersistenceException{
        taxRateByStateMap.replace(taxObject.getState(), taxObject);
        writeTaxes();
    }

    @Override
    public void removeTax(Tax taxObject) throws FlooringPersistenceException{
        taxRateByStateMap.remove(taxObject.getState());
        writeTaxes();
    }

    // load taxes into map
    private void loadTaxes() throws FlooringPersistenceException {
        // create an instance of Scanner
        Scanner scanner;

        try{
            // pass scanner a BufferedReader and that a FileReader that will read our filename declared above
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch(FileNotFoundException e){
            // if file is not found, throw an application exception instead of FileNotFound
            throw new FlooringPersistenceException("-_- Could not load tax data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while(scanner.hasNextLine()){
            // get current line in the file
            currentLine = scanner.nextLine();
            // get each section of a line, separated by a comma and put in array currentTokens
            currentTokens = currentLine.split(STRING_DELIMITER);
            // create a new Tax object based on the line items
            Tax currentTaxObject = new Tax(currentTokens[0], new BigDecimal(currentTokens[1]));

            // add the new object to the map (memory)
            taxRateByStateMap.put(currentTaxObject.getState(), currentTaxObject);
        }

        // cleanup, close scanner
        scanner.close();

    }

    private void writeTaxes() throws FlooringPersistenceException {
        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(filename));
        } catch (IOException e){
            throw new FlooringPersistenceException("Unable to write taxes to file");
        }

        List<Tax> allTaxObjects = this.retrieveAllTaxes();
        for(Tax currentTax : allTaxObjects){
            out.println(currentTax.getState() + STRING_DELIMITER
                        +currentTax.getTaxRate());

            out.flush();
        }
        out.close();
    }
}
