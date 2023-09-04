package wms.domain.delivery.service.delivery_bill;

import com.fasterxml.jackson.core.JsonProcessingException;
import wms.common.ReturnPaginationDTO;
import wms.domain.warehouse.dto.SplitBillDTO;
import wms.domain.sellout.entity.DeliveryBill;
import wms.domain.sellout.entity.DeliveryBillItem;
import wms.domain.delivery.entity.ShipmentItem;
import wms.exception.CustomException;

import java.util.List;

public interface IDeliveryBillService {
    ReturnPaginationDTO<DeliveryBill> getAllBills(int page, int pageSize, String sortField, boolean isSortAsc, String orderCode) throws JsonProcessingException;
    List<DeliveryBillItem> getBillItemsOfOrder(String orderCode) throws JsonProcessingException;
    List<DeliveryBillItem> getBillItemsOfBill(String billCode) throws JsonProcessingException;
    List<DeliveryBillItem> getBillItemsOfBillBySeq(String billCode, String seqId) throws JsonProcessingException;
    ReturnPaginationDTO<DeliveryBill> getBillsCanDeliver(int page, int pageSize, String sortField, boolean isSortAsc) throws JsonProcessingException;
    DeliveryBill getBillById(long id);
    DeliveryBill getBillByCode(String code);
    DeliveryBillItem getBillItemsOfOrder(String billCode, String billItemSeq);
    //    void updateBillItem(UpdatePurchaseOrderDTO updatePurchaseOrderDTO, long id) throws CustomException;
    void deleteBillItem(long id);
    void splitBills(SplitBillDTO splitBillDTO) throws CustomException;
    List<ShipmentItem> getSplitBillByCode(String deliveryBillCode);
}
