package xuechun.springboot.ecommerceapp.dto.Users;

public class SignInResponseDto {
    private String status;
    private String token;
    
    public SignInResponseDto() {
    }

    public SignInResponseDto(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public String getStatus() {
        return this.status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
