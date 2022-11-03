package GFO.Spring.domain.User.presentation.dto;

import GFO.Spring.domain.User.User;
import GFO.Spring.domain.User.presentation.dto.request.SignupRequest;
import GFO.Spring.domain.User.presentation.dto.response.SignupResponse;
import GFO.Spring.domain.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void Signup(SignupRequest signupRequest) {
        userService.signUp(signupRequest);
    }
}
