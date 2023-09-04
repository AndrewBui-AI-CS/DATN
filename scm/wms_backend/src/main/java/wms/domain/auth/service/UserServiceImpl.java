package wms.domain.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.StringHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wms.common.ReturnPaginationDTO;
import wms.common.response.IErrorResult;
import wms.domain.auth.service.IUserService;
import wms.domain.category.product.ProductDTO;
import wms.domain.auth.entity.UserRegister;
import wms.exception.CustomException;
import wms.domain.auth.dao.UserRepo;
import wms.common.BaseService;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends BaseService implements IUserService, IErrorResult {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ReturnPaginationDTO<UserRegister> getAllUsersPaging(int page, int pageSize, String sortField, boolean isSortAsc,
                                                               String role, String textSearch) {
        Pageable pageable = StringHelper.isEmpty(sortField) ? PageRequest.of(page - 1, pageSize)
                : isSortAsc ? PageRequest.of(page - 1, pageSize, Sort.by(sortField).ascending())
                : PageRequest.of(page - 1, pageSize, Sort.by(sortField).descending());
        Page<UserRegister> users = userRepo.search(pageable, role, textSearch);
        return getPaginationResult(users.getContent(), page, users.getTotalPages(), users.getTotalElements());
    }

    @Override
    public List<UserRegister> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<UserRegister> getAllUsersByRole(String roleName) {
        return userRepo.getUsersByRole(roleName);
    }
}
