package xuechun.springboot.ecommerceapp.dto.Users;

public class SignInDto {
    private String email;
    private String password;

    public SignInDto() {
    }

    public SignInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
