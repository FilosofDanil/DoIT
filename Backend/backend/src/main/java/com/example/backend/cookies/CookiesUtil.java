package com.example.backend.cookies;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
public class CookiesUtil {

    public Cookie createCookie(String token) {
        String accessToken = "Bearer" +
                token;
        Cookie jwtTokenCookie = new Cookie("Authorization", accessToken);

        jwtTokenCookie.setMaxAge(24*60*60*30);
        jwtTokenCookie.setDomain("localhost");
        jwtTokenCookie.setPath("/");

        return jwtTokenCookie;
    }

    public Optional<String> readServletCookie(HttpServletRequest request, String name) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> name.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
