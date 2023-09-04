package wms.common;

import lombok.Data;
import wms.common.constant.MessageConstant;

@Data
public class ResultEntity {
    private Integer code;
    private String message;
    private Object data;
    public ResultEntity() {
        this.code = 0;
        this.message = "No message";
    }
    public ResultEntity(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
