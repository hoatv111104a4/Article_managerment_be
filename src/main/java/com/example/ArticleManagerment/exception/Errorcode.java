package com.example.ArticleManagerment.exception;

public enum Errorcode {
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi không xác định"),
    MISSING_REQUIRED_FIELDS(1000, "Mời nhập đầy đủ thông tin"),
    KEY_INVALID(1001, "Invalid message key"),

    EMAIL_EXISTED(1002, "Email đã tồn tại, vui lòng nhập email khác"),
    PHONE_EXISTED(1002, "SDT đã tồn tại, vui lòng nhập SDT khác"),
    USER_NOT_EXISTED(1003, "User không tồn tại"),
    UNAUTHENTICATED(403, "Bạn chưa đăng nhập"),
    ACCESS_DENIED(401, "Bạn không có quyền truy cập"),
    ROLE_NOT_FOUND(1006, "Chức vụ không tồn tại"),

    USERNAME_INVALID(1010, "Tên đăng nhập phải lớn hơn 3 ký tự"),
    PASSWORD_INVALID(1011, "Mật khẩu không hợp lệ"),
    PASSWORD_WEAK(1012, "Mật khẩu phải có độ dài từ 6 đến 100 ký tự"),
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
