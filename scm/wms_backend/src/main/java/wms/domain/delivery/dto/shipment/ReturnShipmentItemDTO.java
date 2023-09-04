package wms.domain.delivery.dto.shipment;

import lombok.Data;
import wms.domain.sellout.entity.DeliveryBill;
import wms.domain.delivery.entity.DeliveryTrip;
import wms.domain.delivery.entity.Shipment;

import java.time.ZonedDateTime;

@Data
public class ReturnShipmentItemDTO {
    private Long id;
    private String code;
    private Shipment shipment;
    private DeliveryBill deliveryBill;
    private DeliveryTrip deliveryTrip;
    private Integer quantity;
    private String tripSeqId;
    private String productName;
    private ZonedDateTime createdDate;
    private int isDeleted;
}
