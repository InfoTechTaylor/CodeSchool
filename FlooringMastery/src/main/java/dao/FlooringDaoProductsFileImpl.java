package dao;

import dto.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlooringDaoProductsFileImpl implements FlooringDaoProducts {

    private Map<String, Product> productMap = new HashMap<>();
    private final String FILENAME = "Products.txt";

    @Override
    public Product retrieveProductByMaterial(String material) {
        return null;
    }

    @Override
    public List<Product> retrieveAllProducts() {
        return null;
    }

    @Override
    public void createProduct(Product productObj) {

    }

    @Override
    public void updateProduct(Product productObj) {

    }

    @Override
    public void removeProduct(Product productObj) {

    }

    // load Products into map
    private void loadProducts(){

    }
}
