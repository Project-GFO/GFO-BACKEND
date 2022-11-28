package GFO.Spring.global.util;

import GFO.Spring.domain.user.entity.User;
import GFO.Spring.domain.user.repository.UserRepository;
import GFO.Spring.global.exception.exceptioncollection.UserNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    private UserRepository userRepository;

    public User CurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByEmail(email).orElseThrow(()->new UserNotFoundException("유저가 없습니다"));
    }
}
