package GFO.Spring.domain.email.presentation;

import GFO.Spring.domain.email.entity.EmailAuth;
import GFO.Spring.domain.email.presentation.dto.request.EmailSendDto;
import GFO.Spring.domain.email.service.EmailAuthService;
import GFO.Spring.domain.email.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailSendService emailSendService;
    private final EmailAuthService emailAuthService;
    
    @PostMapping
    public ResponseEntity<Void> authEmail(@RequestBody @Valid EmailSendDto emailSendDto) {
        emailSendService.execute(emailSendDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> mailVerify(@Email @RequestParam String email, @RequestParam String authKey){
        emailAuthService.execute(email,authKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
