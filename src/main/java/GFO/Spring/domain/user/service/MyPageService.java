package GFO.Spring.domain.user.service;


import GFO.Spring.domain.user.entity.User;
import GFO.Spring.domain.user.exception.exceptioncollection.EmailNotFoundException;
import GFO.Spring.domain.user.exception.exceptioncollection.EmailNotVerifiedException;
import GFO.Spring.domain.user.exception.exceptioncollection.UserNotFoundException;
import GFO.Spring.domain.user.exception.exceptioncollection.WrongPasswordException;
import GFO.Spring.domain.user.presentation.dto.request.PasswordRequest;
import GFO.Spring.domain.user.presentation.dto.response.MyPageResponse;
import GFO.Spring.domain.user.repository.UserRepository;
import GFO.Spring.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final User user;
    private final UserRepository userRepository;
    private final UserUtil userUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MyPageResponse myPage(){
        User user = userUtil.currentUser();

        return MyPageResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    @Transactional
    public void deleteUser(){
        User user = userUtil.currentUser();
        if(authenticationStatus(user.getEmail())){
            userRepository.delete(user);
        }
    }

    private Boolean authenticationStatus(String email){
        User emailAuth = userRepository.findById(email).orElseThrow(()->new EmailNotFoundException("이메일을 찾지못했습니다."));

        if(!emailAuth.getVerificationStatus()){
            throw new EmailNotVerifiedException("이메일이 인증되지 않았습니다.");
        }

        return true;
    }
    @Transactional
    public void editPassword(PasswordRequest passwordRequest){
        User user = userUtil.currentUser();
        if(authenticationStatus(user.getEmail())){
            user.updatePassword(passwordEncoder.encode(passwordRequest.getPassword()));
        }
    }
}
