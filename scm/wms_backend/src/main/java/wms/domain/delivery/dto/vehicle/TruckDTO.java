package wms.domain.delivery.dto.vehicle;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class TruckDTO {
    @Positive(message = "Trong tai phai la so duong")
    double capacity;
    @Positive(message = "Toc do phai lon hon khong")
    double speed;
    @Positive(message = "Chi phi phai duong")
    double transportCostPerUnit;
    @Positive(message = "Phi cho doi phai duong")
    double waitingCost;
    String userManaged;
    String name;
    String size;
}
