package GFO.Spring.domain.User.service;

import GFO.Spring.domain.User.User;
import GFO.Spring.domain.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void signUp() {
      userRepository.save(new User());
    }
}
