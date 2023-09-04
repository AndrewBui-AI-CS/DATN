package wms.domain.category.customer;

import lombok.Data;

@Data
public class CustomerUpdateDTO {
    private String name;
    private String phone;
    private String address;
    private String status;
    private String facilityCode;
    private String latitude;
    private String longitude;
    private String customerTypeCode;
    private String contractTypeCode;
}
