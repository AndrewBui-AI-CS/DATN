package wms.domain.warehouse.dto;

import lombok.Data;
import wms.domain.warehouse.dto.ImportItemDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ImportToFacilityDTO {
    @NotBlank(message = "Truong order code khong duoc bo trong")
    private String orderCode;
    @Valid
    List<ImportItemDTO> importItems;
}
