package ru.ok.journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ok.journal.dto.UserDto;
import ru.ok.journal.model.Role;
import ru.ok.journal.model.User;
import ru.ok.journal.repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailsService implements
        org.springframework.security.core.userdetails.UserDetailsService,
        IUserService {
    private final UserRepository userRepository;
    private final IDtoService dtoService;

    @Autowired
    public UserDetailsService(UserRepository userRepository, IDtoService dtoService) {
        this.userRepository = userRepository;
        this.dtoService = dtoService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("No user find with such login: " + login);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), user.isEnabled(),
                true, true, true,
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return dtoService.convertToDto(userRepository.findByLogin(auth.getName()));
    }

    @Override
    public User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByLogin(auth.getName());
    }
}
