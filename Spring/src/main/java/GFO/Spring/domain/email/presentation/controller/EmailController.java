package GFO.Spring.domain.email.presentation.controller;

import GFO.Spring.domain.email.service.EmailService;
import GFO.Spring.domain.user.presentation.dto.UserDto;
import GFO.Spring.domain.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService mss;

    @PostMapping("/signup")
    public void signUp(@RequestBody UserDto userDto){
        //DB에 기본 정보 삽입
        userService.signup(userDto);

        //authKey 생성 & 이메일 발송
        String authKey = mss.sendAuthMail(userDto.getUserEmail());
    }

    @GetMapping("member/signUpConfirm")
    public void signUpConfirm(@RequestParam String email){
        //email, authKey 가 일치할경우 authStatus 업데이트
        userService.updateAuthStatus(email);
        System.out.println("email = " + email);
    }

}
