package wms.domain.delivery.dto.shipment;

import wms.domain.sellout.entity.DeliveryBill;
import wms.domain.delivery.entity.DeliveryTrip;
import wms.domain.delivery.entity.Shipment;


public interface ShipmentProjection {
    String getCode();
    Integer getQuantity();
    String getDeliveryBillItemSeqId();

    String getTripSeqId();
    Shipment getShipment();

    DeliveryBill getDeliveryBill();

    DeliveryTrip getDeliveryTrip();


}
