package GFO.Spring.domain.user.presentation;

import GFO.Spring.domain.user.presentation.dto.request.SigninRequest;
import GFO.Spring.domain.user.presentation.dto.request.SignupRequest;
import GFO.Spring.domain.user.presentation.dto.response.SignInResponse;
import GFO.Spring.domain.user.service.UserService;
import GFO.Spring.global.security.jwt.JwtProvider;
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
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignupRequest signupRequest) {
        userService.signUp(signupRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponse> SignIn(@Valid @RequestBody SigninRequest signinRequest) {
        SignInResponse data = userService.signIn(signinRequest);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
