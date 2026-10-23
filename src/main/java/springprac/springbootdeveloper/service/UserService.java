package springprac.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springprac.springbootdeveloper.domain.User;
import springprac.springbootdeveloper.dto.AddUserRequest;
import springprac.springbootdeveloper.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder().email(dto.getEmail()).password(passwordEncoder.encode(dto.getPassword())).build()).getId();
    }
}
