package wms.domain.category.customer;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerDTO {
    @NotBlank(message = "Name khong duoc de trong")
    private String name;
    @NotBlank(message = "So dien thoai khong duoc de trong")
    private String phone;
    @NotBlank(message = "Dia chi khong duoc de trong")
    private String address;
    private String province;
    private String status;
//    @NotBlank(message = "Ma kho hang khong duoc de trong")
//    private String facilityCode;
    private String latitude;
    private String longitude;
    private String createdBy;
    private String customerTypeCode;
    private String contractTypeCode;
}
