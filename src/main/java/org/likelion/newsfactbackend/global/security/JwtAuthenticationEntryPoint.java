package org.likelion.newsfactbackend.global.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.global.domain.CommonResponse;
import org.likelion.newsfactbackend.global.domain.enums.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Object errorObj = request.getAttribute("error");
        ResultCode result = errorObj instanceof ResultCode ? (ResultCode) errorObj : ResultCode.EXPIRED_TOKEN;

        String token = request.getHeader("Authorization");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(objectMapper.writeValueAsString(
                CommonResponse.builder()
                        .code(401)
                        .msg(result.getMessage())
                        .build()
        ));
    }
}
