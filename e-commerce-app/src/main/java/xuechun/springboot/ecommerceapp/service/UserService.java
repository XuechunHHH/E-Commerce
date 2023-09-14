package xuechun.springboot.ecommerceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.DatatypeConverter;
import xuechun.springboot.ecommerceapp.config.MessageStrings;
import xuechun.springboot.ecommerceapp.dto.Users.SignInDto;
import xuechun.springboot.ecommerceapp.dto.Users.SignInResponseDto;
import xuechun.springboot.ecommerceapp.dto.Users.SignUpResponseDto;
import xuechun.springboot.ecommerceapp.dto.Users.SignupDto;
import xuechun.springboot.ecommerceapp.exceptions.AuthenticationFailException;
import xuechun.springboot.ecommerceapp.exceptions.CustomException;
import xuechun.springboot.ecommerceapp.model.AuthenticationToken;
import xuechun.springboot.ecommerceapp.model.User;
import xuechun.springboot.ecommerceapp.repository.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public SignUpResponseDto signUp(SignupDto signupDto) throws CustomException{
        if (userRepository.findByEmail(signupDto.getEmail()) != null) {
            throw new CustomException("User already exists");
        }
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = encryptPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("Error while encrypting password");
        }

        User user = new User(signupDto.getFirstname(), signupDto.getLastname(), signupDto.getEmail(), encryptedPassword);
        try {
            userRepository.save(user);
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new SignUpResponseDto("success", "User created successfully");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, CustomException {
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (user == null) {
            throw new AuthenticationFailException(MessageStrings.USER_NOT_PRESENT); 
        }

        try {
            if (!user.getPassword().equals(encryptPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }   
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("Hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthenticationToken token = authenticationService.findByUser(user);
        if (token == null) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }

        return new SignInResponseDto("success", token.getToken());
    }
}
