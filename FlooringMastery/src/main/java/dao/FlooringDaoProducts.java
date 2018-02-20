package dao;

import dto.Product;

import java.util.List;

public interface FlooringDaoProducts {

    Product retrieveProductByMaterial(String material);
    List<Product> retrieveAllProducts();
    void createProduct(Product productObj);
    void updateProduct(Product productObj);
    void removeProduct(Product productObj);
}
