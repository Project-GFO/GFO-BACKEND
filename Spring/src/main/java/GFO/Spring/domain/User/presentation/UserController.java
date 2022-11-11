package GFO.Spring.domain.User.presentation;

import GFO.Spring.domain.User.presentation.dto.request.SignupRequest;
import GFO.Spring.domain.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> Signup(@RequestBody @Valid SignupRequest signupRequest) {
        userService.signUp(signupRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
