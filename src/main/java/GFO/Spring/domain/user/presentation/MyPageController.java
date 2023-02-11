package GFO.Spring.domain.user.presentation;

import GFO.Spring.domain.user.presentation.dto.request.PasswordRequest;
import GFO.Spring.domain.user.presentation.dto.response.MyPageResponse;
import GFO.Spring.domain.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class MyPageController {

    private MyPageService myPageService;

    @GetMapping("/my")
    public ResponseEntity<MyPageResponse> myPage(){
        return ResponseEntity.ok().body(myPageService.myPage());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody @Valid PasswordRequest passwordRequest){
        myPageService.deleteUser(passwordRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> editUser(@RequestBody @Valid PasswordRequest passwordRequest){
        myPageService.editPassword(passwordRequest);

        return ResponseEntity.ok().build();
    }
}
