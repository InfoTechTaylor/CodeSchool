package flooringmasteryspringmvc.dao;

import java.io.*;
import java.util.Scanner;

public class ConfigDaoImpl implements ConfigDao {

    String filename;
    String currentHighestOrderNumber;

    public ConfigDaoImpl(String filename){
        this.filename = filename;
    }

    @Override
    public String generateOrderNumber() throws FlooringPersistenceException {
        loadOrderNumber();
        writeOrderNumber();
        return currentHighestOrderNumber;
    }

    private void loadOrderNumber() throws FlooringPersistenceException {

        Scanner scanner;

        try{
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e){
            throw new FlooringPersistenceException("Unable to load config file.");
        }

        currentHighestOrderNumber = scanner.nextLine();
        int orderNumberOnFileInt = Integer.parseInt(currentHighestOrderNumber);
        orderNumberOnFileInt++;
        currentHighestOrderNumber = Integer.toString(orderNumberOnFileInt);

        scanner.close();

    }

    private void writeOrderNumber() throws FlooringPersistenceException {
        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(filename));
        } catch (IOException e){
            throw new FlooringPersistenceException("Unable to write to config file.");
        }

        out.print(currentHighestOrderNumber);
        out.flush();
        out.close();
    }
}
