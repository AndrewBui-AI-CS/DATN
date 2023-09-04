package wms.domain.category.product;

import wms.common.BaseReturnDTO;
import wms.domain.category.entity.ProductCategory;
import wms.domain.category.entity.ProductUnit;

public class ProductReturnDTO extends BaseReturnDTO {
    private String code;
    private String name;
    private Integer unitPerBox;
    private ProductUnit productUnit;
    private String brand;
    private ProductCategory productCategory;
    private String status;
    private int massQuantity;
    private String sku;
    double vat;
    double priceBeforeVat;
}
