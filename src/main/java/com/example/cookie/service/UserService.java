package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    //login logic
    public void login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get();

            if(userDto.getPassword().equals(pw)){
                var cookie = new Cookie("authorization-cookie" , userDto.getId());
                cookie.setDomain("localhost"); //ex) naver.com. daum.net
                cookie.setPath("/");
                cookie.setHttpOnly(true); //자바스크립트로 쿠키접근 불가하게함
                cookie.setMaxAge(-1); // -1: 세션이 유지되는 동안만 사용
                cookie.setSecure(true);// https에서만 접근가능 http 불가능

                httpServletResponse.addCookie(cookie);
            }
        }else{
            throw new RuntimeException("User not Found");
        }
    }
}
