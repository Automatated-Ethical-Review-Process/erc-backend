package com.g7.ercsystem.rest.auth.service;

import com.g7.ercsystem.interfaces.UserService;
import com.g7.ercsystem.repository.RefreshTokenRepository;
import com.g7.ercsystem.repository.RoleRepository;
import com.g7.ercsystem.repository.UserRepository;
import com.g7.ercsystem.rest.auth.model.EnumRole;
import com.g7.ercsystem.rest.auth.model.Role;
import com.g7.ercsystem.rest.auth.model.User;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenService refreshTokenService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        refreshTokenService.deleteByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<User> getAllReviewers() {
        return null;
    }

    @Override
    public List<User> getAllApplicants() {
        return null;
    }

    public Set<Role> getRoles(Set<String> strRoles, String email){
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role superAdminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                            .orElseThrow(()->{
                                log.error("Error: ROLE_ADMIN is not found from DB and requested by username {}",email);
                                return new RuntimeException("Error: Role is not found.");
                            });
                    roles.add(superAdminRole);
                    break;
                case "secretary":
                    Role secretaryRole = roleRepository.findByName(EnumRole.ROLE_SECRETARY)
                            .orElseThrow(()->{
                                log.error("Error: ROLE_ADMIN is not found from DB and requested by username {}",email);
                                return new RuntimeException("Error: Role is not found.");
                            });
                    roles.add(secretaryRole);
                    break;

                case "clerk":
                    Role clerkRole = roleRepository.findByName(EnumRole.ROLE_CLERK)
                            .orElseThrow(()->{
                                log.error("Error: ROLE_STUDENT is not found from DB and requested by username {}",email);
                                return new RuntimeException("Error: Role is not found.");
                            });
                    roles.add(clerkRole);
                    break;
                case "reviewer":
                    Role reviewerRole = roleRepository.findByName(EnumRole.ROLE_REVIEWER)
                            .orElseThrow(()-> {
                                log.error("Error: ROLE_COMPANY is not found from DB and requested by username {}",email);
                                return new RuntimeException("Error: Role is not found.");
                            });
                    roles.add(reviewerRole);
                    break;

                case "applicant":
                    Role applicantRole = roleRepository.findByName(EnumRole.ROLE_APPLICANT)
                            .orElseThrow(()-> {
                                log.error("Error: ROLE_COMPANY is not found from DB and requested by username {}",email);
                                return new RuntimeException("Error: Role is not found.");
                            });
                    roles.add(applicantRole);
                    break;
            }
        });
        return roles;
    }
}
