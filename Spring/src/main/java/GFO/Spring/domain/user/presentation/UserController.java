package GFO.Spring.domain.user.presentation;

import GFO.Spring.domain.user.presentation.dto.request.SignInRequest;
import GFO.Spring.domain.user.presentation.dto.request.SignUpRequest;
import GFO.Spring.domain.user.presentation.dto.response.SignInResponse;
import GFO.Spring.domain.user.service.UserService;
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


    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest signupRequest) {
        userService.signUp(signupRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest signinRequest) {
        SignInResponse data = userService.signIn(signinRequest);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@Valid @RequestHeader("Authorization")String accessToken){
        userService.logout(accessToken);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
