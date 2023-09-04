package wms.domain.auth.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wms.domain.auth.dao.EntityAuthorizationRepo;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class EntityAuthorizationServiceImplTest {

    @Mock
    private EntityAuthorizationRepo entityAuthorizationRepo;
    @InjectMocks
    private EntityAuthorizationServiceImpl entityAuthorizationService;
    private
    @Test
    void getEntityAuthorization() {
        
    }
}