package com.ticketevent.auth.spring;

import com.ticketevent.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
@RequiredArgsConstructor
public class UserModelDetailsService implements UserDetailsService {
    final IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return userRepository.findByEmailIgnoreCase(username);
        return userRepository.findByEmailIgnoreCase(username).map(UserModelDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " .concat(username) ));
    }
}
