package GFO.Spring.domain.User.presentation;

import GFO.Spring.domain.User.presentation.dto.request.SignupRequest;
import GFO.Spring.domain.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void Signup(@RequestBody SignupRequest signupRequest) {
        userService.signUp(signupRequest);
    }
}
