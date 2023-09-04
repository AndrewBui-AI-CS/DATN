package wms.domain.delivery.dto.vehicle;

import lombok.Data;

@Data
public class DroneResponseDTO {
    private Long id;
    private String code;

    private String name;

    private double capacity;

    private double speed;

    private double transportCostPerUnit;

    private double waitingCost;

    private double durationTime;

    private String userName;
}
