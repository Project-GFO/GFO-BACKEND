package GFO.Spring.domain.email.presentation.controller;

import GFO.Spring.domain.email.service.MailSendService;
import GFO.Spring.domain.user.presentation.dto.UserDto;
import GFO.Spring.domain.user.presentation.dto.request.SignupRequest;
import GFO.Spring.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailSendService mss;    // mss 는MailSendService 라는 뜻!

    @PostMapping("/signup")
    public void signUp(@RequestBody SignupRequest SignupRequest){
        //DB에 기본 정보 삽입
        userService.signUp(SignupRequest);

        //authKey 생성  이메일 발송
        String authKey = mss.sendAuthMail(SignupRequest.getEmail());
    }

    @GetMapping("member/signup-confirm")
    public void signUpConfirm(@RequestParam String email){
        userService.updateAuthStatus(email);
        System.out.println("email = " + email);
    }

}
