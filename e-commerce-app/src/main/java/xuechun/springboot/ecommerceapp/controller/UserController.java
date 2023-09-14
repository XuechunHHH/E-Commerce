package xuechun.springboot.ecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xuechun.springboot.ecommerceapp.dto.Users.SignInDto;
import xuechun.springboot.ecommerceapp.dto.Users.SignInResponseDto;
import xuechun.springboot.ecommerceapp.dto.Users.SignUpResponseDto;
import xuechun.springboot.ecommerceapp.dto.Users.SignupDto;
import xuechun.springboot.ecommerceapp.exceptions.AuthenticationFailException;
import xuechun.springboot.ecommerceapp.exceptions.CustomException;
import xuechun.springboot.ecommerceapp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws CustomException, AuthenticationFailException {
        return userService.signIn(signInDto);
    }
}
