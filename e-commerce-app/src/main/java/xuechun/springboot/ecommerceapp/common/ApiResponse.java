package xuechun.springboot.ecommerceapp.common;

import java.time.LocalDateTime;

public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTimeStamp() {
        return LocalDateTime.now().toString();
    }
}
