package wms.domain.warehouse.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wms.common.enums.CommonStatus;
import wms.domain.auth.entity.UserRegister;
import wms.domain.warehouse.dao.FacilityRepo;
import wms.domain.warehouse.entity.Facility;
import wms.utils.GeneralUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class FacilityServiceImplTest {
    @Autowired
    private FacilityRepo facilityRepo;
    @Test
    void createFacilityFromFile() {
    }

    @Test
    void getFacilityById() {
        // Arrange
        Facility testingFac = facilityRepo.getFacilityById(1);
        // Act
        // Assert
        assertThat(testingFac).isNotNull();
    }

    @Test
    void createFacility() {
        // Arrange
        Facility newFacility = Facility.builder()
                .name("FC1")
                .code("FAC" + GeneralUtils.generateCodeFromSysTime())
                .address("50 Phan Dinh Giot")
                .status(CommonStatus.ACTIVE.getStatus().toUpperCase())
                .latitude("10.01")
                .longitude("10.02")
                .creator(new UserRegister())
                .manager(new UserRegister())
                .build();
        // Act
        Facility savedFacility = facilityRepo.save(newFacility);
        // Assert
        assertThat(savedFacility).isNotNull();
        assertThat(savedFacility.getName()).isEqualTo("FC1");
    }
}