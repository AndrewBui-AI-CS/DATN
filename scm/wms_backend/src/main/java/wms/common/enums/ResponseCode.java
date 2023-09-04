package wms.common.enums;

public enum ResponseCode {
    SUCCESS(1),
    ALREADY_EXIST(2),

    NON_EXIST(3),
    FORMAT(4),
    AUTHENTICATION(5),
    USER_ACTION_FAILED(6),
    UNMATCH(7),
    SYSTEM_ACTION_FAILED(8),
    UNKNOWN(9);

    private final int code;
    ResponseCode(int value) {
        this.code = value;
    }
    public int getCode() {
        return this.code;
    }
}
