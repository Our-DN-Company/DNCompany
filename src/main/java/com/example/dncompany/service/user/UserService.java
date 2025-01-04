package com.example.dncompany.service.user;

import com.example.dncompany.dto.user.UserJoinDTO;
import com.example.dncompany.dto.user.UserLoginDTO;
import com.example.dncompany.dto.user.UserSessionDTO;
import com.example.dncompany.exception.user.LoginFailedException;
import com.example.dncompany.exception.user.UserDuplicateException;
import com.example.dncompany.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /**
     * 새로운 회원을 등록!
     * <p>
     *     회원 가입 시 다음을 검증한다 :
     *     - 로그인 아이디 중복 여부
     * </p>
     * @param userJoinDTO 회원가입 정보를 담고 있는 DTO
     * @throws UserDuplicateException 동일한 로그인 아이디가 이미 존재하는 경우
     */
    public void addUser(UserJoinDTO userJoinDTO) {
        String loginId = userJoinDTO.getLoginId();

        // 로그인아이디가 중복이라면
        if(isLoginIdDuplicated(loginId)) {
            // 확인된 예외
            // 확인되지 않은 예외가 있기 때문에 오류가 난다.
            // UserDuplicateException 에 RuntimeException 을 상속받으면 된다
            throw new UserDuplicateException("이미 사용중인 아이디 : " + loginId);
        }

        userMapper.insertUser(userJoinDTO);

    }

    /**
     * 로그인 아이디 중복 여부를 확인한다. <br>
     * 회원 가입 시 아이디 중복 검사에 활용한다.
     *
     * @param loginId 중복 검사할 로그인 아이디
     * @return 중복인 경우 true, 사용 가능한 경우 false
     */
//    회원가입 시 이전에 사용된 아이디 검사하는 서비스
//    countByLoginId가 1이라면 즉 조건이 true 라면 중복이다. false라면 가능.
    public boolean isLoginIdDuplicated(String loginId) {
        return userMapper.countByLoginId(loginId) > 0;
    }

    public UserSessionDTO getLoginInfo(UserLoginDTO userLoginDTO){
        return userMapper.selectLoginInfo(userLoginDTO)
                .orElseThrow(()-> new LoginFailedException("아이디 또는 비밀번호가 일치하지 않습니다"));
    }

}
