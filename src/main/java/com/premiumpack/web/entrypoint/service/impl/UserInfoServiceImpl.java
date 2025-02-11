package com.premiumpack.web.entrypoint.service.impl;

import com.premiumpack.web.dataprovider.jpa.entity.UserEntity;
import com.premiumpack.web.dataprovider.jpa.repository.UserRepository;
import com.premiumpack.web.domain.RolDto;
import com.premiumpack.web.domain.UserSecurityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.getUserWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserSecurityDto.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .roles(user.getUsersRoles().stream().map(r -> RolDto.builder().rolName(r.getRol().getRolName()).build()).collect(Collectors.toList()))
                .build();
    }
}
