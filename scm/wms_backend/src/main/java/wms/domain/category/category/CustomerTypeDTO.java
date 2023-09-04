package wms.domain.category.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerTypeDTO {
    @NotBlank(message = "Truong name khong duoc bo trong")
    private String name;
}
