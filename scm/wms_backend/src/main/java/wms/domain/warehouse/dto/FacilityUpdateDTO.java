package wms.domain.warehouse.dto;

import lombok.Data;

@Data
public class FacilityUpdateDTO {
//    private Long id;
    private String name;
    private String address;
    private String status;
    private String latitude;
    private String longitude;
    private String managedBy;
}
