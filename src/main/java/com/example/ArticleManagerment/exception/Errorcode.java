package com.example.ArticleManagerment.exception;

public enum Errorcode {
    UNAUTHENTICATED(401, "Bạn chưa đăng nhập"),
    ACCESS_DENIED(401, "Bạn không có quyền truy cập"),

    UNCATEGORIZED_EXCEPTION(9999, "Lỗi không xác định"),
    MISSING_REQUIRED_FIELDS(1000, "Mời nhập đầy đủ thông tin"),
    KEY_INVALID(1001, "Invalid message key"),
    EMAIL_EXISTED(1003, "Email đã tồn tại, vui lòng nhập email khác"),
    PHONE_EXISTED(1004, "SDT đã tồn tại, vui lòng nhập SDT khác"),
    USER_NOT_EXISTED(1005, "User không tồn tại"),

    ROLE_NOT_FOUND(1006, "Chức vụ không tồn tại"),

    USERNAME_INVALID(1007, "Tên đăng nhập phải lớn hơn 3 ký tự"),
    PASSWORD_INVALID(1008, "Mật khẩu không hợp lệ"),
    PASSWORD_WEAK(1009, "Mật khẩu phải có độ dài từ 6 đến 100 ký tự"),
    PRICE_MUST_BE_NUMERIC(1010, "Giá tiền chỉ được nhập chữ số"),
    INVALID_STATUS(1011, "Trạng thái không hợp lệ"),
    TOPIC_NOT_FOUND(1012, "Chủ đề không tồn tại"),
    EMAIL_NOT_FOUND(1013, "Email không tồn tại"),
    EMAIL_INVALID(1014, "Email không hợp lệ"),
    INVALID_CREDENTIALS(1015, "Thông tin đăng nhập không hợp lệ"),
    PHONE_INVALID(1016, "Số điện thoại không hợp lệ")

    ;

    private int code;
    private String message;

    Errorcode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
