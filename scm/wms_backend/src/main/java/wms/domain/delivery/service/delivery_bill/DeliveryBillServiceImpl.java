package wms.domain.delivery.service.delivery_bill;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.StringHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wms.common.enums.ResponseCode;
import wms.domain.delivery.dao.ShipmentItemRepo;
import wms.domain.delivery.entity.ShipmentItem;
import wms.domain.sellout.dao.DeliveryBillItemRepo;
import wms.domain.sellout.dao.DeliveryBillRepo;
import wms.domain.sellout.entity.DeliveryBill;
import wms.domain.sellout.entity.DeliveryBillItem;
import wms.domain.warehouse.dao.ExportInventoryItemRepo;
import wms.domain.warehouse.dao.InventoryItemRepo;
import wms.domain.warehouse.entity.ExportInventoryItem;
import wms.domain.warehouse.entity.InventoryItem;
import wms.common.ReturnPaginationDTO;
import wms.domain.warehouse.dto.SplitBillDTO;
import wms.domain.warehouse.dto.SplitBillItemDTO;
import wms.exception.CustomException;
import wms.common.BaseService;
import wms.utils.GeneralUtils;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeliveryBillServiceImpl extends BaseService implements IDeliveryBillService {
    private final InventoryItemRepo inventoryItemRepo;
    private final ShipmentItemRepo shipmentItemRepo;
    private final ExportInventoryItemRepo exportInventoryItemRepo;
    private final DeliveryBillItemRepo deliveryBillItemRepo;
    private final DeliveryBillRepo deliveryBillRepo;

    public DeliveryBillServiceImpl(DeliveryBillRepo deliveryBillRepo,
                                   DeliveryBillItemRepo deliveryBillItemRepo,
                                   ExportInventoryItemRepo exportInventoryItemRepo,
                                   ShipmentItemRepo shipmentItemRepo,
                                   InventoryItemRepo inventoryItemRepo) {
        this.deliveryBillRepo = deliveryBillRepo;
        this.deliveryBillItemRepo = deliveryBillItemRepo;
        this.exportInventoryItemRepo = exportInventoryItemRepo;
        this.shipmentItemRepo = shipmentItemRepo;
        this.inventoryItemRepo = inventoryItemRepo;
    }

    @Override
    public ReturnPaginationDTO<DeliveryBill> getAllBills(int page, int pageSize, String sortField, boolean isSortAsc, String orderCode) throws JsonProcessingException {
        Pageable pageable = StringHelper.isEmpty(sortField) ? getDefaultPage(page, pageSize)
                : isSortAsc ? PageRequest.of(page - 1, pageSize, Sort.by(sortField).ascending())
                : PageRequest.of(page - 1, pageSize, Sort.by(sortField).descending());
        Page<DeliveryBill> deliveryBills = deliveryBillRepo.search(pageable, orderCode);
        return getPaginationResult(deliveryBills.getContent(), page, deliveryBills.getTotalPages(), deliveryBills.getTotalElements());
    }

    @Override
    public List<DeliveryBillItem> getBillItemsOfOrder(String orderCode) throws JsonProcessingException {
        return deliveryBillItemRepo.search(orderCode);
    }

    @Override
    public List<DeliveryBillItem> getBillItemsOfBill(String billCode) {
        return deliveryBillItemRepo.getAllItemOfABill(billCode);
    }

    @Override
    public List<DeliveryBillItem> getBillItemsOfBillBySeq(String billCode, String seqId) {
        return deliveryBillItemRepo.getItemOfABillBySeq(billCode, seqId);
    }

    @Override
    public ReturnPaginationDTO<DeliveryBill> getBillsCanDeliver(int page, int pageSize, String sortField, boolean isSortAsc) throws JsonProcessingException {
        Pageable pageable = StringHelper.isEmpty(sortField) ? getDefaultPage(page, pageSize)
                : isSortAsc ? PageRequest.of(page - 1, pageSize, Sort.by(sortField).ascending())
                : PageRequest.of(page - 1, pageSize, Sort.by(sortField).descending());
        Page<DeliveryBill> deliveryBills = deliveryBillRepo.search(pageable);
        return getPaginationResult(deliveryBills.getContent(), page, deliveryBills.getTotalPages(), deliveryBills.getTotalElements());
    }

    @Override
    public DeliveryBill getBillById(long id) {
        return null;
    }

    @Override
    public DeliveryBill getBillByCode(String code) {
        return null;
    }

    @Override
    public DeliveryBillItem getBillItemsOfOrder(String billCode, String billItemSeq) {
        return null;
    }

    @Override
    public void deleteBillItem(long id) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void splitBills(SplitBillDTO splitBillDTO) throws CustomException {
        DeliveryBill deliveryBill =  deliveryBillRepo.getBillWithCode(splitBillDTO.getDeliveryBillCode());
        if (deliveryBill == null) {
            throw caughtException(ResponseCode.NON_EXIST.getCode(), "Can't find referenced bill, can't split");
        }
        int index = 0;
        for (SplitBillItemDTO splitBillItemDTO : splitBillDTO.getBillItemDTOS()) {
            index++;
            List<DeliveryBillItem> currBillItems = deliveryBill.getDeliveryBillItems().stream().filter(item -> item.getSeqId().equals(splitBillItemDTO.getDeliveryBillItemSeqId())).collect(Collectors.toList());
            // There is only one bill item can have same seqId and delivery_bill_code with this DTO.
            if (currBillItems.size() != 1) {
                throw caughtException(ResponseCode.UNMATCH.getCode(), "Can't find matched delivery bill item, can't split");
            }
            List<ShipmentItem> sameBillShipmentItem = shipmentItemRepo.getItemOfSameBillAndProduct(deliveryBill.getCode(), splitBillItemDTO.getDeliveryBillItemSeqId());
            // Check if total quantity of all items that are in the same bill and of the same product.
            int totalSameBillItemQty = sameBillShipmentItem.stream().mapToInt(ShipmentItem::getQuantity).reduce(0, Integer::sum);
            if (totalSameBillItemQty + splitBillItemDTO.getQuantity() > currBillItems.get(0).getEffectiveQty()) {
                throw caughtException(ResponseCode.USER_ACTION_FAILED.getCode(), "Exceed quantity limit, can't split anymore");
            }
            ShipmentItem shipmentItem = ShipmentItem.builder()
                    .code("SHIT" + GeneralUtils.generateCodeFromSysTime() + "_" + index)
                    .deliveryBill(deliveryBill)
                    .deliveryBillItemSeqId(splitBillItemDTO.getDeliveryBillItemSeqId())
                    .quantity(splitBillItemDTO.getQuantity())
                    .build();
            shipmentItemRepo.save(shipmentItem);

            List<InventoryItem> inventoryItems = inventoryItemRepo.getAllItemsOfSameProductOrderByDate(splitBillItemDTO.getProductCode());
            int cumulativeInventoryQty = 0;
            int selectiveInventoryIndex = 0;
            // Phải liệt kê hết các inventory_item của cùng 1 sản phẩm.
            // Nếu lô này mà số lượng của nó không đủ để export => Cần phải tìm lô khác đủ lượng sản phẩm (tối thiểu là >=)
            // Tìm lô theo thứ tự ngày hết hạn của lô. Ưu tiên ngày hết hạn sớm trước.
            // Chọn ra được cái lô cuối cùng mà tổng qty của các lô trước cộng vào bằng vs số lượng đã nhập trên các shipment và lượng đang chuẩn bị được nhập
            // SelectiveInventoryIndex sẽ dừng ko tăng nữa khi cumulativeQty vượt quá so vs lượng cần => Oke.
            for (InventoryItem curr : inventoryItems) {
                cumulativeInventoryQty += curr.getQuantity();
                if (cumulativeInventoryQty < totalSameBillItemQty + splitBillItemDTO.getQuantity()) {
                    selectiveInventoryIndex++;
                }
            }
            ExportInventoryItem newExpInventoryItem = ExportInventoryItem.builder()
                    .code("EXINV" + GeneralUtils.generateCodeFromSysTime() + "_" + index)
                    .effective_date(ZonedDateTime.now().toString())
                    .deliveryBill(deliveryBill)
                    .deliveryBillItemSeqId(splitBillItemDTO.getDeliveryBillItemSeqId())
                    .quantity(splitBillItemDTO.getQuantity())
                    .inventoryItem(inventoryItems.get(selectiveInventoryIndex))
                    .build();
            exportInventoryItemRepo.save(newExpInventoryItem);
        }
    }

    @Override
    public List<ShipmentItem> getSplitBillByCode(String deliveryBillCode) {
        return shipmentItemRepo.getShipmentItemOfABill(deliveryBillCode);
    }
}
