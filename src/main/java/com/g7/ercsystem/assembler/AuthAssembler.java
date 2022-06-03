package com.g7.ercsystem.assembler;

import com.g7.ercsystem.payload.requests.SignUpRequest;
import com.g7.ercsystem.rest.auth.model.User;
import com.g7.ercsystem.rest.auth.service.UserServiceImpl;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Builder
public class AuthAssembler {

    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    public User SignUpRequestToUserEntity(SignUpRequest request){
        User user =  new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setMemberDetails(request.getMemberDetails());
        user.setRoles(userService.getRoles(request.getRoles(), request.getEmail()));
        user.setCreatedDate(Instant.now());
        user.setModifiedDate(Instant.now());
        user.setIsVerified(true);
        user.setIsLocked(true);
        return user;
    }
}
