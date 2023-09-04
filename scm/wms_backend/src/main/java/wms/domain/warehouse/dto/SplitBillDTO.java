package wms.domain.warehouse.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class SplitBillDTO {
    @NotBlank(message = "Delivery bill code khong duoc de trong")
    private String deliveryBillCode;
    @Valid
    private List<SplitBillItemDTO> billItemDTOS;
}
