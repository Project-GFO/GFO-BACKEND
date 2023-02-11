package GFO.Spring.domain.user.service;


import GFO.Spring.domain.user.entity.User;
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

    private final UserRepository userRepository;
    private final UserUtil userUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MyPageResponse myPage(){
        User user = userUtil.currentUser();

        return MyPageResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }

    @Transactional
    public void deleteUser(PasswordRequest passwordRequest){
        User user = userUtil.currentUser();

        if(!passwordEncoder.matches(passwordRequest.getPassword(), user.getPassword())){
            throw new WrongPasswordException("비밀번호가 올바르지 않습니다.");
        }

        userRepository.delete(user);
    }

    @Transactional
    public void editPassword(PasswordRequest passwordRequest){
        User user = userUtil.currentUser();
        User user1 = userRepository.findUserByEmail(user.getEmail())
                .orElseThrow(()->new UserNotFoundException("유저를 찾을 수 없습니다."));
        if(!passwordEncoder.matches(passwordRequest.getPassword(), user.getPassword())){
            throw new WrongPasswordException("비밀번호가 올바르지 않습니다.");
        }

        user1.updatePassword(passwordEncoder.encode(passwordRequest.getPassword()));
    }
}
