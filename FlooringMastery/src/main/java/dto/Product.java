package dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String productType;
    private BigDecimal materialCostPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    public Product(String productType, BigDecimal materialCostPerSquareFoot, BigDecimal laborCostPerSquareFoot) {
        this.productType = productType;
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public Product(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getMaterialCostPerSquareFoot() {
        return materialCostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setMaterialCostPerSquareFoot(BigDecimal materialCostPerSquareFoot) {
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productType, product.productType) &&
                Objects.equals(materialCostPerSquareFoot, product.materialCostPerSquareFoot) &&
                Objects.equals(laborCostPerSquareFoot, product.laborCostPerSquareFoot);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productType, materialCostPerSquareFoot, laborCostPerSquareFoot);
    }
}
