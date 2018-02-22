package dao;

import dto.Product;

import java.math.BigDecimal;
import java.util.List;

public class FlooringDaoProductsStubImpl implements FlooringDaoProducts {

    Product onlyProduct;
    List<Product> allProducts;

    public FlooringDaoProductsStubImpl(){
        onlyProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        allProducts.add(onlyProduct);
    }

    @Override
    public Product retrieveProductByMaterial(String material) throws FlooringPersistenceException {
        if(material.equals(onlyProduct.getProductType())){
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {
        return allProducts;
    }

    @Override
    public void createProduct(Product productObj) throws FlooringPersistenceException {

    }

    @Override
    public void updateProduct(Product productObj) throws FlooringPersistenceException {

    }

    @Override
    public void removeProduct(Product productObj) throws FlooringPersistenceException {

    }
}
