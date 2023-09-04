package wms.domain.delivery.dto.vehicle;

import lombok.Data;

@Data
public class TruckResponseDTO {
    private Long id;
    private String code;

    private String name;

    private String size;

    private double capacity;

    private double speed;

    private double transportCostPerUnit;

    private double waitingCost;

    private String userName;
}
