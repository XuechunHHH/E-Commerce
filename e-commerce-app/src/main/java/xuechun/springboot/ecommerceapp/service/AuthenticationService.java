package xuechun.springboot.ecommerceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xuechun.springboot.ecommerceapp.config.MessageStrings;
import xuechun.springboot.ecommerceapp.exceptions.AuthenticationFailException;
import xuechun.springboot.ecommerceapp.model.AuthenticationToken;
import xuechun.springboot.ecommerceapp.model.User;
import xuechun.springboot.ecommerceapp.repository.TokenRepository;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken token) {
        tokenRepository.save(token);
    }
        
    public AuthenticationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public AuthenticationToken findByUser(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUserByToken(String token) {
        AuthenticationToken authenticationToken = findByToken(token);
        if (authenticationToken != null) {
            return authenticationToken.getUser();
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException {
        AuthenticationToken authenticationToken = findByToken(token);
        if (authenticationToken == null) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        } 
        if (getUserByToken(token) == null) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }
    }
}
