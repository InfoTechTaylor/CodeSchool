package dao;

import dto.Product;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FlooringDaoProductsFileImpl implements FlooringDaoProducts {

    private Map<String, Product> productMap = new HashMap<>();
    private String filename;
    private final String STRING_DELIMITER = ",";

    public FlooringDaoProductsFileImpl(String filename){
        this.filename = filename;
    }

    @Override
    public Product retrieveProductByMaterial(String material) throws FlooringPersistenceException{
        loadProducts();
        return productMap.get(material);
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {
        loadProducts();
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void createProduct(Product productObj) throws FlooringPersistenceException {
        productMap.put(productObj.getProductType(), productObj);
        writeProducts();
    }

    @Override
    public void updateProduct(Product productObj) throws FlooringPersistenceException {
        productMap.replace(productObj.getProductType(), productObj);
        writeProducts();
    }

    @Override
    public void removeProduct(Product productObj) throws FlooringPersistenceException {
        productMap.remove(productObj.getProductType());
        writeProducts();
    }

    // load Products into map
    private void loadProducts() throws FlooringPersistenceException{
        Scanner scanner;

        try{
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch(FileNotFoundException e){
            throw new FlooringPersistenceException("Unable to load products from file.");
        }

        String currentLine;
        String[] currentTokens;

        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(STRING_DELIMITER);

            Product currentProduct = new Product(currentTokens[0], new BigDecimal(currentTokens[1]),
                                        new BigDecimal(currentTokens[2]));

            productMap.put(currentProduct.getProductType(), currentProduct);

        }
        scanner.close();
    }

    private void writeProducts() throws FlooringPersistenceException{
        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(filename));
        } catch(IOException e){
            throw new FlooringPersistenceException("could not write products to file");
        }

        List<Product> allProducts = this.retrieveAllProducts();
        for(Product currentProduct : allProducts){
            out.println(currentProduct.getProductType() + STRING_DELIMITER +
                        currentProduct.getMaterialCostPerSquareFoot() + STRING_DELIMITER +
                        currentProduct.getLaborCostPerSquareFoot());

            out.flush();
        }
        out.close();
    }
}
