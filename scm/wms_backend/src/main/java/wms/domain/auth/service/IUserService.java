package wms.domain.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import wms.common.ReturnPaginationDTO;
import wms.domain.category.product.ProductDTO;
import wms.domain.auth.entity.UserRegister;
import wms.exception.CustomException;

import java.util.List;

public interface IUserService {
    ReturnPaginationDTO<UserRegister> getAllUsersPaging(int page, int pageSize, String sortField, boolean isSortAsc, String role, String textSearch) throws JsonProcessingException;
    List<UserRegister> getAllUsers();
    List<UserRegister> getAllUsersByRole(String roleName);

}
