package xuechun.springboot.ecommerceapp.dto.Users;

public class SignUpResponseDto {
    private String message;
    private String status;

    public SignUpResponseDto() {
    }

    public SignUpResponseDto(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
