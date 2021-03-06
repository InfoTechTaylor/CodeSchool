package dao;

import dto.Product;

import java.util.List;

public interface FlooringDaoProducts {

    Product retrieveProductByMaterial(String material) throws FlooringPersistenceException;
    List<Product> retrieveAllProducts() throws FlooringPersistenceException;
    void createProduct(Product productObj) throws FlooringPersistenceException;
    void updateProduct(Product productObj) throws FlooringPersistenceException;
    void removeProduct(Product productObj) throws FlooringPersistenceException;
}
