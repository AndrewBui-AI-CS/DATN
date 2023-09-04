package wms.domain.sellin.dto.purchase_order;

import lombok.Data;

@Data
public class PurchaseOrderItemDTO {
    private String productCode;
    private Integer quantity;
    private Double priceUnit;
}
