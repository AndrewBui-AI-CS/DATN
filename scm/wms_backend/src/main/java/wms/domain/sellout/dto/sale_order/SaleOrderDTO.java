package wms.domain.sellout.dto.sale_order;

import lombok.Data;

import java.util.List;

@Data
public class SaleOrderDTO {
    private String boughtBy; // customer
    private Double discount;
    private List<SaleOrderItemDTO> orderItems;
}
