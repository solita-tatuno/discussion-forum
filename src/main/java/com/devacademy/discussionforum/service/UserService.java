package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.security.CustomUserDetails;
import com.devacademy.discussionforum.dto.UserRequest;
import com.devacademy.discussionforum.dto.UserResponse;
import com.devacademy.discussionforum.repostitory.UserRepository;
import com.devacademy.discussionforum.security.UserRole;
import com.jooq.discussionforum.tables.pojos.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse addUser(UserRequest user) {
        String hashedPassword = passwordEncoder.encode(user.password());
        return userRepository.save(user, hashedPassword);
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userFromDb = userRepository.findByUsername(username);

        if (userFromDb.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Users validUser = userFromDb.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        UserRole role = validUser.getIsAdmin() ? UserRole.ROLE_ADMIN : UserRole.ROLE_USER;
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new CustomUserDetails(
                validUser.getUsername(),
                validUser.getPasswordHash(),
                authorities,
                validUser.getId(),
                validUser.getIsAdmin());
    }
}
