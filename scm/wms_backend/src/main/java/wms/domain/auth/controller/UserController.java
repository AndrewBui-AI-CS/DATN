package wms.domain.auth.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wms.common.BaseController;
import wms.common.constant.DefaultConst;
import wms.common.ResultEntity;
import wms.common.enums.ResponseCode;
import wms.domain.auth.service.IUserService;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController extends BaseController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get all users with pagination, applying filters and sorting")
    @GetMapping("/get-users-paging")
    public ResponseEntity<?> getAllUsersWithPagination(
            @RequestParam(value = DefaultConst.PAGE, required = false, defaultValue = DefaultConst.DEFAULT_PAGE) Integer page,
            @RequestParam(value = DefaultConst.PAGE_SIZE, required = false, defaultValue = DefaultConst.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(value = DefaultConst.SORT_TYPE, required = false, defaultValue = DefaultConst.STRING) String sortField,
            @RequestParam(value = "sortAsc", required = false, defaultValue = DefaultConst.BOOL) Boolean isSortAsc,
            @RequestParam(value = "role", required = false, defaultValue = DefaultConst.STRING) String role,
            @RequestParam(value = "textSearch", required = false, defaultValue = DefaultConst.STRING) String textSearch
            ) {
        try {
            return response(new ResultEntity(ResponseCode.SUCCESS.getCode(), "Get list users pagination successfully",
                    userService.getAllUsersPaging(page, pageSize, sortField, isSortAsc, role, textSearch)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @ApiOperation(value = "Get all users without pagination and sorting and some conditions")
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsersExists(
    ) {
        try {
            return response(new ResultEntity(ResponseCode.SUCCESS.getCode(), "Get all users successfully",
                    userService.getAllUsers()));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
    @ApiOperation(value = "Get all users by roles")
    @GetMapping("/get-all-by-roles")
    public ResponseEntity<?> getAllUsersExists(
            @RequestParam(value = "role", defaultValue = DefaultConst.STRING) String role
    ) {
        try {
            return response(new ResultEntity(ResponseCode.SUCCESS.getCode(), "Get all users by role successfully",
                    userService.getAllUsersByRole(role)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
